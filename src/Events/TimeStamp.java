package Events;

public class TimeStamp {
	
	private int min;	

	public TimeStamp(){
		Time t = Time.getInstanceTime();
		this.min = t.min;		
	}
	
	public String getStringStamp(){
		return "D" + Integer.toString(this.day) + "H" + Integer.toString(this.hour) + "Min" + Integer.toString(this.min);
	}
	
	public int getIntStamp(){
		return (this.day*10000+this.hour*100 +this.min);
	}
	
}
