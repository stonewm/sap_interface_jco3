package jco3.demo6;

import com.sap.conn.jco.*;
import org.junit.Test;

public class TableAsImportingDemo {
    public JCoTable readTable() throws JCoException {
        JCoDestination dest = JCoDestinationManager.getDestination("ECC");
        JCoFunction fm = dest.getRepository().getFunction("RFC_READ_TABLE");

        // QUERY_TABLE: importing, table name to be queried
        fm.getImportParameterList().setValue("QUERY_TABLE", "SPFLI");

        // DELIMITER: delimiter of output data
        fm.getImportParameterList().setValue("DELIMITER", ",");

        // OPTIONS: Selection entries, valid "WHERE clauses"
        // needing to be populated
        JCoTable options = fm.getTableParameterList().getTable("OPTIONS");

        options.appendRow();
        options.setValue("TEXT", "COUNTRYFR EQ 'US' ");

        options.appendRow();
        options.setValue("TEXT", " OR COUNTRYFR EQ 'DE' ");

        // FIELDS: names to be output in the structure, needing to be specified
        // if we want to restrict, otherwise all fields will be extracted
        String[] outputFields = new String[] {"CARRID", "COUNTRYFR", "CITYFROM", "CITYTO" };
        JCoTable fields = fm.getTableParameterList().getTable("FIELDS");

        int count = outputFields.length;
        fields.appendRows(count);
        for (int i = 0; i < count; i++){
            fields.setRow(i);
            fields.setValue("FIELDNAME", outputFields[i]);
        }


        fm.execute(dest);

        // Get data
        JCoTable data = fm.getTableParameterList().getTable("DATA");

        return data;
    }

    @Test
    public void testReadTable() throws JCoException {
        JCoTable data = readTable();

        for(int i = 0; i < data.getNumRows(); i++){
            data.setRow(i);
            String rowData = data.getValue("WA").toString(); // get value from first column
            String[] values = rowData.split(",");
            for(String item : values) {
                System.out.print(item + "\t");
            }
            System.out.println();
        }
    }
}
