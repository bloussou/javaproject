package Events;

import Emergency.*;
import HR.*;

public class Arrival extends Event{

	private ED ed;
	private Patient patient;
	
	public Arrival(ED ed, Patient patient){
		this.ed = ed;
		this.patient = patient;
		this.setStartTime(new TimeStamp());
		Time time = Time.getInstanceTime();
		this.setEndTime(endTime);
	}
	
}
