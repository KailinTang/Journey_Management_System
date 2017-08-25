import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


public class Database {
	public String directoryPath = "";

	public Table trainTable = null;
	public Table driverTable = null;
	public RouteTable routeTable = null;
	public JourneyTable journeyTable = null;
	public Table backJourneyTable = null;

	public Database(String directoryPath){
		this.directoryPath = directoryPath;
		try {
			this.init();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Database() {
		try {
			this.init();
		} catch (ParseException e) {
		}
	}

	public void init() throws ParseException {
	//Initialize and load table
		this.trainTable = new Table(this.directoryPath + (this.directoryPath.equals("") ? "" : "\\") + "train_table.csv", "tid", "train name");
		this.driverTable = new Table(this.directoryPath + (this.directoryPath.equals("") ? "" : "\\") + "driver_table.csv", "did", "driver name");
		this.routeTable = new RouteTable(this.directoryPath + (this.directoryPath.equals("") ? "" : "\\") + "route_table.csv", "rid", "station 1",
				"station 2", "station 3", "station 4");
		this.journeyTable = new JourneyTable(this, this.directoryPath + (this.directoryPath.equals("") ? "" : "\\") + "jounery_table.csv", "jid", "rid", "tid",
				"did", "start time", "time 1", "time 2", "time 3", "end time");
		this.backJourneyTable = new Table(this.directoryPath + (this.directoryPath.equals("") ? "" : "\\") + "jounery_table_back.csv", "jid", "start time","time 1","time 2","time 3","end time");
		this.journeyTable.load();
		//List<List<String>> totalData = this.journeyTable.allData;
		for(int i=0; i<=journeyTable.getRowCount(); i++){
			//System.out.println(journeyTable.getValueAt(i, 0));
			//System.out.println(journeyTable.getValueAt(i, 4));
			//System.out.println(journeyTable.getValueAt(i, 5));
			//System.out.println(journeyTable.getValueAt(i, 6));
			//System.out.println(journeyTable.getValueAt(i, 7));
			//System.out.println(journeyTable.getValueAt(i, 0));
			SimpleDateFormat sdf0 = new SimpleDateFormat("HH:mm");
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
			SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
			SimpleDateFormat sdf4 = new SimpleDateFormat("HH:mm");
			String initialTime = (String) journeyTable.getValueAt(i, 4);
			String time1 = (String) journeyTable.getValueAt(i, 5);
			String time2 = (String) journeyTable.getValueAt(i, 6);
			String time3 = (String) journeyTable.getValueAt(i, 7);
			String endTime = (String) journeyTable.getValueAt(i, 8);
			Date dt0 = sdf0.parse(initialTime);
			Date dt1 = sdf1.parse(time1);
			Date dt2 = sdf2.parse(time2);
			Date dt3 = sdf3.parse(time3);
			Date dt4 = sdf4.parse(endTime);
			long minute_0_1 = (dt1.getTime()-dt0.getTime())/(1000*60);
			long minute_1_2 = (dt2.getTime()-dt1.getTime())/(1000*60);
			long minute_2_3 = (dt3.getTime()-dt2.getTime())/(1000*60);
			long minute_3_4 = (dt4.getTime()-dt3.getTime())/(1000*60);
			//System.out.println(minute_0_1);
			Calendar C = Calendar.getInstance();
			C.setTime(dt4);
			C.add(Calendar.MINUTE, 30);
			dt0 = C.getTime();
			C.add(Calendar.MINUTE, (int) minute_3_4);
			dt1 = C.getTime();
			C.add(Calendar.MINUTE, (int) minute_2_3);
			dt2 = C.getTime();
			C.add(Calendar.MINUTE, (int) minute_1_2);
			dt3 = C.getTime();
			C.add(Calendar.MINUTE, (int) minute_0_1);
			dt4 = C.getTime();
			//System.out.println(sdf0.format(dt0));
			//System.out.println(sdf0.format(dt1));
			//System.out.println(sdf0.format(dt2));
			//System.out.println(sdf0.format(dt3));
			//System.out.println(sdf0.format(dt4));
			this.backJourneyTable.insertBack(
					Arrays.asList(new String[] {(String) journeyTable.getValueAt(i, 0), sdf0.format(dt0), 
							sdf0.format(dt1), sdf0.format(dt2), sdf0.format(dt3), sdf0.format(dt4)}));
		}
		this.backJourneyTable.save();
	}

	public void clear() {
	//Clear all the content in all table
		this.trainTable.clear();
		this.driverTable.clear();
		this.routeTable.clear();
		this.journeyTable.clear();
	}

	public void load() {
	//Load the content of table to our system
		this.trainTable.load();
		this.driverTable.load();
		this.routeTable.load();
		this.journeyTable.load();
	}

	public void save() {
	//Save the current value to table
		this.trainTable.save();
		this.driverTable.save();
		this.routeTable.save();
		this.journeyTable.save();
	}

	public static void main(String[] args) throws ParseException {
	//Initialize some default data in the database
		Database trainDatabase = null;
		trainDatabase = new Database();
		trainDatabase.clear();
		trainDatabase.trainTable.insertBack(Arrays.asList(new String[] { "#1001", "Freedom" }));
		trainDatabase.trainTable.insertBack(Arrays.asList(new String[] { "#1002", "Democracy" }));
		trainDatabase.trainTable.insertBack(Arrays.asList(new String[] { "#1003", "Queen" }));
		trainDatabase.trainTable.insertBack(Arrays.asList(new String[] { "#1004", "Prince" }));
		trainDatabase.trainTable.insertBack(Arrays.asList(new String[] { "#1005", "Science" }));
		trainDatabase.driverTable.insertBack(Arrays.asList(new String[] { "#2001", "Tom" }));
		trainDatabase.driverTable.insertBack(Arrays.asList(new String[] { "#2002", "Mike" }));
		trainDatabase.driverTable.insertBack(Arrays.asList(new String[] { "#2003", "James" }));
		trainDatabase.driverTable.insertBack(Arrays.asList(new String[] { "#2004", "Andrew" }));
		trainDatabase.driverTable.insertBack(Arrays.asList(new String[] { "#2005", "Edison" }));
		trainDatabase.routeTable.insertBack(Arrays.asList(new String[] { "#3001", "A", "C", "E", "G" }));
		trainDatabase.routeTable.insertBack(Arrays.asList(new String[] { "#3002", "E", "D", "A", "C" }));
		trainDatabase.routeTable.insertBack(Arrays.asList(new String[] { "#3003", "D", "F", "B", "A" }));
		trainDatabase.routeTable.insertBack(Arrays.asList(new String[] { "#3004", "C", "A", "E", "B" }));
		trainDatabase.routeTable.insertBack(Arrays.asList(new String[] { "#3005", "B", "A", "G", "F" }));
		trainDatabase.journeyTable.insertBack(Arrays.asList(new String[] { "#4001", "#3001", "#1001", "#2001", "08:00", "08:20", "08:40", "09:00", "09:20" }));
		trainDatabase.journeyTable.insertBack(Arrays.asList(new String[] { "#4002", "#3002", "#1002", "#2002", "08:10", "08:30", "08:45", "09:05", "09:20" }));
		trainDatabase.journeyTable.insertBack(Arrays.asList(new String[] { "#4003", "#3003", "#1003", "#2003", "09:00", "09:20", "09:40", "10:00", "10:20" }));
		trainDatabase.journeyTable.insertBack(Arrays.asList(new String[] { "#4004", "#3004", "#1004", "#2004", "12:00", "12:20", "12:40", "13:00", "13:20" }));
		trainDatabase.journeyTable.insertBack(Arrays.asList(new String[] { "#4005", "#3005", "#1005", "#2005", "13:00", "13:20", "13:40", "14:00", "14:20" }));
		System.out.println(trainDatabase.trainTable.dataToString());
		System.out.println(trainDatabase.driverTable.dataToString());
		System.out.println(trainDatabase.routeTable.dataToString());
		System.out.println(trainDatabase.journeyTable.dataToString());

		trainDatabase.save();
	}
}