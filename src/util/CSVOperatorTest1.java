package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CSVOperatorTest1 {

    @Test
    public void testExportCSVFile() {
        List<List<String>> allTestData = new ArrayList<List<String>>();
        allTestData.add(Arrays.asList("a", "b", "c"));
        allTestData.add(Arrays.asList("aa", "bb", "cc"));
        allTestData.add(Arrays.asList("aaa", "bbb", "ccc"));
        boolean isSuccess = CSVOperator.exportCSV("csvTestFile.csv", allTestData);
        System.out.println(isSuccess);
    }
}

