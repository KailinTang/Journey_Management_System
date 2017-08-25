import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;;

public class JourneyTableTest {
	
	boolean flag;
	@Test
	public void testJourneyTable() {
		String info = checkData(Arrays.asList
				(new String[] { "#4001", "#3001", "#1001", "#2001", 
						"08:00", "08:20", "08:40", "09:00", "09:20" }));
		if(info == null){
			flag = true;
		}
		assertEquals(flag,true);

	}
	public String checkData(List<String> journeys) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		for (int i = 4; i < journeys.size(); i++) {
			try {
				sdf.parse(journeys.get(i));
			} catch (ParseException e) {
				return "Time \"" + journeys.get(i) + "\" is illegal!";
			}
		}

		try {
			Date startDate = sdf.parse(journeys.get(4));

			if (!(startDate.after(sdf.parse("07:59")) && startDate.before(sdf.parse("16:01")))) {
				return "Illegal time: \"" + journeys.get(4) + "\". Start time must between 08:00 and 16:00!";
			}

			for (int i = 5; i < journeys.size(); i++) {
				long deltaTime = sdf.parse(journeys.get(i)).getTime() - sdf.parse(journeys.get(i - 1)).getTime();
				if (!(deltaTime >= 15 * 60 * 1000 && deltaTime <= 30 * 60 * 1000)) {
					return "Illegal time: \"" + journeys.get(i) + "\". Time used between two stops must between 15min and 30min!";
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}





