/*
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Library General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this program (see the file COPYING.LIB for more
 * details); if not, write to the Free Software Foundation, Inc.,
 * 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package org.dcache.chimera.nfs.v4;

import java.util.List;
import org.dcache.chimera.nfs.v4.xdr.nfsstat4;
import org.dcache.chimera.nfs.v4.xdr.entry4;
import org.dcache.chimera.nfs.v4.xdr.dirlist4;
import org.dcache.chimera.nfs.v4.xdr.verifier4;
import org.dcache.chimera.nfs.v4.xdr.component4;
import org.dcache.chimera.nfs.v4.xdr.utf8str_cs;
import org.dcache.chimera.nfs.v4.xdr.nfs_cookie4;
import org.dcache.chimera.nfs.v4.xdr.uint64_t;
import org.dcache.chimera.nfs.v4.xdr.nfs4_prot;
import org.dcache.chimera.nfs.v4.xdr.nfs_argop4;
import org.dcache.chimera.nfs.v4.xdr.nfs_opnum4;
import org.dcache.chimera.nfs.v4.xdr.utf8string;
import org.dcache.chimera.nfs.v4.xdr.READDIR4resok;
import org.dcache.chimera.nfs.v4.xdr.READDIR4res;
import org.dcache.chimera.nfs.ChimeraNFSException;
import java.util.Random;

import org.dcache.chimera.DirectoryStreamHelper;
import org.dcache.chimera.FsInode;
import org.dcache.chimera.HimeraDirectoryEntry;
import org.dcache.chimera.posix.AclHandler;
import org.dcache.chimera.posix.Stat;
import org.dcache.chimera.posix.UnixAcl;
import org.dcache.chimera.util.DirectoryListCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperationREADDIR extends AbstractNFSv4Operation {

    private static final Logger _log = LoggerFactory.getLogger(OperationREADDIR.class);


    // needed to calculate replay size for READDIR4
    /*
     * RFS4_MINLEN_ENTRY4: XDR-encoded size of smallest possible dirent.
     *   This is used to return NFS4ERR_TOOSMALL when clients specify
     *   maxcount that isn't large enough to hold the smallest possible
     *   XDR encoded dirent.
     *
     *       sizeof cookie (8 bytes) +
     *       sizeof name_len (4 bytes) +
     *       sizeof smallest (padded) name (4 bytes) +
     *       sizeof bitmap4_len (12 bytes) +   NOTE: we always encode len=2 bm4
     *       sizeof attrlist4_len (4 bytes) +
     *       sizeof next boolean (4 bytes)
     *
     * RFS4_MINLEN_RDDIR4: XDR-encoded size of READDIR op reply containing
     * the smallest possible entry4 (assumes no attrs requested).
     *   sizeof nfsstat4 (4 bytes) +
     *   sizeof verifier4 (8 bytes) +
     *   sizeof entsecond_to_ry4list bool (4 bytes) +
     *   sizeof entry4   (36 bytes) +
     *   sizeof eof bool  (4 bytes)
     *
     * RFS4_MINLEN_RDDIR_BUF: minimum length of buffer server will provide to
     *   VOP_READDIR.  Its value is the size of the maximum possible dirent
     *   for solaris.  The DIRENT64_RECLEN macro returns the size of dirent
     *   required for a given name length.  MAXNAMELEN is the maximum
     *   filename length allowed in Solaris.  The first two DIRENT64_RECLEN()
     *   macros are to allow for . and .. entries -- just a minor tweak to try
     *   and guarantee that buffer we give to VOP_READDIR will be large enough
     *   to hold ., .., and the largest possible solaris dirent64.
     */

    private static final int ENTRY4_SIZE = 36;
    private static final int DIRLIST4_SIZE = 4 + nfs4_prot.NFS4_VERIFIER_SIZE + 4 + ENTRY4_SIZE + 4;
    private static final int READDIR4RESOK_SIZE = DIRLIST4_SIZE + ENTRY4_SIZE;


    private static final DirectoryListCache<verifier4,List<HimeraDirectoryEntry>> _dlCache =
        new DirectoryListCache<verifier4, List<HimeraDirectoryEntry>>();

    /**
     * random to generate verifiers
     */
    private final static Random _random = new Random();

	OperationREADDIR(nfs_argop4 args) {
		super(args, nfs_opnum4.OP_READDIR);
	}


    /*
     * to simulate snapshot-like list following trick is used:
     *
     *   1. for each mew readdir(plus) ( cookie == 0 ) generate new cookie verifier
     *   2. list result stored in timed Map, where verifier used as a key
     *   3. remove cached entry as soon as list sent
     *
     */

	@Override
	public boolean process(CompoundContext context) {


        READDIR4res res = new READDIR4res();

        try {

            FsInode dir = context.currentInode();

            Stat dirStat = dir.statCache();
            UnixAcl acl = new UnixAcl(dirStat.getUid(), dirStat.getGid(),dirStat.getMode() & 0777 );
            if ( ! context.getAclHandler().isAllowed(acl, context.getUser(), AclHandler.ACL_LOOKUP) ) {
                throw new ChimeraNFSException( nfsstat4.NFS4ERR_ACCESS, "Permission denied."  );
            }

            if( !dir.exists()  ) {
                throw new ChimeraNFSException( nfsstat4.NFS4ERR_NOENT, "Path Do not exist."  );
            }

            if(  !dir.isDirectory() ) {
                throw new ChimeraNFSException( nfsstat4.NFS4ERR_NOTDIR, "Path is not a directory."  );
            }

            List<HimeraDirectoryEntry> dirList = null;
            verifier4 verifier;
            long startValue = _args.opreaddir.cookie.value.value;


            /**
             * we have to fake cookie values, while '0' and '1' is reserved
             * so we start with 3
             */
            final long COOKIE_OFFSET = 3;
            if( startValue != 0 ) {

                // while client sends to us last cookie, we have to continue from the next one
                ++startValue;

                verifier = _args.opreaddir.cookieverf;

                dirList = _dlCache.get(verifier);
                if( dirList == null ) {
                    throw new ChimeraNFSException(nfsstat4.NFS4ERR_NOT_SAME, "invalid verifier");
                    //dirList = context.getFs().listDirFull(dir) ;
                }

            }else{
                byte[] b = new byte[nfs4_prot.NFS4_VERIFIER_SIZE];
                _random.nextBytes(b);
                verifier = new verifier4(b);
                dirList = DirectoryStreamHelper.listOf(dir);
                _dlCache.add(verifier, dirList, dirList.size()  );
                startValue = COOKIE_OFFSET;
            }


            // the cookie==1,2 is reserved
            if( (startValue > dirList.size()+COOKIE_OFFSET) || (startValue < COOKIE_OFFSET) ) {
                throw new ChimeraNFSException( nfsstat4.NFS4ERR_BAD_COOKIE, "bad cookie : " + startValue + " " + dirList.size() );
            }

            if( _args.opreaddir.maxcount.value.value < READDIR4RESOK_SIZE ) {
                throw new ChimeraNFSException(nfsstat4.NFS4ERR_TOOSMALL, "maxcount too small");
            }

            res.resok4 = new READDIR4resok();
            res.resok4.reply = new dirlist4();

            res.resok4.cookieverf = verifier;

            int currcount = READDIR4RESOK_SIZE;
            int dircount = 0;
            res.resok4.reply.entries = new entry4();
            entry4 currentEntry = res.resok4.reply.entries;
            entry4 lastEntry = null;

            /*
             * hope to send all entries at once.
             * if it's not the case, eof flag will be set to false
             */
            res.resok4.reply.eof = true;
            int fcount = 0;
            for ( long i = startValue; i < dirList.size() + COOKIE_OFFSET; i++) { // chimera have . and ..

                HimeraDirectoryEntry le = dirList.get((int)(i-COOKIE_OFFSET));
                String name = le.getName();

                // skip . and .. while nfsv4 do not care about them
                if( name.equals(".") ) continue;
                if( name.equals("..") ) continue;

                fcount++;

                FsInode ei = le.getInode();

                currentEntry.name = new component4( new utf8str_cs( new utf8string(name.getBytes()) ));
                // keep offset
                currentEntry.cookie = new nfs_cookie4( new uint64_t(i) );

                // TODO: catch here error from getattr and reply 'fattr4_rdattr_error' to the client
                currentEntry.attrs = OperationGETATTR.getAttributes(_args.opreaddir.attr_request, ei);
                currentEntry.nextentry = null;

                // check if writing this entry exceeds the count limit
                int newSize = ENTRY4_SIZE + name.length() + currentEntry.name.value.value.value.length + currentEntry.attrs.attr_vals.value.length;
                int newDirSize = name.length() + 4; // name + sizeof(long)
                if ( (currcount + newSize > _args.opreaddir.maxcount.value.value ) || (dircount + newDirSize > _args.opreaddir.dircount.value.value) ){

                    res.resok4.reply.eof = false;

                   _log.debug("Sending {} entries ({} bytes from {}, dircount = {} from {} ) cookie = {} total {}",
                       new Object[] {
                           i - startValue, currcount,
                           _args.opreaddir.maxcount.value.value,
                           dircount,
                           _args.opreaddir.dircount.value.value,
                           startValue, dirList.size()
                       }
                   );

                    break;
                }
                dircount += newDirSize;
                currcount += newSize;

                lastEntry = currentEntry;
                if( i + 1 < dirList.size() + COOKIE_OFFSET) {
                    currentEntry.nextentry = new entry4();
                    currentEntry = currentEntry.nextentry;
                }

            }

            // empty directory
            if( lastEntry == null  ){
                res.resok4.reply.entries = null;
            }else{
                lastEntry.nextentry = null;
            }

            if(res.resok4.reply.eof) {
      //      	_dlCache.remove(new VerifierAsKey(verifier));
            }

            res.status = nfsstat4.NFS4_OK;
            _log.debug("Sending {} entries ({} bytes from {}, dircount = {} from {} ) cookie = {} total {} EOF={}",
                new Object[] {
                    fcount, currcount,
                    _args.opreaddir.maxcount.value.value,
                    startValue,
                    _args.opreaddir.dircount.value.value,
                    dirList.size(), res.resok4.reply.eof
                }
            );

        }catch(ChimeraNFSException he) {
            _log.debug("READDIR: {}", he.getMessage() );
            res.status = he.getStatus();
        }catch(Exception e) {
        	res.status = nfsstat4.NFS4ERR_SERVERFAULT;
            _log.error("READDIR4", e);
        }

        _result.opreaddir = res;

            context.processedOperations().add(_result);
            return res.status == nfsstat4.NFS4_OK;

	}

}