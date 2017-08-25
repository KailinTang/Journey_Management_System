import java.text.ParseException;

import org.junit.Test;

public class DatabaseTest {

	@Test
	public void test() {	//Initialization
		try {
			Database.main(null);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
