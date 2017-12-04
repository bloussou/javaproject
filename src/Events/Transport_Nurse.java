package Events;

import Emergency.ED;
import HR.*;
import Rooms.*;

public class Transport_Nurse extends Event{

	/**
	 * The patient which is associated to this instance of the event Transport_Nurse
	 */
	private Patient patient;
	
	/**
	 * The nurse which is associated to this instance of the event Transport_Nurse
	 */
	private Nurse nurse;
	
	/**
	 * The WaitingRoom which is associated to this instance of the event Transport_Nurse
	 */
	private WaitingRoom targetRoom;
	
	
	public Transport_Nurse(ED ed, Patient patient, Nurse nurse, WaitingRoom targetRoom){
		this.setEd(ed);
		this.patient = patient;
		this.nurse = nurse;
		this.targetRoom = targetRoom;
		this.setDuration(2);
		
		this.setStartTime(new TimeStamp());
		Time time = Time.getInstanceTime();
		this.setEndTime(new TimeStamp(this.getDuration()));
		
		this.nurse.transport(patient, targetRoom);
	}

	
	
	
	@Override
	public void endEvent() {
		//nothing happens at the end of these events
		
	}
	
}
