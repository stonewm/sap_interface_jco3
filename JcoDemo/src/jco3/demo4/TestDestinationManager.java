package jco3.demo4;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import org.junit.Test;

public class TestDestinationManager {
    @Test
    public void pingDest() throws JCoException {
        JCoDestination dest = DestinationManager.getDestination("SAP_AS");
        dest.ping();
    }
}
