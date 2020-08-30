package jco3.demo1;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import org.junit.Test;

public class JCoDestinationDemo {
    public JCoDestination getDestination() throws JCoException {
        /**
         * Get instance of JCoDestination from file: ECC.jcodestination
         * which should be located in the root folder of project
         */

        JCoDestination dest = JCoDestinationManager.getDestination("ECC");
        return dest;
    }

    @Test
    public void pingDestination() throws JCoException {
        JCoDestination dest = this.getDestination();
        dest.ping();
    }
}


