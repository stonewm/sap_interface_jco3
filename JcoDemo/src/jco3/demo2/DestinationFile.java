package jco3.demo2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import com.sap.conn.jco.ext.DestinationDataProvider;
import org.junit.Test;

public class DestinationFile {

    public Properties setProperties() {
        Properties props = new Properties();

        props.setProperty(DestinationDataProvider.JCO_ASHOST, "192.168.44.100");
        props.setProperty(DestinationDataProvider.JCO_SYSNR, "00"); // instance number
        props.setProperty(DestinationDataProvider.JCO_USER, "STONE");
        props.setProperty(DestinationDataProvider.JCO_PASSWD, "w123456");
        props.setProperty(DestinationDataProvider.JCO_CLIENT, "001");
        props.setProperty(DestinationDataProvider.JCO_LANG, "EN");

        return props;
    }

    private void doCreateFile(String fName, String suffix, Properties props) throws IOException {

        /**
         * Write contents of properties into a text file
         * which was named [fName+suffix.jcodestination]
         */

        File cfg = new File(fName + "." + suffix);
        if (!cfg.exists()){
            // Create file output stream, not using append mode
            FileOutputStream outStream = new FileOutputStream(cfg, false);

            // store the properties in file output stream
            // and also add comments
            props.store(outStream, "SAP Logon parameters");

            outStream.close();
        }else{
            throw new RuntimeException("Configuration file already exits.");
        }
    }

    @Test
    public void testCreateCfgFile() throws IOException {
        Properties props = this.setProperties();
        String fileName = "SAP_AS";

        this.doCreateFile(fileName, "jcodestination", props);
    }
}
