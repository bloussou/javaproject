package Events;

import Emergency.ED;
import HR.*;
import Proba.Uniform;
import Rooms.*;

public class Consultation extends Event{

	private Patient patient;
	private Physician physician;
	private Room targetRoom;
	
	
	public Consultation(ED ed, Patient patient, Physician physician, Room targetRoom){
		this.setEd(ed);
		this.patient = patient;
		this.physician = physician;
		this.targetRoom = targetRoom;
		
		this.setStartTime(new TimeStamp());
		this.setDuration((int) Uniform.randSample(5, 20));
		this.setEndTime(new TimeStamp(this.getDuration()));
		
		physician.handleNewPatient(patient, targetRoom);
	}
	
	
	
	@Override
	public void endEvent() {
		
		physician.prescribe(patient, targetRoom);
		
	}

}
