package jco3.demo3;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import org.junit.Test;

public class TestFileDestinationManager {
    @Test
    public void pingDest() throws JCoException {
        JCoDestination dest = FileDestinationManager.getDestination("SAP_AS");
        dest.ping();
    }
}
