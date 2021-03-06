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
		this.patient.getLocation().removeOccupant(this.patient);
		
		this.physicianAlreadyConsulted = (patient.getPhysician()== this.physician);
		

		if(this.physicianAlreadyConsulted){
			System.out.println("\nEVENT : Final Consultation --- ED : " + this.getEd().getName() + " Patient " + this.getPatient().getSeverityLevel() + " : " + this.getPatient().getName() + "  Physician : " + this.getPhysician().getName() + "  Room : " + this.getTargetRoom().getName());
			this.setDuration(2);
		}else {
			System.out.println("\nEVENT : Consultation --- ED : " + this.getEd().getName() + " Patient " + this.getPatient().getSeverityLevel() + " : " + this.getPatient().getName() + "  Physician : " + this.getPhysician().getName() + "  Room : " + this.getTargetRoom().getName());
			this.setDuration((int) Uniform.randSample(5, 20));
			physician.handleNewPatient(patient, targetRoom);
		}
		
		this.setEndTime(new TimeStamp(this.getStartTime().getTimeStamp() + this.getDuration()));
	}
	
	/**
	 * Processing the actions which have to be performed at the end of the consultation :
	 * <li>{@link Consultation#physician} prescribes a test to the {@link Consultation#patient} if it is necesary</li>
	 */
	@Override
	public void endEvent() {
		if(this.physicianAlreadyConsulted){
			System.out.println("\nEVENT END : Final Consultation --- ED : " + this.getEd().getName() + " Patient " + this.getPatient().getSeverityLevel() + " : " + this.getPatient().getName() + "  Physician : " + this.getPhysician().getName() + "  Room : " + this.getTargetRoom().getName());
			this.physician.emitVerdict(this.patient);
		}else {
			System.out.println("\nEVENT END : Consultation --- ED : " + this.getEd().getName() + " Patient " + this.getPatient().getSeverityLevel() + " : " + this.getPatient().getName() + "  Physician : " + this.getPhysician().getName() + "  Room : " + this.getTargetRoom().getName());
			this.physician.prescribe(this.patient, this.targetRoom);
		}
		//get patient out of the room
		this.targetRoom.removeOccupant(this.patient);
	}

	

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	public boolean isPhysicianAlreadyConsulted() {
		return physicianAlreadyConsulted;
	}

	public void setPhysicianAlreadyConsulted(boolean physicianAlreadyConsulted) {
		this.physicianAlreadyConsulted = physicianAlreadyConsulted;
	}

	public Room getTargetRoom() {
		return targetRoom;
	}

	public void setTargetRoom(Room targetRoom) {
		this.targetRoom = targetRoom;
	}
}
