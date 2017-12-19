package table;

import entity.Driver;
import entity.Train;
import util.CSVOperator;
import db.Database;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Table extends AbstractTableModel {


    /**
     *
     */
    private static final long serialVersionUID = 141896957889042146L;
    public ArrayList<Train> trainList = new ArrayList<Train>();
    public ArrayList<Driver> driverList = new ArrayList<Driver>();

    public String filePath = null;
    public List<String> headers = null;
    public List<List<String>> allData = null;    //Two dimensional list represent all data in a table
    public Database database = null;

    public Table(String filePath, String... headers) {        //Constructor for the Table class
        this.filePath = filePath;    //The location where store the table
        this.headers = Arrays.asList(headers);    //The header of every table

        this.load();

        if (this.allData.size() == 0) {
            this.allData.add(this.headers);
            return;
        }

        if (this.headers.size() != 0) {
            if (this.headers.size() != this.allData.get(0).size()) {
                this.clear();
            }
            for (int i = 0; i < this.headers.size(); i++) {
                if (!this.headers.get(i).equals(this.allData.get(0).get(i))) {
                    this.clear();
                    return;
                }
            }
        }
    }


    public void clear() {
        this.allData = new ArrayList<List<String>>();
        this.allData.add(this.headers);
    }

    public void load() {
        //Load the data from csv file
        this.allData = CSVOperator.importCSV(this.filePath);
    }

    public boolean save() {
        //Save the data to csv file
        return CSVOperator.exportCSV(this.filePath, this.allData);
    }

    public int size() {
        //Return the size of the 2 dimensional list
        return this.allData.size() - 1;        //The size of row space, apart from the header
    }

    public void insert(int index, List<String> rowData) {
        //Insert a specific row to the index position
        this.allData.add(index + 1, rowData);
    }

    public void insertFront(List<String> rowData) {
        //Insert a specific row to the front
        this.insert(0, rowData);
    }

    public void insertBack(List<String> rowData) {
        //Insert a specific row to the end, this method is used when we add new data
        this.insert(this.size(), rowData);
    }

    public String dataToString() {
        //Make the element of the list together with the delimiter ","
        if (allData != null && !allData.isEmpty()) {
            String res = "";
            for (List<String> rowData : allData) {
                res += (String.join(",", rowData) + "\n");
            }
            return res;
        } else {
            return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        //Get the header name of a specific column
        return this.headers.get(col);
    }

    public void deleteRow(int index) {
        //Delete all data of a specific row
        this.allData.remove(index + 1);
        this.save();
    }

    @Override
    public int getRowCount() {
        //Count the number of rows
        return this.size();
    }

    public void addNewRow() {
        //Add a new row
        List<String> newData = new ArrayList<String>();
        for (int i = 0; i < this.getColumnCount(); i++) {
            newData.add("");
        }
        this.insertBack(newData);
        this.save();
    }

    @Override
    public int getColumnCount() {
        //Get the number of columns
        return this.headers.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Get the value at a specific position
        try {
            return this.allData.get(rowIndex + 1).get(columnIndex);
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //Every cell is editable
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Set value at a cell
        if (aValue instanceof String) {
            this.allData.get(rowIndex + 1).set(columnIndex, (String) aValue);
            this.save();
        }
    }

    public String checkIsValid() {
        //Check whether there exists a null cell or a duplicated key
        for (int i = 0; i < this.getRowCount(); i++) {    //duplicated key checking
            for (int j = i; j < this.getRowCount() - 1; j++) {
                if (this.getValueAt(i, 0).equals(this.getValueAt(j + 1, 0))) {
                    return "There exists the same key " + this.getValueAt(i, 0) + " in the first column! Please modify them!";
                }
            }
        }
        for (int i = 0; i < this.getRowCount(); i++) {    //null cell checking
            for (int j = 0; j < this.getColumnCount(); j++) {
                if ("".equals(this.getValueAt(i, j))) {
                    return "Has Empty Cell!";
                }
            }
        }
        return null;
    }

    public boolean hasValueInCol(String data, int columnIndex) {
        //Validation of data used when check key
        for (int i = 0; i < this.getRowCount(); i++) {
            if (this.getValueAt(i, columnIndex).equals(data)) {
                return true;
            }
        }
        return false;
    }

}
