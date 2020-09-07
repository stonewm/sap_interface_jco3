package jco3.jcoUtils;

import com.sap.conn.jco.*;

public class Utils {
    public static void printJcoTable(JCoTable table) {
        printHeader(table);
        printLines(table);
    }

    private static void printHeader(JCoTable table) {
        // JCoRecordMeataData is the meta data of either a structure or a table.
        // Each element describes a field of the structure or table.
        JCoRecordMetaData  tableMeta = table.getRecordMetaData();
        for (int i = 0; i < tableMeta.getFieldCount(); i++){
            System.out.print(
                    String.format("%s\t", tableMeta.getName(i))
            );
        }
        System.out.println(); // new line
    }

    private static void printLines(JCoTable table) {
        for (int i = 0; i < table.getNumColumns(); i++){
            // set current row position (starting from 0)
            table.setRow(i);

            // Every row is of type JCoStructure
            // iterate each field in a row
            for (JCoField field : table){
                System.out.print(
                        String.format("%s\t",  field.getValue())
                );
            }
            System.out.println();
        }
    }
}
