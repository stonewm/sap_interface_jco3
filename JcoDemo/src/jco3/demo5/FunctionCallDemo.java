package jco3.demo5;

import com.sap.conn.jco.*;
import org.junit.Test;

public class FunctionCallDemo {

    public void getCoCdDetail(String cocd) throws JCoException {
        // JCoDestination : backend SAP system
        JCoDestination dest = JCoDestinationManager.getDestination("ECC");

        JCoRepository repository = dest.getRepository();

        JCoFunction fm = repository.getFunction("BAPI_COMPANYCODE_GETDETAIL");
        if (fm == null) {
            throw new RuntimeException("Function does not exist in SAP");
        }

        // Set importing parameters
        fm.getImportParameterList().setValue("COMPANYCODEID", cocd);

        fm.execute(dest);

        JCoStructure cocdDetail = fm.getExportParameterList()
                                    .getStructure("COMPANYCODE_DETAIL");

        this.printStructure(cocdDetail);

    }

    private void printStructure(JCoStructure stru){
        for (JCoField field : stru){
            System.out.println(String.format("%s \t %s", field.getName(), field.getString()));
        }
    }

    @Test
    public void testGetCocdDetail() throws JCoException {
        this.getCoCdDetail("Z900");
    }
}
