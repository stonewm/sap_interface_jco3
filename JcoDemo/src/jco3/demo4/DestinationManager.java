package jco3.demo4;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;

import java.util.Properties;

public class DestinationManager {
    private static Properties setProperties(String destName)
    {
        // SAP connection parameters and other properties
        Properties props = new Properties();

        if (destName == "SAP_AS") {
            props.setProperty(DestinationDataProvider.JCO_ASHOST, "192.168.44.100");
            props.setProperty(DestinationDataProvider.JCO_SYSNR, "00");
            props.setProperty(DestinationDataProvider.JCO_USER, "STONE");
            props.setProperty(DestinationDataProvider.JCO_PASSWD, "w123456");
            props.setProperty(DestinationDataProvider.JCO_CLIENT, "001");
            props.setProperty(DestinationDataProvider.JCO_LANG, "EN");
        }

        return props;
    }

    public static JCoDestination getDestination (String destName) throws JCoException {
        Properties props = setProperties(destName);

        DestinationDataProviderImpl providerImpl = new DestinationDataProviderImpl();
        providerImpl.addDestinationProps(destName, props);

        Environment.registerDestinationDataProvider(providerImpl);

        JCoDestination dest = JCoDestinationManager.getDestination(destName);
        return dest;
    }
}
