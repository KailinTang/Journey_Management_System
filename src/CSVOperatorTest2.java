

import java.util.List;
import org.junit.Test;

public class CSVOperatorTest2 {

	@Test
	public void testImportCSVString() {
		List<List<String>> allData = CSVOperator.importCSV("csvTestFile.csv");
		if (allData != null && !allData.isEmpty()) {
			for (List<String> rowData : allData) {
				System.out.println(String.join(",", rowData));
			}
		}
	}
}



