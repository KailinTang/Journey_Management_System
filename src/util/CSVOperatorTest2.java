package util;

import java.util.List;

import org.junit.Test;

public class CSVOperatorTest2 {

    @Test
    public void testImportCSVString() {
        List<List<String>> allTestData = CSVOperator.importCSV("csvTestFile.csv");
        if (allTestData != null && !allTestData.isEmpty()) {
            for (List<String> rowData : allTestData) {
                System.out.println(String.join(",", rowData));
            }
        }
    }
}



