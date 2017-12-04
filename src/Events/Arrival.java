package Events;

import Emergency.*;
import HR.*;

public class Arrival extends Event{

	/**
	 * The patient which is associated to this instance of the event arrival
	 */
	private Patient patient;
	
	
	/**
	 * Creation of an Arrival :
	 * <li>patient associated to this instance of arrival</li>
	 * <li>set the timeStamp of this arrival</li>
	 * @param ed
	 * @param patient
	 * @see Event#setStartTime(TimeStamp)
	 * @see Event#setDuration(int)
	 * @see Event#setEndTime(TimeStamp)
	 */
	public Arrival(ED ed, Patient patient){
		this.setEd(ed);
		this.patient = patient;
		this.setStartTime(new TimeStamp());
		this.setEndTime(new TimeStamp(this.getDuration()));
	}

	/**
	 * Processing the actions which have to be performed at the end of the event :
	 * <li>Nothing has to happen because an arrival is instantaneous</li>
	 */
	@Override
	public void endEvent() {
		//nothing happens at the end of this event
	}

	
}
