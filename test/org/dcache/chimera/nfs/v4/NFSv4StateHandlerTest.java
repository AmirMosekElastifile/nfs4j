package org.dcache.chimera.nfs.v4;

import org.dcache.chimera.nfs.v4.xdr.stateid4;
import org.dcache.chimera.nfs.v4.xdr.verifier4;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import org.dcache.chimera.nfs.ChimeraNFSException;

public class NFSv4StateHandlerTest {

    private NFSv4StateHandler _stateHandler;

    @Before
    public void setUp() {
        _stateHandler = new NFSv4StateHandler();
    }

    @Test
    public void testGetByStateId() throws Exception {
        NFS4Client client = createClient();

        stateid4 state = client.createState().stateid();
        _stateHandler.addClient(client);
        _stateHandler.getClientIdByStateId(state);
    }

    @Test
    public void testGetByVerifier() throws Exception {
        NFS4Client client = createClient();

        stateid4 state = client.createState().stateid();
        _stateHandler.addClient(client);
        assertEquals(client, _stateHandler.getClientByVerifier(client.verifier()));
    }

    @Test
    public void testGetByVerifierNotExists() throws Exception {
        assertNull("get not exisintg", _stateHandler.getClientByVerifier( new verifier4()));
    }

    @Test(expected=ChimeraNFSException.class)
    public void testGetClientNotExists() throws Exception {
        _stateHandler.getClientByID(1L);
    }

    @Test
    public void testGetClientExists() throws Exception {
         NFS4Client client = createClient();
        _stateHandler.addClient(client);
        assertEquals(client,  _stateHandler.getClientByID(client.getId()));
    }

    @Test
    public void testUpdateLeaseTime() throws Exception {
        NFS4Client client = createClient();
        NFS4State state = client.createState();
        stateid4 stateid = state.stateid();
        state.confirm();
        _stateHandler.addClient(client);
        _stateHandler.updateClientLeaseTime(stateid);
    }

    @Test(expected=ChimeraNFSException.class)
    public void testUpdateLeaseTimeNotConfirmed() throws Exception {
        NFS4Client client = createClient();
        NFS4State state = client.createState();
        stateid4 stateid = state.stateid();

        _stateHandler.addClient(client);
        _stateHandler.updateClientLeaseTime(stateid);
    }

    @Test(expected=ChimeraNFSException.class)
    public void testUpdateLeaseTimeNotExists() throws Exception {
        NFS4Client client = createClient();
        stateid4 state = client.createState().stateid();
        _stateHandler.updateClientLeaseTime(state);
    }

    static NFS4Client createClient() throws UnknownHostException {
        InetSocketAddress address = new InetSocketAddress(InetAddress.getByName("www.google.com"), 123);
        NFS4Client client = new NFS4Client(address, address, "123".getBytes(),
            new verifier4("123".getBytes()), null);
        return client;
    }
}
