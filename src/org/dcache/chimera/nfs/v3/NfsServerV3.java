/*
 * $Id:NfsServerV3.java 140 2007-06-07 13:44:55Z tigran $
 */
package org.dcache.chimera.nfs.v3;

import org.dcache.chimera.nfs.v3.xdr.LOOKUP3res;
import org.dcache.chimera.nfs.v3.xdr.WRITE3resfail;
import org.dcache.chimera.nfs.v3.xdr.RMDIR3resok;
import org.dcache.chimera.nfs.v3.xdr.SYMLINK3resfail;
import org.dcache.chimera.nfs.v3.xdr.post_op_fh3;
import org.dcache.chimera.nfs.v3.xdr.READLINK3args;
import org.dcache.chimera.nfs.v3.xdr.uint64;
import org.dcache.chimera.nfs.v3.xdr.MKDIR3res;
import org.dcache.chimera.nfs.v3.xdr.stable_how;
import org.dcache.chimera.nfs.v3.xdr.WRITE3args;
import org.dcache.chimera.nfs.v3.xdr.createmode3;
import org.dcache.chimera.nfs.v3.xdr.post_op_attr;
import org.dcache.chimera.nfs.v3.xdr.LINK3resfail;
import org.dcache.chimera.nfs.v3.xdr.READ3resfail;
import org.dcache.chimera.nfs.v3.xdr.MKDIR3resok;
import org.dcache.chimera.nfs.v3.xdr.READDIR3args;
import org.dcache.chimera.nfs.v3.xdr.LOOKUP3resfail;
import org.dcache.chimera.nfs.v3.xdr.dirlistplus3;
import org.dcache.chimera.nfs.v3.xdr.SYMLINK3resok;
import org.dcache.chimera.nfs.v3.xdr.READDIR3resok;
import org.dcache.chimera.nfs.v3.xdr.entry3;
import org.dcache.chimera.nfs.v3.xdr.READ3args;
import org.dcache.chimera.nfs.v3.xdr.nfsstat3;
import org.dcache.chimera.nfs.v3.xdr.LOOKUP3args;
import org.dcache.chimera.nfs.v3.xdr.PATHCONF3res;
import org.dcache.chimera.nfs.v3.xdr.uid3;
import org.dcache.chimera.nfs.v3.xdr.gid3;
import org.dcache.chimera.nfs.v3.xdr.LINK3args;
import org.dcache.chimera.nfs.v3.xdr.REMOVE3res;
import org.dcache.chimera.nfs.v3.xdr.READ3resok;
import org.dcache.chimera.nfs.v3.xdr.sattr3;
import org.dcache.chimera.nfs.v3.xdr.count3;
import org.dcache.chimera.nfs.v3.xdr.MKNOD3args;
import org.dcache.chimera.nfs.v3.xdr.READ3res;
import org.dcache.chimera.nfs.v3.xdr.READLINK3resok;
import org.dcache.chimera.nfs.v3.xdr.cookie3;
import org.dcache.chimera.nfs.v3.xdr.LOOKUP3resok;
import org.dcache.chimera.nfs.v3.xdr.READDIR3resfail;
import org.dcache.chimera.nfs.v3.xdr.RMDIR3res;
import org.dcache.chimera.nfs.v3.xdr.RMDIR3resfail;
import org.dcache.chimera.nfs.v3.xdr.WRITE3resok;
import org.dcache.chimera.nfs.v3.xdr.REMOVE3resfail;
import org.dcache.chimera.nfs.v3.xdr.WRITE3res;
import org.dcache.chimera.nfs.v3.xdr.wcc_data;
import org.dcache.chimera.nfs.v3.xdr.nfs3_prot;
import org.dcache.chimera.nfs.v3.xdr.MKDIR3resfail;
import org.dcache.chimera.nfs.v3.xdr.RENAME3resok;
import org.dcache.chimera.nfs.v3.xdr.dirlist3;
import org.dcache.chimera.nfs.v3.xdr.READDIRPLUS3args;
import org.dcache.chimera.nfs.v3.xdr.MKDIR3args;
import org.dcache.chimera.nfs.v3.xdr.fattr3;
import org.dcache.chimera.nfs.v3.xdr.MKNOD3res;
import org.dcache.chimera.nfs.v3.xdr.fileid3;
import org.dcache.chimera.nfs.v3.xdr.SETATTR3resfail;
import org.dcache.chimera.nfs.v3.xdr.uint32;
import org.dcache.chimera.nfs.v3.xdr.entryplus3;
import org.dcache.chimera.nfs.v3.xdr.pre_op_attr;
import org.dcache.chimera.nfs.v3.xdr.SETATTR3args;
import org.dcache.chimera.nfs.v3.xdr.SYMLINK3res;
import org.dcache.chimera.nfs.v3.xdr.PATHCONF3args;
import org.dcache.chimera.nfs.v3.xdr.writeverf3;
import org.dcache.chimera.nfs.v3.xdr.RENAME3args;
import org.dcache.chimera.nfs.v3.xdr.SYMLINK3args;
import org.dcache.chimera.nfs.v3.xdr.READDIRPLUS3resfail;
import org.dcache.chimera.nfs.v3.xdr.nfs_fh3;
import org.dcache.chimera.nfs.v3.xdr.REMOVE3resok;
import org.dcache.chimera.nfs.v3.xdr.READLINK3res;
import org.dcache.chimera.nfs.v3.xdr.RENAME3res;
import org.dcache.chimera.nfs.v3.xdr.RMDIR3args;
import org.dcache.chimera.nfs.v3.xdr.READDIRPLUS3resok;
import org.dcache.chimera.nfs.v3.xdr.cookieverf3;
import org.dcache.chimera.nfs.v3.xdr.nfs3_protServerStub;
import org.dcache.chimera.nfs.v3.xdr.READDIRPLUS3res;
import org.dcache.chimera.nfs.v3.xdr.nfstime3;
import org.dcache.chimera.nfs.v3.xdr.LINK3resok;
import org.dcache.chimera.nfs.v3.xdr.size3;
import org.dcache.chimera.nfs.v3.xdr.REMOVE3args;
import org.dcache.chimera.nfs.v3.xdr.wcc_attr;
import org.dcache.chimera.nfs.v3.xdr.SETATTR3res;
import org.dcache.chimera.nfs.v3.xdr.LINK3res;
import org.dcache.chimera.nfs.v3.xdr.SETATTR3resok;
import org.dcache.chimera.nfs.v3.xdr.READDIR3res;
import org.dcache.chimera.nfs.v3.xdr.PATHCONF3resok;
import org.dcache.chimera.nfs.v3.xdr.nfspath3;
import org.dcache.chimera.nfs.v3.xdr.filename3;
import org.dcache.chimera.nfs.v3.xdr.FSINFO3res;
import org.dcache.chimera.nfs.v3.xdr.GETATTR3resok;
import org.dcache.chimera.nfs.v3.xdr.CREATE3args;
import org.dcache.chimera.nfs.v3.xdr.CREATE3resok;
import org.dcache.chimera.nfs.v3.xdr.FSSTAT3args;
import org.dcache.chimera.nfs.v3.xdr.FSSTAT3resok;
import org.dcache.chimera.nfs.v3.xdr.FSINFO3args;
import org.dcache.chimera.nfs.v3.xdr.CREATE3res;
import org.dcache.chimera.nfs.v3.xdr.GETATTR3args;
import org.dcache.chimera.nfs.v3.xdr.ACCESS3resfail;
import org.dcache.chimera.nfs.v3.xdr.GETATTR3res;
import org.dcache.chimera.nfs.v3.xdr.COMMIT3res;
import org.dcache.chimera.nfs.v3.xdr.FSINFO3resok;
import org.dcache.chimera.nfs.v3.xdr.ACCESS3resok;
import org.dcache.chimera.nfs.v3.xdr.FSSTAT3res;
import org.dcache.chimera.nfs.v3.xdr.COMMIT3args;
import org.dcache.chimera.nfs.v3.xdr.ACCESS3args;
import org.dcache.chimera.nfs.v3.xdr.CREATE3resfail;
import org.dcache.chimera.nfs.v3.xdr.FSINFO3resfail;
import org.dcache.chimera.nfs.v3.xdr.ACCESS3res;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.dcache.chimera.ChimeraFsException;
import org.dcache.chimera.DirectoryStreamHelper;
import org.dcache.chimera.FileNotFoundHimeraFsException;
import org.dcache.chimera.FileSystemProvider;
import org.dcache.chimera.FsInode;
import org.dcache.chimera.FsStat;
import org.dcache.chimera.HimeraDirectoryEntry;
import org.dcache.chimera.IOHimeraFsException;
import org.dcache.chimera.UnixPermission;
import org.dcache.chimera.nfs.ChimeraNFSException;
import org.dcache.chimera.nfs.ExportFile;
import org.dcache.chimera.nfs.NFSHandle;
import org.dcache.chimera.nfs.v3.xdr.COMMIT3resfail;
import org.dcache.chimera.nfs.v3.xdr.FSSTAT3resfail;
import org.dcache.chimera.nfs.v3.xdr.MKNOD3resfail;
import org.dcache.chimera.nfs.v3.xdr.READLINK3resfail;
import org.dcache.chimera.nfs.v3.xdr.RENAME3resfail;
import org.dcache.chimera.posix.AclHandler;
import org.dcache.chimera.posix.Stat;
import org.dcache.chimera.posix.UnixAcl;
import org.dcache.chimera.posix.UnixPermissionHandler;
import org.dcache.chimera.util.DirectoryListCache;
import org.dcache.xdr.OncRpcException;
import org.dcache.xdr.RpcCall;

import static org.dcache.chimera.nfs.v3.HimeraNfsUtils.defaultPostOpAttr;
import static org.dcache.chimera.nfs.v3.HimeraNfsUtils.defaultWccData;

public class NfsServerV3 extends nfs3_protServerStub {

    // needed to calculate replay size for READDIR3 and READDIRPLUS3
    private static final int ENTRY3_SIZE = 24;
    private static final int ENTRYPLUS3_SIZE = 124;
    private static final int READDIR3RESOK_SIZE = 104;
    private static final int READDIRPLUS3RESOK_SIZE = 104;
    private static final Logger _log = Logger.getLogger(NfsServerV3.class.getName());
    private static final AclHandler _permissionHandler = UnixPermissionHandler.getInstance();
    private final FileSystemProvider _fs;
    private final ExportFile _exports;
    private static final DirectoryListCache<cookieverf3, List<HimeraDirectoryEntry>> _dlCacheFull =
            new DirectoryListCache<cookieverf3, List<HimeraDirectoryEntry>>();
    /**
     * random to generate verifiers
     */
    private final static Random _random = new Random();

    public NfsServerV3(ExportFile exports, FileSystemProvider fs) throws OncRpcException, IOException {
        _fs = fs;
        _exports = exports;
    }

    @Override
    public ACCESS3res NFSPROC3_ACCESS_3(RpcCall call$, ACCESS3args arg1) {

        ACCESS3res res = new ACCESS3res();

        org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
        _log.log(Level.FINEST, "NFS Request ACCESS uid: {0}", user);

        try {

            res.status = nfsstat3.NFS3_OK;
            res.resok = new ACCESS3resok();

            int reqAccess = arg1.access.value;

            res.resok.obj_attributes = new post_op_attr();
            res.resok.obj_attributes.attributes_follow = true;
            res.resok.obj_attributes.attributes = new fattr3();

            FsInode inode = NFSHandle.toFsInode(_fs, arg1.object.data);

            if (!inode.exists()) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_STALE, "Path do not exist.");
            }
            Stat objStat = inode.statCache();

            HimeraNfsUtils.fill_attributes(objStat, res.resok.obj_attributes.attributes);

            UnixAcl acl = new UnixAcl(objStat.getUid(), objStat.getGid(), objStat.getMode() & 0777);

            int realAccess = 0;

            if ((reqAccess & nfs3_prot.ACCESS3_EXECUTE) == nfs3_prot.ACCESS3_EXECUTE) {
                if (_permissionHandler.isAllowed(acl, user, AclHandler.ACL_EXECUTE)) {
                    realAccess |= nfs3_prot.ACCESS3_EXECUTE;
                }
            }

            if ((reqAccess & nfs3_prot.ACCESS3_EXTEND) == nfs3_prot.ACCESS3_EXTEND) {
                if (_permissionHandler.isAllowed(acl, user, AclHandler.ACL_INSERT)) {
                    realAccess |= nfs3_prot.ACCESS3_EXTEND;
                }
            }

            if ((reqAccess & nfs3_prot.ACCESS3_LOOKUP) == nfs3_prot.ACCESS3_LOOKUP) {
                if (_permissionHandler.isAllowed(acl, user, AclHandler.ACL_LOOKUP)) {
                    realAccess |= nfs3_prot.ACCESS3_LOOKUP;
                }
            }

            if ((reqAccess & nfs3_prot.ACCESS3_DELETE) == nfs3_prot.ACCESS3_DELETE) {
                if (_permissionHandler.isAllowed(acl, user, AclHandler.ACL_DELETE)) {
                    realAccess |= nfs3_prot.ACCESS3_DELETE;
                }
            }

            if ((reqAccess & nfs3_prot.ACCESS3_MODIFY) == nfs3_prot.ACCESS3_MODIFY) {
                if (_permissionHandler.isAllowed(acl, user, AclHandler.ACL_WRITE)) {
                    realAccess |= nfs3_prot.ACCESS3_MODIFY;
                }
            }

            if ((reqAccess & nfs3_prot.ACCESS3_READ) == nfs3_prot.ACCESS3_READ) {
                if (_permissionHandler.isAllowed(acl, user, AclHandler.ACL_READ)) {
                    realAccess |= nfs3_prot.ACCESS3_READ;
                }
            }

            res.resok.access = new uint32(realAccess);
        } catch (ChimeraNFSException hne) {
            res.status = hne.getStatus();
            res.resfail = new ACCESS3resfail();
            res.resfail.obj_attributes = defaultPostOpAttr();
        } catch (ChimeraFsException e) {
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new ACCESS3resfail();
            res.resfail.obj_attributes = defaultPostOpAttr();
            _log.log(Level.SEVERE, "ACCESS", e);
        } catch (Exception e) {
            _log.log(Level.SEVERE, "ACCESS", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new ACCESS3resfail();
            res.resfail.obj_attributes = defaultPostOpAttr();
        }

        if (res.status != nfsstat3.NFS3_OK) {
            _log.log(Level.SEVERE, "Access failed : {0}", HimeraNfsUtils.nfsErr2String(res.status));
        }
        return res;

    }

    @Override
    public COMMIT3res NFSPROC3_COMMIT_3(RpcCall call$, COMMIT3args arg1) {
        COMMIT3res res = new COMMIT3res();
        res.status = nfsstat3.NFS3ERR_NOTSUPP;
        res.resfail = new COMMIT3resfail();
        res.resfail.file_wcc = defaultWccData();
        return res;

    }

    @Override
    public CREATE3res NFSPROC3_CREATE_3(RpcCall call$, CREATE3args arg1) {

        org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
        _log.log(Level.FINEST, "NFS Request CREATE3 uid: {0}", user);

        CREATE3res res = new CREATE3res();

        FsInode parent = NFSHandle.toFsInode(_fs, arg1.where.dir.data);
        String path = arg1.where.name.value;

        sattr3 newAttr = null;
        int mode = arg1.how.mode;

        if ((mode == createmode3.UNCHECKED) || (mode == createmode3.GUARDED)) {
            newAttr = arg1.how.obj_attributes;
        }

        try {

            FsInode inode = null;
            Stat inodeStat = new Stat();
            Stat parentStat = null;
            boolean exists = true;
            long now = System.currentTimeMillis();

            try {
                inode = parent.inodeOf(path);
            } catch (FileNotFoundHimeraFsException hfe) {
                exists = false;
            }

            if (exists && (mode != createmode3.UNCHECKED)) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_EXIST, "File alredy exist.");
            }

            parentStat = parent.statCache();
            UnixAcl acl = new UnixAcl(parentStat.getUid(), parentStat.getGid(), parentStat.getMode() & 0777);
            if (!_permissionHandler.isAllowed(acl, user, AclHandler.ACL_INSERT)) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_ACCES, "Permission denied.");
            }

            try {
                int fmode = 0644 | UnixPermission.S_IFREG;
                if (newAttr != null) {
                    fmode = newAttr.mode.mode.value.value | UnixPermission.S_IFREG;
                }
                inode = parent.create(path, user.getUID(), user.getGID(), fmode);

                // as inode is new, we can use our information and do not ask DB for it
                inodeStat.setATime(now);
                inodeStat.setCTime(now);
                inodeStat.setMTime(now);

                inodeStat.setGid(user.getGID());
                inodeStat.setUid(user.getUID());

                inodeStat.setSize(0);
                inodeStat.setNlink(1);
                inodeStat.setIno((int) inode.id());
                inodeStat.setMode(fmode);

            } catch (ChimeraFsException hfe) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_ACCES, "Permission denied.");
            }

            res.status = nfsstat3.NFS3_OK;
            res.resok = new CREATE3resok();
            res.resok.obj_attributes = new post_op_attr();
            res.resok.obj_attributes.attributes_follow = true;
            res.resok.obj_attributes.attributes = new fattr3();

            HimeraNfsUtils.fill_attributes(inodeStat, res.resok.obj_attributes.attributes);
            res.resok.obj = new post_op_fh3();
            res.resok.obj.handle_follows = true;
            res.resok.obj.handle = new nfs_fh3();
            res.resok.obj.handle.data = inode.toFullString().getBytes();

            res.resok.dir_wcc = new wcc_data();
            res.resok.dir_wcc.after = new post_op_attr();
            res.resok.dir_wcc.after.attributes_follow = true;
            res.resok.dir_wcc.after.attributes = new fattr3();

            // correct parent modification time and nlink counter
            parentStat.setNlink(parentStat.getNlink() + 1);
            parentStat.setMTime(now);

            HimeraNfsUtils.fill_attributes(parentStat, res.resok.dir_wcc.after.attributes);

            res.resok.dir_wcc.before = new pre_op_attr();
            res.resok.dir_wcc.before.attributes_follow = false;

        } catch (ChimeraNFSException hne) {

            _log.log(Level.FINE, hne.getMessage());
            res.resfail = new CREATE3resfail();
            res.resfail.dir_wcc = defaultWccData();

            res.status = hne.getStatus();
        } catch (ChimeraFsException e) {
            _log.log(Level.SEVERE, "Create {0}", path);
            res.status = nfsstat3.NFS3ERR_IO;
            res.resfail = new CREATE3resfail();
            res.resfail.dir_wcc = defaultWccData();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "create", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new CREATE3resfail();
            res.resfail.dir_wcc = defaultWccData();
        }

        if (res.status != nfsstat3.NFS3_OK) {
            _log.log(Level.SEVERE, "create failed : {0}", HimeraNfsUtils.nfsErr2String(res.status));
        }
        return res;

    }

    @Override
    public FSINFO3res NFSPROC3_FSINFO_3(RpcCall call$, FSINFO3args arg1) {

        org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
        _log.log(Level.FINEST, "NFS Request FSINFO from: {0}", user);

        FSINFO3res res = new FSINFO3res();
        FsInode inode = NFSHandle.toFsInode(_fs, arg1.fsroot.data);
        try {

            res.status = nfsstat3.NFS3_OK;
            res.resok = new FSINFO3resok();

            // max size of READ request supported by server
            res.resok.rtmax = new uint32(32768);
            // preferred size of READ request
            res.resok.rtpref = new uint32(32768);
            // suggested multiple for the size of READ request
            res.resok.rtmult = new uint32(8);
            // max size of WRITE request supported by server
            res.resok.wtmax = new uint32(32768);
            // preferred size of WRITE request
            res.resok.wtpref = new uint32(32768);
            // suggested multiple of WRITE request
            res.resok.wtmult = new uint32(8);
            // preferred size of READDIR request
            res.resok.dtpref = new uint32(8192);
            // max size of a file of the file system
            res.resok.maxfilesize = new size3(new uint64(4294967296L));
            // server time granularity -- accurate only to nearest second
            nfstime3 time = new nfstime3();
            time.seconds = new uint32(1);
            time.nseconds = new uint32(0);
            res.resok.time_delta = time;

            // obj_attributes
            res.resok.obj_attributes = new post_op_attr();
            res.resok.obj_attributes.attributes_follow = true;
            res.resok.obj_attributes.attributes = new fattr3();

            HimeraNfsUtils.fill_attributes(inode.stat(), res.resok.obj_attributes.attributes);

            res.resok.properties = new uint32(nfs3_prot.FSF3_CANSETTIME |
                    nfs3_prot.FSF3_HOMOGENEOUS |
                    nfs3_prot.FSF3_LINK |
                    nfs3_prot.FSF3_SYMLINK);

        } catch (ChimeraFsException e) {
            res.resfail = new FSINFO3resfail();
            res.resfail.obj_attributes = defaultPostOpAttr();
            res.status = nfsstat3.NFS3ERR_IO;
            _log.log(Level.SEVERE, "FSINFO", e);
        } catch (Exception e) {
            _log.log(Level.SEVERE, "FSINFO", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new FSINFO3resfail();
            res.resfail.obj_attributes = defaultPostOpAttr();
        }

        if (res.status != nfsstat3.NFS3_OK) {
            _log.log(Level.SEVERE, "FSinfo failed : {0}", HimeraNfsUtils.nfsErr2String(res.status));
        }


        return res;
    }

    @Override
    public FSSTAT3res NFSPROC3_FSSTAT_3(RpcCall call$, FSSTAT3args arg1) {
        // org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$, _fs);
        // JSyslog.log(JSyslog.LOG_DEBUG, "NFS Request FSSTAT3 uid: {0}", user);

        FSSTAT3res res = new FSSTAT3res();

        try {

            res.status = nfsstat3.NFS3_OK;
            res.resok = new FSSTAT3resok();

            FsStat fsStat = _fs.getFsStat();
            res.resok.tbytes = new size3(new uint64(fsStat.getTotalSpace()));
            res.resok.fbytes = new size3(new uint64(fsStat.getTotalSpace() - fsStat.getUsedSpace()));
            res.resok.abytes = new size3(new uint64(fsStat.getTotalSpace() - fsStat.getUsedSpace()));

            res.resok.tfiles = new size3(new uint64(fsStat.getTotalFiles()));
            res.resok.ffiles = new size3(new uint64(fsStat.getTotalFiles() - fsStat.getUsedFiles()));
            res.resok.afiles = new size3(new uint64(fsStat.getTotalFiles() - fsStat.getUsedFiles()));

            res.resok.invarsec = new uint32(0);

            res.resok.obj_attributes = new post_op_attr();
            res.resok.obj_attributes.attributes_follow = true;
            res.resok.obj_attributes.attributes = new fattr3();

            FsInode inode = NFSHandle.toFsInode(_fs, arg1.fsroot.data);

            HimeraNfsUtils.fill_attributes(inode.stat(), res.resok.obj_attributes.attributes);

        } catch (ChimeraFsException e) {
            _log.log(Level.SEVERE, "FSSTAT", e);
            res.status = nfsstat3.NFS3ERR_IO;
            res.resfail = new FSSTAT3resfail();
            res.resfail.obj_attributes = defaultPostOpAttr();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "FSSTAT", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new FSSTAT3resfail();
            res.resfail.obj_attributes = defaultPostOpAttr();
        }

        if (res.status != nfsstat3.NFS3_OK) {
            _log.log(Level.SEVERE, "FSSTAT ({0}) failed: {1}",
                    new Object[]{new String(arg1.fsroot.data),
                        HimeraNfsUtils.nfsErr2String(res.status)
                    });
        }
        return res;

    }

    @Override
    public GETATTR3res NFSPROC3_GETATTR_3(RpcCall call$, GETATTR3args arg1) {
        org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
        _log.log(Level.FINEST, "NFS Request GETTATTR3 uid: {0}", user);

        GETATTR3res res = new GETATTR3res();

        FsInode inode = NFSHandle.toFsInode(_fs, arg1.object.data);
        _log.log(Level.FINEST, "NFS Request GETATTR for inode: {0}", inode.toString());

        try {
            res.status = nfsstat3.NFS3_OK;
            res.resok = new GETATTR3resok();

            res.resok.obj_attributes = new fattr3();
            HimeraNfsUtils.fill_attributes(inode.stat(), res.resok.obj_attributes);

        } catch (FileNotFoundHimeraFsException fnf) {
            res.status = nfsstat3.NFS3ERR_NOENT;
        } catch (ChimeraFsException e) {
            _log.log(Level.SEVERE, "GETATTR", e);
            res.status = nfsstat3.NFS3ERR_IO;
        } catch (Exception e) {
            _log.log(Level.SEVERE, "GETATTR", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
        }

        if (res.status != nfsstat3.NFS3_OK) {
            _log.log(Level.SEVERE, "Getattr failed : {0}", HimeraNfsUtils.nfsErr2String(res.status));
        }

        return res;
    }

    @Override
    public LINK3res NFSPROC3_LINK_3(RpcCall call$, LINK3args arg1) {
        org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
        _log.log(Level.FINEST, "NFS Request LINK3 uid: {0}", user);


        LINK3res res = new LINK3res();

        FsInode parent = NFSHandle.toFsInode(_fs, arg1.link.dir.data);
        String name = arg1.link.name.value;

        FsInode hlink = NFSHandle.toFsInode(_fs, arg1.file.data);

        try {


            FsInode inode = null;
            boolean exists = true;
            try {
                inode = _fs.inodeOf(parent, name);
            } catch (FileNotFoundHimeraFsException hfe) {
                exists = false;
            }

            if (exists) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_EXIST, "File " + name + " already exist.");
            }

            Stat parentStat = parent.statCache();
            UnixAcl acl = new UnixAcl(parentStat.getUid(), parentStat.getGid(), parentStat.getMode() & 0777);
            if (!_permissionHandler.isAllowed(acl, user, AclHandler.ACL_INSERT)) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_ACCES, "Permission denied.");
            }

            _fs.createHLink(parent, hlink, name);

            res.resok = new LINK3resok();
            res.resok.file_attributes = new post_op_attr();
            res.resok.file_attributes.attributes_follow = true;
            res.resok.file_attributes.attributes = new fattr3();

            HimeraNfsUtils.fill_attributes(hlink.stat(), res.resok.file_attributes.attributes);

            res.resok.linkdir_wcc = new wcc_data();
            res.resok.linkdir_wcc.after = new post_op_attr();
            res.resok.linkdir_wcc.after.attributes_follow = true;
            res.resok.linkdir_wcc.after.attributes = new fattr3();

            // fake answer
            parentStat.setNlink(parentStat.getNlink() + 1);
            parentStat.setMTime(System.currentTimeMillis());
            HimeraNfsUtils.fill_attributes(parentStat, res.resok.linkdir_wcc.after.attributes);

            res.resok.linkdir_wcc.before = new pre_op_attr();
            res.resok.linkdir_wcc.before.attributes_follow = false;

            res.status = nfsstat3.NFS3_OK;

        } catch (ChimeraNFSException hne) {
            res.status = hne.getStatus();
            res.resfail = new LINK3resfail();
            res.resfail.file_attributes = defaultPostOpAttr();
            res.resfail.linkdir_wcc = defaultWccData();
        } catch (ChimeraFsException e) {
            _log.log(Level.SEVERE, "LINK", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail.file_attributes = defaultPostOpAttr();
            res.resfail.linkdir_wcc = defaultWccData();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "LINK", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail.file_attributes = defaultPostOpAttr();
            res.resfail.linkdir_wcc = defaultWccData();
        }

        return res;
    }

    @Override
    public LOOKUP3res NFSPROC3_LOOKUP_3(RpcCall call$, LOOKUP3args arg1) {

        LOOKUP3res res = new LOOKUP3res();

        FsInode parent = NFSHandle.toFsInode(_fs, arg1.what.dir.data);
        String name = arg1.what.name.value;

        FsInode inode = null;

        try {

            try {
                inode = _fs.inodeOf(parent, name);
            } catch (ChimeraFsException hfse) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_NOENT, "Path do not exist.");
            }

            res.status = nfsstat3.NFS3_OK;
            res.resok = new LOOKUP3resok();

            nfs_fh3 fh3 = new nfs_fh3();
            fh3.data = (inode.toFullString()).getBytes();
            res.resok.object = fh3;

            res.resok.obj_attributes = new post_op_attr();
            res.resok.obj_attributes.attributes_follow = true;
            res.resok.obj_attributes.attributes = new fattr3();

            HimeraNfsUtils.fill_attributes(inode.stat(), res.resok.obj_attributes.attributes);

            res.resok.dir_attributes = new post_op_attr();
            res.resok.dir_attributes.attributes_follow = true;
            res.resok.dir_attributes.attributes = new fattr3();

            HimeraNfsUtils.fill_attributes(parent.stat(), res.resok.dir_attributes.attributes);

        } catch (ChimeraNFSException hne) {
            res.status = hne.getStatus();
            res.resfail = new LOOKUP3resfail();
            res.resfail.dir_attributes = defaultPostOpAttr();
        } catch (ChimeraFsException e) {
            _log.log(Level.SEVERE, "LOOKUP", e);
            res.status = nfsstat3.NFS3ERR_IO;
            res.resfail = new LOOKUP3resfail();
            res.resfail.dir_attributes = defaultPostOpAttr();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "LOOKUP", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new LOOKUP3resfail();
            res.resfail.dir_attributes = defaultPostOpAttr();
        }

        _log.log(Level.FINEST, "LOOKUP for {0} in {1}: {2}",
                new Object[]{name, parent.toString(), inode});

        if ((res.status != nfsstat3.NFS3_OK) && (res.status != nfsstat3.NFS3ERR_NOENT)) {
            _log.log(Level.SEVERE, "lookup {0}", HimeraNfsUtils.nfsErr2String(res.status));
        }
        return res;

    }

    @Override
    public MKDIR3res NFSPROC3_MKDIR_3(RpcCall call$, MKDIR3args arg1) {
        org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
        _log.log(Level.FINEST, "NFS Request MKDIR3 uid: {0}", user);

        MKDIR3res res = new MKDIR3res();

        FsInode parent = NFSHandle.toFsInode(_fs, arg1.where.dir.data);

        String name = arg1.where.name.value;
        sattr3 attr = arg1.attributes;

        try {

            Stat parentStat = parent.statCache();

            UnixAcl acl = new UnixAcl(parentStat.getUid(), parentStat.getGid(), parentStat.getMode() & 0777);
            if (!_permissionHandler.isAllowed(acl, user, AclHandler.ACL_INSERT)) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_ACCES, "Permission denied.");
            }

            FsInode inode = null;
            try {
                inode = _fs.mkdir(parent, name);
            } catch (ChimeraFsException hfe) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_EXIST, "Directory already exist.");
            }

            if (attr != null) {
                attr.gid.set_it = true;
                attr.gid.gid = new gid3(new uint32(user.getGID()));

                attr.uid.set_it = true;
                attr.uid.uid = new uid3(new uint32(user.getUID()));

                attr.mode.mode.value.value |= UnixPermission.S_IFDIR;

                HimeraNfsUtils.set_sattr(inode, attr);
            }

            res.resok = new MKDIR3resok();
            res.resok.obj = new post_op_fh3();
            res.resok.obj.handle_follows = true;
            res.resok.obj.handle = new nfs_fh3();
            res.resok.obj.handle.data = inode.toFullString().getBytes();

            res.resok.obj_attributes = new post_op_attr();
            res.resok.obj_attributes.attributes_follow = true;
            res.resok.obj_attributes.attributes = new fattr3();

            HimeraNfsUtils.fill_attributes(inode.stat(), res.resok.obj_attributes.attributes);

            res.resok.dir_wcc = new wcc_data();
            res.resok.dir_wcc.after = new post_op_attr();
            res.resok.dir_wcc.after.attributes_follow = true;
            res.resok.dir_wcc.after.attributes = new fattr3();

            // fake answer
            parentStat.setNlink(parentStat.getNlink() + 1);
            parentStat.setMTime(System.currentTimeMillis());
            HimeraNfsUtils.fill_attributes(parentStat, res.resok.dir_wcc.after.attributes);

            res.resok.dir_wcc.before = new pre_op_attr();
            res.resok.dir_wcc.before.attributes_follow = false;

            res.status = nfsstat3.NFS3_OK;

        } catch (ChimeraNFSException hne) {
            res.resfail = new MKDIR3resfail();
            res.resfail.dir_wcc = defaultWccData();
            res.status = hne.getStatus();
        } catch (ChimeraFsException e) {
            _log.log(Level.SEVERE, "MKDIR", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new MKDIR3resfail();
            res.resfail.dir_wcc = defaultWccData();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "MKDIR", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new MKDIR3resfail();
            res.resfail.dir_wcc = defaultWccData();
        }

        return res;
    }

    @Override
    public MKNOD3res NFSPROC3_MKNOD_3(RpcCall call$, MKNOD3args arg1) {

        MKNOD3res res = new MKNOD3res();
        res.status = nfsstat3.NFS3ERR_NOTSUPP;
        res.resfail = new MKNOD3resfail();
        res.resfail.dir_wcc = defaultWccData();
        return res;

    }

    @Override
    public void NFSPROC3_NULL_3(RpcCall call$) {
    }

    @Override
    public PATHCONF3res NFSPROC3_PATHCONF_3(RpcCall call$, PATHCONF3args arg1) {

        PATHCONF3res res = new PATHCONF3res();

        res.resok = new PATHCONF3resok();
        res.resok.case_insensitive = false;
        res.resok.case_preserving = true;
        res.resok.chown_restricted = false;
        res.resok.no_trunc = true;
        res.resok.linkmax = new uint32(512);
        res.resok.name_max = new uint32(256);

        res.resok.obj_attributes = new post_op_attr();
        res.resok.obj_attributes.attributes_follow = false;

        res.status = nfsstat3.NFS3_OK;

        return res;

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
    public READDIRPLUS3res NFSPROC3_READDIRPLUS_3(RpcCall call$, READDIRPLUS3args arg1) {
        org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
        _log.log(Level.FINEST, "NFS Request READDIRPLUS3 uid: {0}", user);

        READDIRPLUS3res res = new READDIRPLUS3res();

        try {

            FsInode dir = NFSHandle.toFsInode(_fs, arg1.dir.data);

            Stat dirStat = dir.statCache();
            UnixAcl acl = new UnixAcl(dirStat.getUid(), dirStat.getGid(), dirStat.getMode() & 0777);
            if (!_permissionHandler.isAllowed(acl, user, AclHandler.ACL_LOOKUP)) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_ACCES, "Permission denied.");
            }


            if (!dir.exists()) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_NOENT, "Path Do not exist.");
            }

            if (!dir.isDirectory()) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_NOTDIR, "Path is not a directory.");
            }

            long startValue = arg1.cookie.value.value;
            cookieverf3 cookieverf = null;

            List<HimeraDirectoryEntry> dirList = null;

            /*
             * use cache only in subsequent readdirs
             */
            if (startValue != 0) {
                ++startValue;

                cookieverf = arg1.cookieverf;
                dirList = _dlCacheFull.get(cookieverf);
                if (dirList == null) {
                    _log.log(Level.FINEST, "updating dirlist from db");
                    dirList = DirectoryStreamHelper.listOf(dir);
                } else {
                    _log.log(Level.FINEST, "using dirlist from cache");
                }

            } else {
                byte[] verifier = new byte[nfs3_prot.NFS3_COOKIEVERFSIZE];
                _random.nextBytes(verifier);
                cookieverf = new cookieverf3(verifier);

                dirList = DirectoryStreamHelper.listOf(dir);
            }

            if (startValue > dirList.size()) {
                res.status = nfsstat3.NFS3ERR_BAD_COOKIE;
                res.resfail = new READDIRPLUS3resfail();
                res.resfail.dir_attributes = new post_op_attr();
                res.resfail.dir_attributes.attributes_follow = false;
                return res;
            }


            res.status = nfsstat3.NFS3_OK;
            res.resok = new READDIRPLUS3resok();
            res.resok.reply = new dirlistplus3();
            res.resok.dir_attributes = new post_op_attr();
            res.resok.dir_attributes.attributes_follow = true;
            res.resok.dir_attributes.attributes = new fattr3();
            HimeraNfsUtils.fill_attributes(dir.statCache(), res.resok.dir_attributes.attributes);

            res.resok.cookieverf = cookieverf;

            int currcount = READDIRPLUS3RESOK_SIZE;
            int dircount = 0;
            res.resok.reply.entries = new entryplus3();
            entryplus3 currentEntry = res.resok.reply.entries;
            entryplus3 lastEntry = null;

            for (long i = startValue; i < dirList.size(); i++) {

                HimeraDirectoryEntry le = dirList.get((int) i);
                String name = le.getName();

                FsInode ef = le.getInode();

                currentEntry.fileid = new fileid3(new uint64(ef.id()));
                currentEntry.name = new filename3(name);
                currentEntry.cookie = new cookie3(new uint64(i));
                currentEntry.name_handle = new post_op_fh3();
                currentEntry.name_handle.handle_follows = true;
                currentEntry.name_handle.handle = new nfs_fh3();
                currentEntry.name_handle.handle.data = ef.toFullString().getBytes();
                currentEntry.name_attributes = new post_op_attr();
                currentEntry.name_attributes.attributes_follow = true;
                currentEntry.name_attributes.attributes = new fattr3();
                HimeraNfsUtils.fill_attributes(le.getStat(), currentEntry.name_attributes.attributes);
                currentEntry.nextentry = null;


                // check if writing this entry exceeds the count limit
                int newSize = ENTRYPLUS3_SIZE + name.length() + currentEntry.name_handle.handle.data.length;
                int newDirSize = name.length();
                if ((currcount + newSize > arg1.maxcount.value.value) || (dircount + newDirSize > arg1.dircount.value.value)) {

                    // cache result
                    _dlCacheFull.add(cookieverf, dirList, dirList.size() / 100);

                    res.resok.reply.eof = false;
                    lastEntry.nextentry = null;

                    _log.log(Level.FINEST, "Sending {0} entries ( {1} bytes from {2}, dircount = {3} from {4} ) cookie = {5} total {6}",
                            new Object[]{(i - startValue), currcount,
                                arg1.maxcount.value.value, dircount,
                                arg1.dircount.value.value,
                                startValue, dirList.size()
                            });
                    return res;
                }
                dircount += newDirSize;
                currcount += newSize;

                if (i + 1 < dirList.size()) {
                    lastEntry = currentEntry;
                    currentEntry.nextentry = new entryplus3();
                    currentEntry = currentEntry.nextentry;
                }
            }

            res.resok.reply.eof = true;
            // all entries sent to client, remove cache
            _dlCacheFull.remove(cookieverf);
            _log.log(Level.FINEST, "Cleaning cache");

        } catch (ChimeraNFSException hne) {
            res.resfail = new READDIRPLUS3resfail();
            res.resfail.dir_attributes = defaultPostOpAttr();
            res.status = hne.getStatus();
        } catch (ChimeraFsException e) {
            _log.log(Level.SEVERE, "READDIRPLUS3", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new READDIRPLUS3resfail();
            res.resfail.dir_attributes = defaultPostOpAttr();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "READDIRPLUS3", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new READDIRPLUS3resfail();
            res.resfail.dir_attributes = defaultPostOpAttr();
        }

        if (res.status != nfsstat3.NFS3_OK) {
            _log.log(Level.SEVERE, "READDIRPLUS3 status - {0}", HimeraNfsUtils.nfsErr2String(res.status));
        }
        return res;
    }

    @Override
    public READDIR3res NFSPROC3_READDIR_3(RpcCall call$, READDIR3args arg1) {
        org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
        _log.log(Level.FINEST, "NFS Request READDIR3 uid: {0}", user);

        READDIR3res res = new READDIR3res();

        try {

            FsInode dir = NFSHandle.toFsInode(_fs, arg1.dir.data);

            Stat dirStat = dir.statCache();
            UnixAcl acl = new UnixAcl(dirStat.getUid(), dirStat.getGid(), dirStat.getMode() & 0777);
            if (!_permissionHandler.isAllowed(acl, user, AclHandler.ACL_LOOKUP)) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_ACCES, "Permission denied.");
            }

            if (!dir.exists()) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_NOENT, "Path Do not exist.");
            }

            if (!dir.isDirectory()) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_NOTDIR, "Path is not a directory.");
            }


            long startValue = arg1.cookie.value.value;
            List<HimeraDirectoryEntry> dirList = null;
            cookieverf3 cookieverf;

            if (startValue != 0) {
                ++startValue;
                cookieverf = arg1.cookieverf;
                // try cache first
                dirList = _dlCacheFull.get(cookieverf);
                if (dirList == null) {
                    dirList = DirectoryStreamHelper.listOf(dir);
                }

            } else {
                byte[] verifier = new byte[nfs3_prot.NFS3_COOKIEVERFSIZE];
                _random.nextBytes(verifier);
                cookieverf = new cookieverf3(verifier);

                dirList = DirectoryStreamHelper.listOf(dir);
            }

            if (startValue > dirList.size()) {
                res.status = nfsstat3.NFS3ERR_BAD_COOKIE;
                res.resfail = new READDIR3resfail();
                res.resfail.dir_attributes = new post_op_attr();
                res.resfail.dir_attributes.attributes_follow = false;
                return res;
            }

            res.status = nfsstat3.NFS3_OK;
            res.resok = new READDIR3resok();
            res.resok.reply = new dirlist3();
            res.resok.dir_attributes = new post_op_attr();
            res.resok.dir_attributes.attributes_follow = true;
            res.resok.dir_attributes.attributes = new fattr3();
            HimeraNfsUtils.fill_attributes(dir.stat(), res.resok.dir_attributes.attributes);

            res.resok.cookieverf = cookieverf;

            int currcount = READDIR3RESOK_SIZE;
            res.resok.reply.entries = new entry3();
            entry3 currentEntry = res.resok.reply.entries;
            entry3 lastEntry = null;

            for (long i = startValue; i < dirList.size(); i++) {

                HimeraDirectoryEntry le = dirList.get((int) i);
                String name = le.getName();
                FsInode ef = le.getInode();

                currentEntry.fileid = new fileid3(new uint64(ef.id()));
                currentEntry.name = new filename3(name);
                currentEntry.cookie = new cookie3(new uint64(i));
                currentEntry.nextentry = null;

                // check if writing this entry exceeds the count limit
                int newSize = ENTRY3_SIZE + name.length();
                if (currcount + newSize > arg1.count.value.value) {
                    lastEntry.nextentry = null;

                    // cache result
                    _dlCacheFull.add(cookieverf, dirList, dirList.size() / 200);

                    res.resok.reply.eof = false;

                    _log.log(Level.FINEST, "Sending {0} entries ( {1} bytes from {2}) cookie = {3} total {4}",
                            new Object[]{(i - startValue), currcount,
                                arg1.count.value.value,
                                startValue, dirList.size()
                            });

                    return res;
                }
                currcount += newSize;

                if (i + 1 < dirList.size()) {
                    lastEntry = currentEntry;
                    currentEntry.nextentry = new entry3();
                    currentEntry = currentEntry.nextentry;
                }
            }

            res.resok.reply.eof = true;
            // all entries sent to client, remove cache
            _dlCacheFull.remove(cookieverf);

        } catch (ChimeraNFSException hne) {
            res.resfail = new READDIR3resfail();
            res.resfail.dir_attributes = defaultPostOpAttr();
            res.status = hne.getStatus();
        } catch (ChimeraFsException e) {
            _log.log(Level.SEVERE, "READDIR", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new READDIR3resfail();
            res.resfail.dir_attributes = defaultPostOpAttr();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "READDIR", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new READDIR3resfail();
            res.resfail.dir_attributes = defaultPostOpAttr();
        }

        if (res.status != nfsstat3.NFS3_OK) {
            _log.log(Level.SEVERE, "READDIR status - {0}", HimeraNfsUtils.nfsErr2String(res.status));
        }
        return res;

    }

    @Override
    public READLINK3res NFSPROC3_READLINK_3(RpcCall call$, READLINK3args arg1) {

        READLINK3res res = new READLINK3res();


        try {
            FsInode inode = NFSHandle.toFsInode(_fs, arg1.symlink.data);

            res.resok = new READLINK3resok();
            res.resok.data = new nfspath3(new String(inode.readlink()));
            res.resok.symlink_attributes = new post_op_attr();

            res.resok.symlink_attributes.attributes_follow = true;
            res.resok.symlink_attributes.attributes = new fattr3();
            HimeraNfsUtils.fill_attributes(_fs.stat(inode), res.resok.symlink_attributes.attributes);

            res.status = nfsstat3.NFS3_OK;

        } catch (ChimeraFsException e) {
            _log.log(Level.SEVERE, "READLINK", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new READLINK3resfail();
            res.resfail.symlink_attributes = defaultPostOpAttr();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "READLINK", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new READLINK3resfail();
            res.resfail.symlink_attributes = defaultPostOpAttr();
        }

        return res;

    }

    @Override
    public READ3res NFSPROC3_READ_3(RpcCall call$, READ3args arg1) {

        READ3res res = new READ3res();

        FsInode inode = NFSHandle.toFsInode(_fs, arg1.file.data);
        long offset = arg1.offset.value.value;
        int count = arg1.count.value.value;

        try {

            org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                    _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
            Stat inodeStat = inode.statCache();
            UnixAcl fileAcl = new UnixAcl(inodeStat.getUid(), inodeStat.getGid(), inodeStat.getMode() & 0777);
            if (!_permissionHandler.isAllowed(fileAcl, user, AclHandler.ACL_READ)) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_ACCES, "Permission denied.");
            }

            res.resok = new READ3resok();
            res.resok.data = new byte[count];

            res.resok.count = new count3();
            res.resok.count.value = new uint32();

            byte[] b = new byte[count];
            res.resok.count.value.value = inode.read(offset, b, 0, count);
            if (res.resok.count.value.value < 0) {
                throw new IOHimeraFsException("IO not allowed");
            }
            res.resok.data = new byte[res.resok.count.value.value];


            System.arraycopy(b, 0, res.resok.data, 0, res.resok.count.value.value);

            if (res.resok.count.value.value + offset == inodeStat.getSize()) {
                res.resok.eof = true;
            }

            res.resok.file_attributes = new post_op_attr();
            res.resok.file_attributes.attributes_follow = true;
            res.resok.file_attributes.attributes = new fattr3();
            HimeraNfsUtils.fill_attributes(inode.stat(), res.resok.file_attributes.attributes);
        } catch (ChimeraNFSException hne) {
            res.status = hne.getStatus();
            res.resfail = new READ3resfail();
            res.resfail.file_attributes = defaultPostOpAttr();
        } catch (IOHimeraFsException hfe) {
            res.status = nfsstat3.NFS3ERR_IO;
            res.resfail = new READ3resfail();
            res.resfail.file_attributes = defaultPostOpAttr();
        } catch (ChimeraFsException e) {
            res.status = nfsstat3.NFS3ERR_IO;
            res.resfail = new READ3resfail();
            res.resfail.file_attributes = defaultPostOpAttr();
            _log.log(Level.SEVERE, "READ", e);
        } catch (Exception e) {
            _log.log(Level.SEVERE, "READ", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new READ3resfail();
            res.resfail.file_attributes = defaultPostOpAttr();
        }

        return res;

    }

    @Override
    public REMOVE3res NFSPROC3_REMOVE_3(RpcCall call$, REMOVE3args arg1) {
        org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
        _log.log(Level.FINEST, "NFS Request REMOVE3 uid: {0}", user);


        REMOVE3res res = new REMOVE3res();

        FsInode parent = NFSHandle.toFsInode(_fs, arg1.object.dir.data);

        String name = arg1.object.name.value;

        try {

            Stat inodeStat = null;
            Stat parentStat = null;
            try {
                FsInode inode = _fs.inodeOf(parent, name);
                inodeStat = inode.statCache();
                parentStat = parent.statCache();
            } catch (ChimeraFsException hfe) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_NOENT, "Path do not exist.");
            }

            UnixAcl parentAcl = new UnixAcl(parentStat.getUid(), parentStat.getGid(), parentStat.getMode() & 0777);
            UnixAcl fileAcl = new UnixAcl(inodeStat.getUid(), inodeStat.getGid(), inodeStat.getMode() & 0777);
            if (!(_permissionHandler.isAllowed(fileAcl, user, AclHandler.ACL_DELETE) ||
                    _permissionHandler.isAllowed(parentAcl, user, AclHandler.ACL_DELETE))) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_ACCES, "Permission denied.");
            }

            parent.remove(name);

            res.resok = new REMOVE3resok();
            res.status = nfsstat3.NFS3_OK;

            res.resok.dir_wcc = new wcc_data();

            res.resok.dir_wcc.before = new pre_op_attr();
            res.resok.dir_wcc.before.attributes_follow = true;
            res.resok.dir_wcc.before.attributes = new wcc_attr();
            HimeraNfsUtils.fill_attributes(parentStat, res.resok.dir_wcc.before.attributes);


            // correct parent modification time and nlink counter
            parentStat.setMTime(System.currentTimeMillis());
            parentStat.setNlink(parentStat.getNlink() - 1);

            res.resok.dir_wcc.after = new post_op_attr();
            res.resok.dir_wcc.after.attributes_follow = true;
            res.resok.dir_wcc.after.attributes = new fattr3();
            HimeraNfsUtils.fill_attributes(parentStat, res.resok.dir_wcc.after.attributes);


        } catch (ChimeraNFSException hne) {
            res.resfail = new REMOVE3resfail();
            res.resfail.dir_wcc = defaultWccData();
            res.status = hne.getStatus();
        } catch (ChimeraFsException e) {
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new REMOVE3resfail();
            res.resfail.dir_wcc = defaultWccData();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "REMOVE", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new REMOVE3resfail();
            res.resfail.dir_wcc = defaultWccData();
        }

        return res;

    }

    @Override
    public RENAME3res NFSPROC3_RENAME_3(RpcCall call$, RENAME3args arg1) {
        org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
        _log.log(Level.FINEST, "NFS Request RENAME3 uid: {0}", user);

        RENAME3res res = new RENAME3res();

        FsInode from = NFSHandle.toFsInode(_fs, arg1.from.dir.data);
        String file1 = arg1.from.name.value;

        FsInode to = NFSHandle.toFsInode(_fs, arg1.to.dir.data);
        String file2 = arg1.to.name.value;


        try {

            _fs.move(from, file1, to, file2);

            res.resok = new RENAME3resok();

            res.resok.fromdir_wcc = new wcc_data();
            res.resok.fromdir_wcc.after = new post_op_attr();
            res.resok.fromdir_wcc.after.attributes_follow = true;
            res.resok.fromdir_wcc.after.attributes = new fattr3();
            HimeraNfsUtils.fill_attributes(from.stat(), res.resok.fromdir_wcc.after.attributes);

            res.resok.fromdir_wcc.before = new pre_op_attr();
            res.resok.fromdir_wcc.before.attributes_follow = false;

            res.resok.todir_wcc = new wcc_data();
            res.resok.todir_wcc.after = new post_op_attr();
            res.resok.todir_wcc.after.attributes_follow = true;
            res.resok.todir_wcc.after.attributes = new fattr3();
            HimeraNfsUtils.fill_attributes(to.stat(), res.resok.todir_wcc.after.attributes);

            res.resok.todir_wcc.before = new pre_op_attr();
            res.resok.todir_wcc.before.attributes_follow = false;

            res.status = nfsstat3.NFS3_OK;
        } catch (ChimeraFsException e) {
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new RENAME3resfail();
            res.resfail.fromdir_wcc = defaultWccData();
            res.resfail.todir_wcc = defaultWccData();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "RENAME", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new RENAME3resfail();
            res.resfail.fromdir_wcc = defaultWccData();
            res.resfail.todir_wcc = defaultWccData();
        }

        return res;

    }

    @Override
    public RMDIR3res NFSPROC3_RMDIR_3(RpcCall call$, RMDIR3args arg1) {
        org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
        _log.log(Level.FINEST, "NFS Request RMDIR3 uid: {0}", user);


        RMDIR3res res = new RMDIR3res();

        FsInode parent = NFSHandle.toFsInode(_fs, arg1.object.dir.data);
        String file = arg1.object.name.value;

        try {

            FsInode inode = _fs.inodeOf(parent, file);
            Stat inodeStat = inode.statCache();
            Stat parentStat = parent.statCache();


            UnixAcl parentAcl = new UnixAcl(parentStat.getUid(), parentStat.getGid(), parentStat.getMode() & 0777);
            UnixAcl fileAcl = new UnixAcl(inodeStat.getUid(), inodeStat.getGid(), inodeStat.getMode() & 0777);
            if (!(_permissionHandler.isAllowed(fileAcl, user, AclHandler.ACL_DELETE) ||
                    _permissionHandler.isAllowed(parentAcl, user, AclHandler.ACL_DELETE))) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_ACCES, "Permission denied.");
            }

            if (!parent.remove(file)) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_NOTEMPTY, "Directory is not empty.");
            }

            res.resok = new RMDIR3resok();
            res.status = nfsstat3.NFS3_OK;

            res.resok.dir_wcc = new wcc_data();
            res.resok.dir_wcc.after = new post_op_attr();

            res.resok.dir_wcc.before = new pre_op_attr();
            res.resok.dir_wcc.before.attributes_follow = true;
            res.resok.dir_wcc.before.attributes = new wcc_attr();
            HimeraNfsUtils.fill_attributes(parentStat, res.resok.dir_wcc.before.attributes);

            res.resok.dir_wcc.after.attributes_follow = true;
            res.resok.dir_wcc.after.attributes = new fattr3();

            parentStat.setMTime(System.currentTimeMillis());
            parentStat.setNlink(parentStat.getNlink() - 1);

            HimeraNfsUtils.fill_attributes(parentStat, res.resok.dir_wcc.after.attributes);

        } catch (ChimeraNFSException hne) {
            res.resfail = new RMDIR3resfail();
            res.resfail.dir_wcc = defaultWccData();
            res.status = hne.getStatus();
        } catch (ChimeraFsException e) {
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new RMDIR3resfail();
            res.resfail.dir_wcc = defaultWccData();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "RMDIR", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new RMDIR3resfail();
            res.resfail.dir_wcc = defaultWccData();
        }

        return res;
    }

    @Override
    public SETATTR3res NFSPROC3_SETATTR_3(RpcCall call$, SETATTR3args arg1) {
        org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
        _log.log(Level.FINEST, "NFS Request SETATTR3 uid: {0}", user);


        SETATTR3res res = new SETATTR3res();

        FsInode inode = NFSHandle.toFsInode(_fs, arg1.object.data);
        sattr3 newAttr = arg1.new_attributes;

        try {

            Stat stat = null;
            try {
                stat = inode.statCache();
            } catch (ChimeraFsException hfe) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_NOENT, "Path do not exist.");
            }

            UnixAcl acl = new UnixAcl(stat.getUid(), stat.getGid(), stat.getMode() & 0777);
            if (!_permissionHandler.isAllowed(acl, user, AclHandler.ACL_ADMINISTER)) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_ACCES, "Permission denied.");
            }

            HimeraNfsUtils.set_sattr(inode, newAttr);
            res.resok = new SETATTR3resok();
            res.resok.obj_wcc = new wcc_data();
            res.resok.obj_wcc.after = new post_op_attr();
            res.resok.obj_wcc.after.attributes_follow = true;
            res.resok.obj_wcc.after.attributes = new fattr3();
            HimeraNfsUtils.fill_attributes(inode.statCache(), res.resok.obj_wcc.after.attributes);

            res.resok.obj_wcc.before = new pre_op_attr();
            res.resok.obj_wcc.before.attributes_follow = false;

            res.status = nfsstat3.NFS3_OK;
        } catch (ChimeraNFSException hne) {
            res.status = hne.getStatus();
            res.resfail = new SETATTR3resfail();
            res.resfail.obj_wcc = defaultWccData();
        } catch (ChimeraFsException e) {
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new SETATTR3resfail();
            res.resfail.obj_wcc = defaultWccData();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "SETATTR", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new SETATTR3resfail();
            res.resfail.obj_wcc = defaultWccData();
        }

        return res;

    }

    @Override
    public SYMLINK3res NFSPROC3_SYMLINK_3(RpcCall call$, SYMLINK3args arg1) {
        org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
        _log.log(Level.FINEST, "NFS Request SYMLINK3 uid: {0}", user);

        SYMLINK3res res = new SYMLINK3res();

        FsInode parent = NFSHandle.toFsInode(_fs, arg1.where.dir.data);
        String file = arg1.where.name.value;

        String link = arg1.symlink.symlink_data.value;
        sattr3 linkAttr = arg1.symlink.symlink_attributes;

        try {


            FsInode inode = null;
            boolean exists = true;
            try {
                inode = _fs.inodeOf(parent, file);
            } catch (FileNotFoundHimeraFsException hfe) {
                exists = false;
            }

            if (exists) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_EXIST, "File " + file + " already exist.");
            }

            Stat parentStat = parent.statCache();
            UnixAcl acl = new UnixAcl(parentStat.getUid(), parentStat.getGid(), parentStat.getMode() & 0777);
            if (!_permissionHandler.isAllowed(acl, user, AclHandler.ACL_INSERT)) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_ACCES, "Permission denied.");
            }

            inode = _fs.createLink(parent, file, link);

            HimeraNfsUtils.set_sattr(inode, linkAttr);

            res.resok = new SYMLINK3resok();

            res.resok.obj_attributes = new post_op_attr();
            res.resok.obj_attributes.attributes_follow = true;
            res.resok.obj_attributes.attributes = new fattr3();

            HimeraNfsUtils.fill_attributes(inode.statCache(), res.resok.obj_attributes.attributes);
            res.resok.obj = new post_op_fh3();
            res.resok.obj.handle_follows = true;
            res.resok.obj.handle = new nfs_fh3();
            res.resok.obj.handle.data = inode.toFullString().getBytes();

            res.resok.dir_wcc = new wcc_data();
            res.resok.dir_wcc.after = new post_op_attr();
            res.resok.dir_wcc.after.attributes_follow = true;
            res.resok.dir_wcc.after.attributes = new fattr3();

            // fake answer
            parentStat.setNlink(parentStat.getNlink() + 1);
            parentStat.setMTime(System.currentTimeMillis());

            HimeraNfsUtils.fill_attributes(parentStat, res.resok.dir_wcc.after.attributes);

            res.resok.dir_wcc.before = new pre_op_attr();
            res.resok.dir_wcc.before.attributes_follow = false;

            res.status = nfsstat3.NFS3_OK;

        } catch (ChimeraNFSException hne) {
            res.status = hne.getStatus();
            res.resfail = new SYMLINK3resfail();
            res.resfail.dir_wcc = defaultWccData();
        } catch (ChimeraFsException e) {
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new SYMLINK3resfail();
            res.resfail.dir_wcc = defaultWccData();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "SYMLINK", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new SYMLINK3resfail();
            res.resfail.dir_wcc = defaultWccData();
        }

        return res;

    }

    @Override
    public WRITE3res NFSPROC3_WRITE_3(RpcCall call$, WRITE3args arg1) {

        WRITE3res res = new WRITE3res();

        FsInode inode = NFSHandle.toFsInode(_fs, arg1.file.data);
        long offset = arg1.offset.value.value;
        int count = arg1.count.value.value;

        try {
            Stat inodeStat = inode.statCache();

            org.dcache.chimera.posix.UnixUser user = HimeraNfsUtils.remoteUser(call$,
                    _exports.isTrusted(call$.getTransport().getRemoteSocketAddress().getAddress()));
            UnixAcl fileAcl = new UnixAcl(inodeStat.getUid(), inodeStat.getGid(), inodeStat.getMode() & 0777);
            if (!_permissionHandler.isAllowed(fileAcl, user, AclHandler.ACL_WRITE)) {
                throw new ChimeraNFSException(nfsstat3.NFS3ERR_ACCES, "Permission denied.");
            }

            res.resok = new WRITE3resok();
            res.status = nfsstat3.NFS3_OK;

            int ret = inode.write(offset, arg1.data, 0, count);
            if (ret < 0) {
                throw new IOHimeraFsException("IO not allowed");
            }

            res.resok.count = new count3(new uint32(ret));
            res.resok.file_wcc = new wcc_data();
            res.resok.file_wcc.after = new post_op_attr();
            res.resok.file_wcc.after.attributes_follow = true;
            res.resok.file_wcc.after.attributes = new fattr3();

            HimeraNfsUtils.fill_attributes(inode.statCache(), res.resok.file_wcc.after.attributes);
            res.resok.file_wcc.before = new pre_op_attr();
            res.resok.file_wcc.before.attributes_follow = false;
            res.resok.committed = stable_how.FILE_SYNC;
            res.resok.verf = new writeverf3();
            res.resok.verf.value = new byte[nfs3_prot.NFS3_WRITEVERFSIZE];
        } catch (ChimeraNFSException hne) {
            res.status = hne.getStatus();
            res.resfail = new WRITE3resfail();
            res.resfail.file_wcc = defaultWccData();
        } catch (IOHimeraFsException hfe) {
            res.status = nfsstat3.NFS3ERR_IO;
            res.resfail = new WRITE3resfail();
            res.resfail.file_wcc = defaultWccData();
        } catch (ChimeraFsException e) {
            res.status = nfsstat3.NFS3ERR_IO;
            res.resfail = new WRITE3resfail();
            res.resfail.file_wcc = defaultWccData();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "WRITE", e);
            res.status = nfsstat3.NFS3ERR_SERVERFAULT;
            res.resfail = new WRITE3resfail();
            res.resfail.file_wcc = defaultWccData();
        }

        return res;

    }
}
