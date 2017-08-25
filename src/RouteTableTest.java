
import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;;

public class RouteTableTest {
	
	boolean flag;
	@Test
	public void testRouteTable() {
		String info = RouteTable.checkStation
				(Arrays.asList(new String[] { "#3001", "A", "B", "C", "D"}));
		if(info == null){
			flag = true;
		}
		assertEquals(flag,true);

	}
}





