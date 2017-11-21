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
		
		this.setStartTime(new TimeStamp());
		Time time = Time.getInstanceTime();
		this.setEndTime(new TimeStamp(this.getDuration()));
		
		physician.handleNewPatient(patient);
		
		//set the history of the patient
		patient.setHistory("(visited, "+ this.getStartTime().toString() + "), ");
		
	}
	
	
	
	@Override
	public void endEvent() {
		//nothing happens at the end of these events
		
	}

}
