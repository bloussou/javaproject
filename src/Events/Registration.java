package Events;

import Emergency.ED;
import HR.*;

public class Registration extends Event{

	/**
	 * The patient which is associated to this instance of the event Registration
	 */
	private Patient patient;
	
	/**
	 * The nurse which is associated to this instance of the event Registration
	 */
	private Nurse nurse;
	
	/**
	 * Creation of a Registration :
	 * <li>ED associated to this instance of registration<li>
	 * <li>patient associated to this instance of registration<li>
	 * <li>nurse associated to this instance of registration<li>
	 * <li>set the timeStamp of this registration<li>
	 * <li>perform the registration of {@link Registration#patient} by {@link Registration#nurse}<li>
	 * @param ed
	 * @param patient
	 * @param nurse
	 * @see Event#setStartTime(TimeStamp)
	 * @see Event#setDuration(int)
	 * @see Event#setEndTime(TimeStamp)
	 * @see Nurse#register(Patient)
	 */
	public Registration(ED ed, Patient patient, Nurse nurse){
		this.setEd(ed);
		this.patient = patient;
		this.nurse = nurse;
		
		this.setStartTime(new TimeStamp());
		Time time = Time.getInstanceTime();
		this.setEndTime(new TimeStamp(this.getDuration()));
		
		this.nurse.register(patient);
	}
	
	
	@Override
	public void endEvent() {
		//nothing happens at the end of these events
		
	}
	
}
