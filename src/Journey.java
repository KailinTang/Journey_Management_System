import java.text.SimpleDateFormat;

public class Journey{
	protected String jid;
	protected String rid;
	protected String tid;
	protected String did;
	protected SimpleDateFormat time0;
	protected SimpleDateFormat time1;
	protected SimpleDateFormat time2;
	protected SimpleDateFormat time3;
	protected SimpleDateFormat time4;
	
	public Journey(String jid, String rid, String tid, String did, SimpleDateFormat time0, SimpleDateFormat time1,
			SimpleDateFormat time2, SimpleDateFormat time3, SimpleDateFormat time4) {
		this.jid = jid;
		this.rid = rid;
		this.tid = tid;
		this.did = did;
		this.time0 = time0;
		this.time1 = time1;
		this.time2 = time2;
		this.time3 = time3;
		this.time4 = time4;
	}

	public String getJid() {
		return jid;
	}

	public void setJid(String jid) {
		this.jid = jid;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public SimpleDateFormat getTime0() {
		return time0;
	}

	public void setTime0(SimpleDateFormat time0) {
		this.time0 = time0;
	}

	public SimpleDateFormat getTime1() {
		return time1;
	}

	public void setTime1(SimpleDateFormat time1) {
		this.time1 = time1;
	}

	public SimpleDateFormat getTime2() {
		return time2;
	}

	public void setTime2(SimpleDateFormat time2) {
		this.time2 = time2;
	}

	public SimpleDateFormat getTime3() {
		return time3;
	}

	public void setTime3(SimpleDateFormat time3) {
		this.time3 = time3;
	}

	public SimpleDateFormat getTime4() {
		return time4;
	}

	public void setTime4(SimpleDateFormat time4) {
		this.time4 = time4;
	}

	@Override
	public String toString() {
		return "Journey [jid=" + jid + ", rid=" + rid + ", tid=" + tid + ", did=" + did + ", time0=" + time0
				+ ", time1=" + time1 + ", time2=" + time2 + ", time3=" + time3 + ", time4=" + time4 + "]";
	}
	
	
}