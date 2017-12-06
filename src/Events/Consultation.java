package Events;

import Emergency.ED;
import HR.*;
import Proba.Uniform;
import Rooms.*;

public class Consultation extends Event{

	/**
	 * The patient which is associated to this instance of the event Consultation
	 */
	private Patient patient;
	
	/**
	 * The physician which is associated to this instance of the event Consultation
	 */
	private Physician physician;
	
	private boolean physicianAlreadyConsulted;
	/**
	 * The consultation room {BoxRoom, ShockRoom} which is associated to this instance of the event Consultation
	 */
	private Room targetRoom;
	
	
	/**
	 * Creation of a Consultation event :
	 * <li>ED associated to this instance of Consultation</li>
	 * <li>patient associated to this instance of Consultation</li>
	 * <li>physician associated to this instance of Consultation</li>
	 * <li>set the start time of the consultation</li>
	 * <li>generate duration thanks to the {@link Uniform} method</li>
	 * <li>set the end time of the consultation</li>
	 * <li>If the patient has never seen this physician, the physician handles this patient as a new Patient</li>
	 * <li>Else if the patient has already seen him, it means that he has already passed a prescribed test, and 
	 * is just waiting for a verdict from his physician </li>
	 * @param ed
	 * @param patient
	 * @param physician
	 * @param targetRoom
	 * @see Uniform
	 * @see Physician#handleNewPatient(Patient, Room)
	 */
	public Consultation(ED ed, Patient patient, Physician physician, Room targetRoom){
		this.setEd(ed);
		this.patient = patient;
		this.physician = physician;
		this.targetRoom = targetRoom;
		
		this.setStartTime(new TimeStamp());
		
		this.physicianAlreadyConsulted = (patient.getPhysician()== this.physician);
		
		
		
		if(this.physicianAlreadyConsulted){
			this.setDuration(2);
		}else {
			this.setDuration((int) Uniform.randSample(5, 20));
			physician.handleNewPatient(patient, targetRoom);
		}
		
		this.setEndTime(new TimeStamp(this.getDuration()));

	}
	
	/**
	 * Processing the actions which have to be performed at the end of the consultation :
	 * <li>{@link Consultation#physician} prescribes a test to the {@link Consultation#patient} if it is necesary</li>
	 */
	@Override
	public void endEvent() {
		
		if(this.physicianAlreadyConsulted){
			this.physician.emitVerdict(this.patient);
		}else {
			this.physician.prescribe(this.patient, this.targetRoom);
		}
	}

}
