package Events;

import Emergency.ED;
import HR.Nurse;
import HR.Patient;
import HR.Transporter;
import Rooms.Room;

public class Transport_Transporter extends Event {

	/**
	 * The patient which is associated to this instance of the event Transport_Transporter
	 */
	private Patient patient;
	
	/**
	 * The nurse which is associated to this instance of the event Transport_Transporter
	 */
	private Transporter transporter;
	
	/**
	 * The WaitingRoom which is associated to this instance of the event Transport_Transporter
	 */
	private Room targetRoom;
	
	/**
	 * Creation of a Transport_Transporter event :
	 * <li>ED associated to this instance of transport</li>
	 * <li>patient associated to this instance of transport</li>
	 * <li>nurse associated to this instance of transport</li>
	 * <li>set the timeStamp of this transport</li>
	 * <li>perform the transport of {@link Transport_Transporter#patient} by {@link Transport_Transporter#Transporter} 
	 * to the room {@link Transport_Transporter#targetRoom}</li>
	 * @param ed
	 * @param patient
	 * @param nurse
	 * @param targetRoom
	 * @see Event#setStartTime(TimeStamp)
	 * @see Event#setDuration(int)
	 * @see Event#setEndTime(TimeStamp)
	 * @see Transporter#transport(Patient)
	 */
	public Transport_Transporter(ED ed, Patient patient, Transporter transporter, Room targetRoom){
		this.setEd(ed);
		this.patient = patient;
		this.setTransporter(transporter);
		this.setTargetRoom(targetRoom);


		
		this.transporter.transport(patient, targetRoom);
		
		this.setStartTime(this.transporter.getStartTime());
		this.setEndTime(this.transporter.getEndTime());
	}

	
	
	/**
	 * Processing the actions which have to be performed at the end of the event :
	 * <li>{@link Transport_Transporter#transporter} drops {@link Transport_Transporter#patient} in 
	 * {@link Transport_Transporter#targetRoom}</li>
	 */
	@Override
	public void endEvent() {
		
		this.transporter.dropPatient(patient);
	}



	public Transporter getTransporter() {
		return transporter;
	}



	public void setTransporter(Transporter transporter) {
		this.transporter = transporter;
	}



	public Room getTargetRoom() {
		return targetRoom;
	}



	public void setTargetRoom(Room targetRoom) {
		this.targetRoom = targetRoom;
	}
}
