package Events;

import Emergency.ED;
import HR.Patient;
import Rooms.MRIRoom;

public class MRIExamination extends Event {
	/**
	 * The {@link Patient} who suffers this event
	 */
	private Patient patient;
	/**
	 * The MRIRoom of this Event
	 */
	private MRIRoom mriRoom;
	
	/**
	 * Event mri examination, it calculate end and start time
	 * @param patient
	 * @param ed
	 * @param mriRoom
	 * @see MRIRoom#updatePatientCharge(Patient)
	 * @see MRIRoom#mriTesting()
	 */
	public MRIExamination(Patient patient, ED ed, MRIRoom mriRoom) {
		this.setEd(ed);
		this.setPatient(patient);
		this.setMriRoom(mriRoom);
		
		this.getMriRoom().addOccupant(this.patient);
		
		this.getMriRoom().updatePatientCharge(patient);
		this.getMriRoom().mriTesting();
		this.setStartTime(mriRoom.getStartTime());
		this.setDuration((int) mriRoom.getDuration());
		this.setEndTime(mriRoom.getEndTime());
		
		System.out.println("\nEVENT : MRI Test --- ED : " + this.getEd().getName() + " Patient " + this.getPatient().getSeverityLevel() + " : " + this.getPatient().getName() + "  Room : " + this.getMriRoom().getName());
	}
	/**
	 * End the event mri examination
	 * @see MRIRoom#endMRITesting()
	 */
	@Override
	public void endEvent() {
		System.out.println("\nEVENT END : MRI Test --- ED : " + this.getEd().getName() + " Patient " + this.getPatient().getSeverityLevel() + " : " + this.getPatient().getName() + "  Room : " + this.getMriRoom().getName());
		this.getMriRoom().endMRITesting();
	}
	
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public MRIRoom getMriRoom() {
		return mriRoom;
	}
	public void setMriRoom(MRIRoom mriRoom) {
		this.mriRoom = mriRoom;
	}

}
