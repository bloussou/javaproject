package Events;

import Emergency.ED;

public abstract class Event {

	private ED ed;
	private TimeStamp startTime;
	private TimeStamp endTime;
	private int duration;
	
	public ED getEd() {
		return ed;
	}
	public void setEd(ED ed) {
		this.ed = ed;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public TimeStamp getStartTime() {
		return startTime;
	}
	public void setStartTime(TimeStamp startTime) {
		this.startTime = startTime;
	}
	public TimeStamp getEndTime() {
		return endTime;
	}
	public void setEndTime(TimeStamp endTime) {
		this.endTime = endTime;
	}
	
	
	public abstract void endEvent();
	
	
}
