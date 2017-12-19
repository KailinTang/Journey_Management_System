package table;

import entity.Route;

import java.util.ArrayList;
import java.util.List;

public class RouteTable extends Table {
	/**
	 *RouteTable is a subclass of Table 
	 */
	private static final long serialVersionUID = 930144252970919267L;

	public ArrayList<Route> routeList = new ArrayList<Route>();
	
	public RouteTable(String filePath, String... headers) {
		super(filePath, headers);
	}

	public static String checkStation(List<String> routes) {
	//Constrain on station 
		for (int i = 1; i < routes.size(); i++) {
			if (!(routes.get(i).charAt(0) >= 'A' && routes.get(i).charAt(0) <= 'G')) {
				return "Station \"" + routes.get(i) + "\" not exist!";
			}
		}

		for (int i = 2; i < routes.size(); i++) {
			if (routes.get(i).equals(routes.get(i - 1))) {
				return "Route \"" + routes.get(0) + "\" pass same station!";
			}
		}

		return null;
	}

	public List<String> getRoutes(String station) {		//get all routes that pass a specific station
		ArrayList<String> res = new ArrayList<String>();

		if (station == null) {
			return res;
		}

		for (int i = 0; i < this.getRowCount(); i++) {
			for (int j = 1; j < this.getColumnCount(); j++) {
				if (station.equals(this.getValueAt(i, j))) {
					res.add((String) this.getValueAt(i, 0));
					continue;
				}
			}
		}

		return res;
	}

	public String checkIsValid() {
	//Validation checking with new requirement
		String checkRes = super.checkIsValid();

		if (checkRes != null) {
			return checkRes;
		} else {
			for (int i = 0; i < this.getRowCount(); i++) {		//Check station 
				checkRes = checkStation(this.allData.get(i + 1));
				if (checkRes != null) {
					return checkRes;
				}
			}

			return null;
		}
	}
}
