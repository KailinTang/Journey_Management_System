
public class Route{
	protected String rid;
	protected char station1;
	protected char station2;
	protected char station3;
	protected char station4;
	
	public Route(String rid, char station1, char station2, char station3, char station4) {
		this.rid = rid;
		this.station1 = station1;
		this.station2 = station2;
		this.station3 = station3;
		this.station4 = station4;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public char getStation1() {
		return station1;
	}
	public void setStation1(char station1) {
		this.station1 = station1;
	}
	public char getStation2() {
		return station2;
	}
	public void setStation2(char station2) {
		this.station2 = station2;
	}
	public char getStation3() {
		return station3;
	}
	public void setStation3(char station3) {
		this.station3 = station3;
	}
	public char getStation4() {
		return station4;
	}
	public void setStation4(char station4) {
		this.station4 = station4;
	}
	@Override
	public String toString() {
		return "Route [rid=" + rid + ", station1=" + station1 + ", station2=" + station2 + ", station3=" + station3
				+ ", station4=" + station4 + "]";
	}
	
	
	
}
