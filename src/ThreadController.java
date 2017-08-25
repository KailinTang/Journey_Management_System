import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

public class ThreadController extends Thread{
		//Periodically check whether there is some train need to be started


	public Database database = new Database();

	
	public void run(){
		this.database.journeyTable.load();
		for(int i=0; i<=this.database.journeyTable.getRowCount(); i++){
			SimpleDateFormat sdf0 = new SimpleDateFormat("HH:mm");
			String initialTime = (String) database.journeyTable.getValueAt(i, 4);
			Date dt0 = null;
			try {
				dt0 = sdf0.parse(initialTime);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		Calendar cal = Calendar.getInstance(); 	//Get the system time
		Date startTime=new Date();		
		startTime=dt0;	
		cal.setTime(startTime);
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		
	while(true){
		Calendar calendar = Calendar.getInstance();  
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
		String str = (dateFormat2.format(calendar.getTime()));
		System.out.println(str);	//Constantly show the time
		int curHour = calendar.get(Calendar.HOUR);
		int curMinute = calendar.get(Calendar.MINUTE);
		System.out.println("Next train will be launched at: " + sdf0.format(dt0));
		if((hour==curHour)&&(minute==curMinute)){	//Check whether it is time to start any train
			JOptionPane.showMessageDialog(null,"It's time to start the train " + 
					(String) database.journeyTable.getValueAt(i, 2));
			break;
		}
		try {
			Thread.sleep(1000);		//Delay 1 second
			} 
		catch (InterruptedException e) {
			e.printStackTrace();
				}
			}		
		}
	}
}
