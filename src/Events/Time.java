package Events;

public class Time {
	
	private static Time instance = null;
	int day;
	int hour;
	int min;
		
	public Time(){
		this.day = 0;
		this.hour = 0;
		this.min = 0;
	}
	
	public static Time getInstanceTime(){
		if (instance == null){
			instance = new Time();
		}
		return instance;
	}
	
	
	
	public void timeGoes(int minutesMore){
		if(this.min<60-minutesMore){
			this.min = this.min + minutesMore;
		}
		else{
			this.min = this.min + minutesMore - 60;
			if(this.hour< 23){
				this.hour = this.hour + 1;	
			}
			else{
				this.hour = 0;
				this.day = this.day + 1;
			}
		}
	}

	public int getTime(){
		return (this.day*10000+this.hour*100 +this.min);
	}

	@Override
	public String toString() {
		return "Time [D" + day + ", H" + hour + ", Min" + min + "]";
	}


	
}
