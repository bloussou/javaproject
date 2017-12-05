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
		
		
		this.getMriRoom().updatePatientCharge(patient);
		this.getMriRoom().mriTesting();
		this.setStartTime(mriRoom.getStartTime());
		this.setDuration((int) this.getMriRoom().getDuration());
		this.setEndTime(mriRoom.getEndTime());
		
	}
	/**
	 * End the event mri examination
	 * @see MRIRoom#endMRITesting()
	 */
	@Override
	public void endEvent() {
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
