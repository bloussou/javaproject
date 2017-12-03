package Events;

public class Time {
	
	private static Time instance = null;
	int min;
		
	private Time(){
		this.min = 0;
	}
	
	public static Time getInstanceTime(){
		if (instance == null){
			instance = new Time();
		}
		return instance;
	}

	public void timeGoes(int minutesMore){
		this.min = this.min + minutesMore;
	}

	public int getTime(){
		return (this.min);
	}

	@Override
	public String toString() {
		
		return "day:" + Integer.toString(this.min/1440) + "  hour:" + Integer.toString((this.min%1440)/60) + "  min:" + Integer.toString((this.min%1440)%60);
	}


	
}
