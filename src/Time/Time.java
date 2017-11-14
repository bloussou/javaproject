package Time;

public class Time {
	private static Time instance = null;
	private int minute;
	
	//private constructor : returns the unique Time object
	private Time(){};
	
	//public getTime method
	public static Time getInstanceTime(){
		if (instance == null){
			instance = new Time();
		}
		return instance;
	}
	
	//public method to obtain next unique 
	public int getNextTime(){
		return minute++;
	}
	
	//public method to obtain the current time
	public int getTime(){
		return minute;
	}
	

}
