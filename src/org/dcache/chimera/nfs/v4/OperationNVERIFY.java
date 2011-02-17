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

import org.dcache.chimera.nfs.v4.xdr.nfsstat4;
import org.dcache.chimera.nfs.v4.xdr.fattr4;
import org.dcache.chimera.nfs.v4.xdr.nfs_argop4;
import org.dcache.chimera.nfs.v4.xdr.nfs_opnum4;
import org.dcache.chimera.nfs.v4.xdr.NVERIFY4res;
import org.dcache.chimera.nfs.ChimeraNFSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperationNVERIFY extends AbstractNFSv4Operation {

    private static final Logger _log = LoggerFactory.getLogger(OperationNVERIFY.class);

    OperationNVERIFY(nfs_argop4 args) {
        super(args, nfs_opnum4.OP_NVERIFY);
    }

    @Override
    public boolean process(CompoundContext context) {

        NVERIFY4res res = new NVERIFY4res();

        try {

            fattr4 currentAttr = OperationGETATTR.getAttributes(_args.opnverify.obj_attributes.attrmask, context.currentInode());

            res.status = nfsstat4.NFS4ERR_SAME;

            for (int i = 0; i < _args.opnverify.obj_attributes.attr_vals.value.length; i++) {

                if (_args.opnverify.obj_attributes.attr_vals.value[i] != currentAttr.attr_vals.value[i]) {
                    res.status = nfsstat4.NFS4_OK;
                    break;
                }
            }

            _log.debug("{} is !same = {}", context.currentInode(), res.status);
        } catch (ChimeraNFSException he) {
            res.status = he.getStatus();
        } catch (Exception e) {
            _log.error("NVERIFY :", e);
            res.status = nfsstat4.NFS4ERR_SERVERFAULT;
        }

        _result.opnverify = res;

        context.processedOperations().add(_result);
        return res.status == nfsstat4.NFS4_OK;

    }
}
