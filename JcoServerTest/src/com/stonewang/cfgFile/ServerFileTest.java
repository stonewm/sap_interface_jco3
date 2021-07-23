package com.stonewang.cfgFile;

import org.junit.Test;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class ServerFileTest {
    @Test
    public void testGetProperties(){
        ServerFile f = new ServerFile();
        Properties props = f.getProperties();

        Enumeration<?> e = props.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = props.getProperty(key);
            System.out.println(key + "=" + value);
        }
    }

    @Test
    public void testCreateCfgFile() throws IOException {
        ServerFile f = new ServerFile();
        Properties props = f.getProperties();
        f.createCfgFile("server","jcoserver", props);
    }
}
