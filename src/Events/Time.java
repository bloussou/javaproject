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

	public static void main(String[] args) {
		
		Time t = Time.getInstanceTime();
		for (int i = 0; i < 5426; i++) {
			t.timeGoes(2);
		}
		System.out.println("Time : " + t.getTime() + " minutes");
		System.out.println("Time : " + t.toString());
	}
	
}
