import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CSVOperatorTest1 {

	@Test
	public void testExportCSVFile() {
		List<List<String>> allData = new ArrayList<List<String>>();
		allData.add(Arrays.asList(new String[] { "a", "b", "c" }));
		allData.add(Arrays.asList(new String[] { "aa", "bb", "cc" }));
		allData.add(Arrays.asList(new String[] { "aaa", "bbb", "ccc" }));
		boolean isSuccess = CSVOperator.exportCSV("csvTestFile.csv", allData);
		System.out.println(isSuccess);
	}
}

