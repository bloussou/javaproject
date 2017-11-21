package Events;

import Emergency.ED;
import HR.*;
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
		
		physician.handleNewPatient(patient);
		
		
	}
	
	
	
	@Override
	public void endEvent() {
		//nothing happens at the end of these events
		
	}

}
