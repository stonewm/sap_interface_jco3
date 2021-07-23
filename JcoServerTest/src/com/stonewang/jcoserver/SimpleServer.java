package com.stonewang.jcoserver;

import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.server.DefaultServerHandlerFactory;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerFactory;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

public class SimpleServer {
    public static void main(String[] args)
    {
        simpleServer();
    }

    public static void simpleServer()
    {
        JCoServer server;
        String serverName = "SERVER";

        try{
            server = JCoServerFactory.getServer(serverName);
        }catch (JCoException ex) {
            throw new RuntimeException("Unable to create server " + serverName + ", because of "
                    + ex.getMessage(), ex);
        }

        // 函数注册:注册的SAP FM才能调用
        JCoServerFunctionHandler fmHandler = new FmHandler();
        DefaultServerHandlerFactory.FunctionHandlerFactory factory
                = new DefaultServerHandlerFactory.FunctionHandlerFactory();
        factory.registerHandler("STFC_CONNECTION", fmHandler);
        factory.registerHandler("BAPI_COMPANYCODE_GETDETAIL", fmHandler);

        server.setCallHandlerFactory(factory);

        server.start();
        System.out.println("Jco Server has started...");
        System.out.println("Press <CTRL>+<C> to stop.");

    }
}
