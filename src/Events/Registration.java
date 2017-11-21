package Events;

import Emergency.ED;
import HR.*;

public class Registration extends Event{

	private Patient patient;
	private Nurse nurse;
	
	public Registration(ED ed, Patient patient, Nurse nurse){
		this.setEd(ed);
		this.patient = patient;
		this.nurse = nurse;
		
		this.setStartTime(new TimeStamp());
		Time time = Time.getInstanceTime();
		this.setEndTime(new TimeStamp(this.getDuration()));
		
		this.nurse.register(patient);
	}
	
	
	@Override
	public void endEvent() {
		//nothing happens at the end of these events
		
	}
	
}
