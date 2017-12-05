package Events;

import Emergency.ED;
import HR.Patient;
import Rooms.BloodRoom;

public class BloodExamination extends Event {
	/**
	 * The {@link Patient} who suffers this event
	 */
	private Patient patient;
	/**
	 * The MRIRoom of this Event
	 */
	private BloodRoom bloodRoom;
	
	/**
	 * Event blood examination, it calculate end and start time
	 * @param patient
	 * @param ed
	 * @param mriRoom
	 * @see BloodRoom#updatePatientCharge(Patient)
	 * @see BloodRoom#bloodTesting()
	 */
	public BloodExamination(Patient patient, ED ed, BloodRoom bloodRoom) {
		this.setEd(ed);
		this.setPatient(patient);
		this.setBloodRoom(bloodRoom);
		
		this.getBloodRoom().updatePatientCharge(patient);
		this.getBloodRoom().bloodTesting();
		this.setStartTime(this.getBloodRoom().getStartTime());
		this.setDuration((int) this.getBloodRoom().getDuration());
		this.setEndTime(this.getBloodRoom().getEndTime());
		
	}
	/**
	 * End the event blood examination
	 * @see BloodRoom#endBloodTesting()
	 */
	@Override
	public void endEvent() {
		this.getBloodRoom().endBloodTesting();
	}
	
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public BloodRoom getBloodRoom() {
		return bloodRoom;
	}
	public void setBloodRoom(BloodRoom bloodRoom) {
		this.bloodRoom = bloodRoom;
	}

}
