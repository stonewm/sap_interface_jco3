package jco3.demo3;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.Environment;

import java.io.File;

public class FileDestinationManager {
    public static JCoDestination getDestination(String destName) throws JCoException {
        File dir = new File("."); // current directory
        String suffix = "txt";

        DestinationDataProviderImpl providerImpl = new DestinationDataProviderImpl();
        providerImpl.setDestinationFile(dir, destName, suffix);
        Environment.registerDestinationDataProvider(providerImpl);

        JCoDestination dest = JCoDestinationManager.getDestination(destName);
        return dest;
    }
}
