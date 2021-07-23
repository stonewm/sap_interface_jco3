package com.stonewang.jcoserver;

import com.sap.conn.jco.*;
import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

public class FmHandler implements JCoServerFunctionHandler {
    @Override
    public void handleRequest(JCoServerContext context, JCoFunction fm) throws AbapException, AbapClassException {
//        System.out.println("-------------------------------------");
//        System.out.println("Call: " + fm.getName());
//        System.out.println("ConnectionId: " + context.getConnectionID());
//        System.out.println("SessionId: " + context.getSessionID());
//        System.out.println("TID: " + context.getTID());
//        System.out.println("Repository name: " + context.getRepository().getName());
//        System.out.println("is in transaction : " + context.isInTransaction());
//        System.out.println("is stateful       : " + context.isStatefulSession());
//
//        System.out.println("-------------------------------------");
//        System.out.println("gwhost: " + context.getServer().getGatewayHost());
//        System.out.println("gwserv: " + context.getServer().getGatewayService());
//        System.out.println("progid: " + context.getServer().getProgramID());
//
//        System.out.println("-------------------------------------");
//        System.out.println("attributes  : ");
//        System.out.println(context.getConnectionAttributes().toString());
//
//        System.out.println("-------------------------------------");
//        System.out.println("CPIC conversation ID: " + context.getConnectionAttributes().getCPICConversationID());

        if (fm.getName().equals("STFC_CONNECTION")) {
            // 打印从SAP接受的request text
            System.out.println("Request text: " + fm.getImportParameterList().getString("REQUTEXT"));
//            System.out.println("Response text: " + fm.getExportParameterList().getString("RESPTEXT"));

            // 向SAP返回消息
            JCoParameterList outboundParams = fm.getExportParameterList();
            outboundParams.setValue("ECHOTEXT", fm.getImportParameterList().getString("REQUTEXT"));
            outboundParams.setValue("RESPTEXT", "我是从Java端返回的字符串");
        }

        if (fm.getName().equals("BAPI_COMPANYCODE_GETDETAIL")){
            JCoStructure cocdDetials = fm.getExportParameterList()
                                         .getStructure("COMPANYCODE_DETAIL");

            for (JCoField item : cocdDetials){
                System.out.println(String.format("%s: %s", item.getName(), item.getValue()));
            }
        }
    }
}
