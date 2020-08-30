package jco3.demo3;

import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DestinationDataProviderImpl implements DestinationDataProvider {
    private File dir;
    private String destName;
    private String suffix;

    @Override
    public Properties getDestinationProperties(String s) {
        Properties props = null;

        try {
            props = this.loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;
    }

    @Override
    public boolean supportsEvents() {
        return false;
    }

    @Override
    public void setDestinationDataEventListener(DestinationDataEventListener destinationDataEventListener) {
        throw new UnsupportedOperationException();
    }

    public void setDestinationFile(File dir, String destName, String suffix) {
        /**
         * 指定 Destination file
         */

        this.dir = dir;
        this.destName = destName;
        this.suffix = suffix;
    }

    private Properties loadProperties() throws IOException {
        Properties props = null;

        File cfgFile = new File(this.dir, this.destName + "." + this.suffix);
        if (cfgFile.exists()){
            FileInputStream inputStream = new FileInputStream(cfgFile);
            props = new Properties();
            props.load(inputStream);
            inputStream.close();
        }else{
            throw new RuntimeException("Configuration file does not exits");
        }

        return props;
    }
}
