package Events;

import Emergency.ED;
import HR.*;
import Proba.Uniform;
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
	private Room targetRoom;

	
	/**
	 * Creation of a Transport_Nurse event :
	 * <li>ED associated to this instance of transport</li>
	 * <li>patient associated to this instance of transport</li>
	 * <li>nurse associated to this instance of transport</li>
	 * <li>set the timeStamp of this transport</li>
	 * <li>perform the transport of {@link Transport_Nurse#patient} by {@link Transport_Nurse#nurse} 
	 * to the room {@link Transport_Nurse#targetRoom}</li>
	 * @param ed
	 * @param patient
	 * @param nurse
	 * @param targetRoom
	 * @see Event#setStartTime(TimeStamp)
	 * @see Event#setDuration(int)
	 * @see Event#setEndTime(TimeStamp)
	 * @see Nurse#transport(Patient)
	 */
	public Transport_Nurse(ED ed, Patient patient, Nurse nurse, Room targetRoom){
		this.setEd(ed);
		this.patient = patient;
		this.nurse = nurse;
		this.targetRoom = targetRoom;

		this.setStartTime(new TimeStamp());
		this.setDuration(2);
		this.setEndTime(new TimeStamp(this.getStartTime().getTimeStamp() + this.getDuration()));
		
		this.nurse.transport(patient, targetRoom);
		this.targetRoom.addOccupant(patient);
		
		System.out.println("\nEVENT : Transport to WaitingRoom --- ED : " + this.getEd().getName() + " Patient " + this.getPatient().getSeverityLevel() + " : " + this.getPatient().getName() + "  Nurse : " + this.getNurse().getName() + "  targetRoom : " + this.getTargetRoom().getName());
	}
	
	/**
	 * Processing the actions which have to be performed at the end of the event :
	 * <li>{@link Transport_Nurse#nurse} drops {@link Transport_Nurse#patient} in 
	 * {@link Transport_Nurse#targetRoom}</li>
	 */
	@Override
	public void endEvent() {
		System.out.println("\nEVENT END : Transport to WaitingRoom --- ED : " + this.getEd().getName() + " Patient " + this.getPatient().getSeverityLevel() + " : " + this.getPatient().getName() + "  Nurse : " + this.getNurse().getName() + "  targetRoom : " + this.getTargetRoom().getName());
		this.nurse.dropPatient(patient);
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public Room getTargetRoom() {
		return targetRoom;
	}

	public void setTargetRoom(Room targetRoom) {
		this.targetRoom = targetRoom;
	}
	
	
	
}
