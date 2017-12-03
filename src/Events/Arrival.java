package Events;

import Emergency.*;
import HR.*;

public class Arrival extends Event{

	private Patient patient;
	
	public Arrival(ED ed, Patient patient){
		this.setEd(ed);
		this.patient = patient;
		this.setStartTime(new TimeStamp());
		this.setEndTime(new TimeStamp(this.getDuration()));
	}

	@Override
	public void endEvent() {
		//nothing happend at the end of this event
	}

	
}
