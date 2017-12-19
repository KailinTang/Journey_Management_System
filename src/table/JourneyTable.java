package table;

import db.Database;
import entity.Journey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class JourneyTable extends Table {
    /**
     * JourneyTable is a subclass of Table
     */
    private static final long serialVersionUID = 2306611372387378803L;

    public ArrayList<Journey> journeyList = new ArrayList<Journey>();

    public Database database = null;

    public JourneyTable(Database trainDatabase, String filePath, String... headers) {
        super(filePath, headers);
        this.database = trainDatabase;
    }

    public String checkRDTData() {
        //Check whether the assigned train/journey/route is exist
        for (int i = 0; i < this.getRowCount(); i++) {
            if (!this.database.routeTable.hasValueInCol((String) this.getValueAt(i, 1), 0)) {
                return "Route \"" + this.getValueAt(i, 1) + "\" does't exist!";
            }
            if (!this.database.trainTable.hasValueInCol((String) this.getValueAt(i, 2), 0)) {
                return "Train \"" + this.getValueAt(i, 2) + "\" does't exist!";
            }
            if (!this.database.driverTable.hasValueInCol((String) this.getValueAt(i, 3), 0)) {
                return "Driver \"" + this.getValueAt(i, 3) + "\" does't exist!";
            }
        }

        return null;
    }

    public String checkData(List<String> journeys) {
        //Check the data format including the time arrangement and train/driver arrangement
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for (int i = 4; i < journeys.size(); i++) {
            try {
                sdf.parse(journeys.get(i));
            } catch (ParseException e) {
                return "Time \"" + journeys.get(i) + "\" is illegal!";
            }
        }

        try {
            Date startDate = sdf.parse(journeys.get(4));    //index 4 in journey table is the initial time

            if (!(startDate.after(sdf.parse("07:59")) && startDate.before(sdf.parse("16:01")))) {
                return "Illegal time: \"" + journeys.get(4) + "\". Start time must between 08:00 and 16:00!";
            }

            for (int i = 5; i < journeys.size(); i++) {        //interval between two stations
                long deltaTime = sdf.parse(journeys.get(i)).getTime() - sdf.parse(journeys.get(i - 1)).getTime();
                if (!(deltaTime >= 15 * 60 * 1000 && deltaTime <= 30 * 60 * 1000)) {
                    return "Illegal time: \"" + journeys.get(i) + "\". Time used between two stops must between 15 min and 30 min!";
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < this.getRowCount() - 1; i++) {        //A train/driver can be assigned if it is available
            for (int j = i + 1; j < this.getRowCount(); j++) {
                if (this.getValueAt(i, 2).equals(this.getValueAt(j, 2))) {
                    return "Journey \"" + this.getValueAt(i, 0) + "\" and Journey \"" + this.getValueAt(j, 0) + "\" use same Train \""
                            + this.getValueAt(i, 2) + "\"!";
                }
                if (this.getValueAt(i, 3).equals(this.getValueAt(j, 3))) {
                    return "Journey \"" + this.getValueAt(i, 0) + "\" and Journey \"" + this.getValueAt(j, 0) + "\" use same driver \""
                            + this.getValueAt(i, 3) + "\"!";
                }
            }
        }

        return null;
    }

    public List<List<String>> getJourney(List<String> routes) {        //get the journey that contain specific route
        ArrayList<List<String>> res = new ArrayList<List<String>>();

        for (int i = 0; i < routes.size(); i++) {
            for (int j = 0; j < this.getRowCount(); j++) {
                if (routes.get(i).equals(this.getValueAt(j, 1))) {
                    res.add(this.allData.get(j + 1));
                }
            }
        }

        return res;
    }

    public String checkIsValid() {
        //Validation checking contained more requirement
        String checkRes = super.checkIsValid();        //inheritance of all previous checking requirement

        if (checkRes != null) {
            return checkRes;
        } else {
            checkRes = this.checkRDTData();        //check assign validation
            if (checkRes != null) {
                return checkRes;
            }

            for (int i = 0; i < this.getRowCount(); i++) {        //check time validation
                checkRes = checkData(this.allData.get(i + 1));
                if (checkRes != null) {
                    return checkRes;
                }
            }

            return null;
        }
    }
}
