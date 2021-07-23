package com.stonewang.cfgFile;

import com.sap.conn.jco.ext.ServerDataProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ServerFile {
    public Properties getProperties()
    {
        // logon parameters and other properties
        Properties serverProps = new Properties();

        serverProps.setProperty(ServerDataProvider.JCO_GWHOST, "sapecc6");
        serverProps.setProperty(ServerDataProvider.JCO_GWSERV, "sapgw00");
        serverProps.setProperty(ServerDataProvider.JCO_PROGID, "JCO3_TEST");
        serverProps.setProperty(ServerDataProvider.JCO_REP_DEST, "ECC");
        serverProps.setProperty(ServerDataProvider.JCO_CONNECTION_COUNT, "2");

        return serverProps;
    }

    /**
     * 将properties属性的内容写入配置文件，文件名为fName+suffix.jcodestination
     */
    public void createCfgFile(String fName, String suffix, Properties props) throws IOException
    {
        File cfg = new File(fName+"."+suffix);
        if (!cfg.exists()){ // file not exists
            // Create file output stream, not using append mode
            FileOutputStream oStream = new FileOutputStream(cfg, false);
            props.store(oStream, "SAP for Java server parameters");
            oStream.close();
        }else{
            throw new RuntimeException("File alreay exists.");
        }
    }
}
