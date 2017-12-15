package Events;

import Emergency.ED;
import HR.Patient;
import Rooms.RadioRoom;

public class RadioExamination extends Event {
	
	/**
	 * The {@link Patient} who suffers this event
	 */
	private Patient patient;
	/**
	 * The MRIRoom of this Event
	 */
	private RadioRoom radioRoom;
	
	
	/**
	 * Event radio examination, it calculate end and start time
	 * @param patient
	 * @param ed
	 * @param mriRoom
	 * @see RadioRoom#updatePatientCharge(Patient)
	 * @see RadioRoom#radioTesting()()
	 */
	public RadioExamination(Patient patient, ED ed, RadioRoom radioRoom) {
		this.setEd(ed);
		this.setPatient(patient);
		this.setRadioRoom(radioRoom);

		this.getRadioRoom().addOccupant(this.getPatient());
		this.getRadioRoom().updatePatientCharge(patient);
		this.getRadioRoom().radioTesting();
		this.setStartTime(radioRoom.getStartTime());
		this.setDuration((int) radioRoom.getDuration());
		this.setEndTime(radioRoom.getEndTime());
		
		System.out.println("\nEVENT : Radio Test --- ED : " + this.getEd().getName() + " Patient " + this.getPatient().getSeverityLevel() + " : " + this.getPatient().getName() + "  Room : " + this.getRadioRoom().getName());
	}
	/**
	 * End the event radio examination
	 * @see RadioRoom#endRadioTesting()
	 */
	@Override
	public void endEvent() {
		System.out.println("\nEVENT END : Radio Test --- ED : " + this.getEd().getName() + " Patient " + this.getPatient().getSeverityLevel() + " : " + this.getPatient().getName() + "  Room : " + this.getRadioRoom().getName());
		this.getRadioRoom().endRadioTesting();
	}
	
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public RadioRoom getRadioRoom() {
		return radioRoom;
	}
	public void setRadioRoom(RadioRoom radioRoom) {
		this.radioRoom = radioRoom;
	}

}
