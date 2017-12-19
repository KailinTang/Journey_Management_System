package entity;

public class Train {
	protected String tid;
	protected String trainName;
	
	public Train(String tid, String trainName){
		this.tid = tid;
		this.trainName = trainName;
	}
	public Train(){
		
	}
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	@Override
	public String toString() {
		return "Train [tid=" + tid + ", trainName=" + trainName + "]";
	}
	
}
