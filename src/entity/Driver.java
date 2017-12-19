package entity;

public class Driver{
    protected String did;
    protected String driverName;

    public Driver(String did, String driverName) {
        this.did = did;
        this.driverName = driverName;
    }

    public Driver() {
    }

    public String getDid() {
        return did;
    }
    public void setDid(String did) {
        this.did = did;
    }
    public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    @Override
    public String toString() {
        return "Driver [did=" + did + ", driverName=" + driverName + "]";
    }

}
