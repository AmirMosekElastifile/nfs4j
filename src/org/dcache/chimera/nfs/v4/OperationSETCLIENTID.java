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

import java.net.Inet6Address;
import org.dcache.chimera.nfs.nfsstat;
import org.dcache.chimera.nfs.v4.xdr.verifier4;
import org.dcache.chimera.nfs.v4.xdr.uint64_t;
import org.dcache.chimera.nfs.v4.xdr.nfs_argop4;
import org.dcache.chimera.nfs.v4.xdr.clientid4;
import org.dcache.chimera.nfs.v4.xdr.nfs_opnum4;
import org.dcache.chimera.nfs.v4.xdr.SETCLIENTID4resok;
import org.dcache.chimera.nfs.v4.xdr.SETCLIENTID4res;
import org.dcache.chimera.nfs.ChimeraNFSException;
import org.dcache.chimera.nfs.v4.xdr.clientaddr4;
import org.dcache.chimera.nfs.v4.xdr.netaddr4;
import org.dcache.chimera.nfs.v4.xdr.nfs_resop4;
import org.dcache.utils.net.InetSocketAddresses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperationSETCLIENTID extends AbstractNFSv4Operation {

    private static final Logger _log = LoggerFactory.getLogger(OperationSETCLIENTID.class);

    OperationSETCLIENTID(nfs_argop4 args) {
        super(args, nfs_opnum4.OP_SETCLIENTID);
    }

    @Override
    public void process(CompoundContext context, nfs_resop4 result) throws ChimeraNFSException {

        final SETCLIENTID4res res = result.opsetclientid;

        verifier4 verifier = _args.opsetclientid.client.verifier;
        NFS4Client client = context.getStateHandler().getClientByVerifier(verifier);
        if (client != null) {
            netaddr4 addr = new netaddr4();
            addr.na_r_netid = client.getRemoteAddress().getAddress() instanceof Inet6Address
                    ? "tcp6" : "tcp";
            addr.na_r_addr = InetSocketAddresses.uaddrOf(client.getRemoteAddress());
            res.status = nfsstat.NFSERR_CLID_INUSE;
            res.client_using = new clientaddr4(addr);
            throw new ChimeraNFSException(nfsstat.NFSERR_CLID_INUSE, "Client Id In use");
        }

        client = context.getStateHandler().createClient(
                context.getRpcCall().getTransport().getRemoteSocketAddress(),
                context.getRpcCall().getTransport().getLocalSocketAddress(),
                _args.opsetclientid.client.id, _args.opsetclientid.client.verifier, null);

        res.resok4 = new SETCLIENTID4resok();
        res.resok4.clientid = new clientid4();
        res.resok4.clientid.value = new uint64_t(client.getId());
        res.resok4.setclientid_confirm = client.verifier();
        res.status = nfsstat.NFS_OK;
    }
}
