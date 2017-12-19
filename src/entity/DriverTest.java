package entity;

import util.CSVOperator;

import java.util.List;

public class DriverTest {
    static Driver testDriver = new Driver();

    public static void main(String args[]) {
        DriverTest.testImportDriverCSVString();
    }

    public static void testImportDriverCSVString() {
        List<List<String>> allData = CSVOperator.importCSV("driver_table.csv");
        if (allData != null && !allData.isEmpty()) {
            for (List<String> rowData : allData) {
                System.out.println(String.join(",", rowData));
            }
        }
        int trainNum = allData.size() - 1;
        for (int i = 1; i <= trainNum; i++) {
            testDriver.setDid(allData.get(i).get(0));
            testDriver.setDriverName(allData.get(i).get(1));
            System.out.println(testDriver.toString());
        }
    }
}


