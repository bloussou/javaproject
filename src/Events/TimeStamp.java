package Events;

public class TimeStamp {
	
	private int min;	

	public TimeStamp(){
		Time t = Time.getInstanceTime();
		this.min = t.min;		
	}
	
	public int getTimeStamp(){
		return (this.min);
	}

	@Override
	public String toString() {
		
		return "day:" + Integer.toString(this.min/1440) + "  hour:" + Integer.toString((this.min%1440)/60) + "  min:" + Integer.toString((this.min%1440)%60);
	}
	

	
}
