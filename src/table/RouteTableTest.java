package table;

import static java.awt.SystemColor.info;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class RouteTableTest {

    boolean flag;

    @Test
    public void testRouteTable() {
        String infoStr = RouteTable.checkStation
                (Arrays.asList("#3001", "A", "B", "C", "D"));
        if (info == null) {
            flag = true;
        }
        assertEquals(flag, true);

    }
}





