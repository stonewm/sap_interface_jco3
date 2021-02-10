package jco3.demo6;

import com.sap.conn.jco.*;
import jco3.jcoUtils.Utils;


public class TableAsExportingDemo {
    public JCoTable getCocdList() throws JCoException {
        JCoDestination dest = JCoDestinationManager.getDestination("ECC");
        JCoFunction fm = dest.getRepository().getFunction("BAPI_COMPANYCODE_GETLIST");
        fm.execute(dest);

        JCoTable companies = fm.getTableParameterList().getTable("COMPANYCODE_LIST");

        return companies;
    }

    @Test
    public void testGetCocdList() throws JCoException {
        JCoTable companies = getCocdList();
        Utils.printJcoTable(companies);
    }
}
