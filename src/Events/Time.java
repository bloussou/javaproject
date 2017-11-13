package Events;

public class Time {
	
	int day;
	int hour;
	int min;
	
	public Time(){
		this.day = 0;
		this.hour = 0;
		this.min = 0;
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

	
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}

	
	@Override
	public String toString() {
		return "Time [D" + day + ", H" + hour + ", Min" + min + "]";
	}

	
	
	
	
}
