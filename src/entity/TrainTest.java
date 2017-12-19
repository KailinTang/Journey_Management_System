package entity;

import util.CSVOperator;

import java.util.List;

public class TrainTest{
	static Train testTrain = new Train();
	public static void main(String args[]){
		TrainTest.testImportTrainCSVString();
	}
	public static void testImportTrainCSVString() {
		List<List<String>> allData = CSVOperator.importCSV("train_table.csv");
		if (allData != null && !allData.isEmpty()) {
			for (List<String> rowData : allData) {
				System.out.println(String.join(",", rowData));
			}
		}
		int trainNum = allData.size() - 1;
		for(int i=1; i<=trainNum; i++){
			testTrain.setTid(allData.get(i).get(0));
			testTrain.setTrainName(allData.get(i).get(1));
			System.out.println(testTrain.toString());
		}
	}
}

