import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVOperator {
	//This class is used to manipulate on a csv file(read/write)
	public static boolean exportCSV(File file, List<List<String>> allData) {
		//This method is used to put the data into the file
		boolean isSucess = false;

		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {	//Make sure the correctness of operation
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out);
			bw = new BufferedWriter(osw);
			if (allData != null && !allData.isEmpty()) {
				for (List<String> rowData : allData) {
					bw.append(String.join(",", rowData)).append("\r");
				}
			}
			isSucess = true;
		} catch (Exception e) {
			isSucess = false;
		} finally {
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return isSucess;
	}

	public static boolean exportCSV(String filePath, List<List<String>> allData) {
	//This method is used to connect to out file, if the file does not exist, create a new one
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return exportCSV(file, allData);
	}

	public static List<List<String>> importCSV(File file) {
	//This method is used to read the data in the out file to the system
		List<List<String>> allData = new ArrayList<List<String>>();

		if (!file.exists()) {
			return allData;
		}

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				allData.add(Arrays.asList(line.split(",")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
					bufferedReader = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return allData;
	}

	public static List<List<String>> importCSV(String filePath) {
	//This method is used to connect to out file for reading
		File file = new File(filePath);
		return importCSV(file);
	}

	public static void main(String[] args) {
		exportCSV_test();	//Testing program to make sure the correctness
		importCSV_test();
	}

	public static void exportCSV_test() {
		List<List<String>> allData = new ArrayList<List<String>>();
		allData.add(Arrays.asList(new String[] { "1", "2", "3" }));
		allData.add(Arrays.asList(new String[] { "11", "22", "33" }));
		allData.add(Arrays.asList(new String[] { "111", "222", "333" }));
		allData.add(Arrays.asList(new String[] { "1", "2", "3" }));
		allData.add(Arrays.asList(new String[] { "11", "22", "33" }));
		allData.add(Arrays.asList(new String[] { "111", "222", "333" }));
		boolean isSuccess = CSVOperator.exportCSV("csvTestFile.csv", allData);
		System.out.println(isSuccess);
	}

	public static void importCSV_test() {
		List<List<String>> allData = CSVOperator.importCSV("csvTestFile.csv");
		if (allData != null && !allData.isEmpty()) {
			for (List<String> rowData : allData) {
				System.out.println(String.join(",", rowData));
			}
		}
	}
	
	
}