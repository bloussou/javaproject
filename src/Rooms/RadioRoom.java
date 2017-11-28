package Rooms;

import Emergency.ED;
import Events.TimeStamp;
import HR.Patient;
import Proba.Uniform;

public class RadioRoom extends Room{

	private static int compteurRadioRoomId;
	private Patient patient;
	private TimeStamp startTime;
	private TimeStamp endTime;
	private double duration;
	
	
	public RadioRoom(ED ed, String name){
		super();
		
		RadioRoom.compteurRadioRoomId += 1;
		this.setId(RadioRoom.compteurRadioRoomId);
		
		this.setEd(ed);
		this.setName(name);
		this.setCapacity(1);
		this.setDist("Unif");
		
		ed.getDbRadioRoom().get(0).add(this);
	}
	public RadioRoom(ED ed){
		super();
		
		RadioRoom.compteurRadioRoomId += 1;
		this.setId(RadioRoom.compteurRadioRoomId);
		
		this.setEd(ed);
		this.setName("RadioRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
		this.setDist("Unif");
		
		ed.getDbRadioRoom().get(0).add(this);
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
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	public static int getCompteurRadioRoomId() {
		return compteurRadioRoomId;
	}
	public static void setCompteurRadioRoomId(int compteurRadioRoomId) {
		RadioRoom.compteurRadioRoomId = compteurRadioRoomId;
	}
	public Patient getPatient() {
		return patient;
	}
	
	
	@Override
	public void addOccupant(Patient patient) {
		this.patient = patient;
		this.patient.setLocation(this);
		this.setState("occupied");
		
		ED edp = this.getEd();
		edp.getDbRadioRoom().get(0).remove(this);
		edp.getDbRadioRoom().get(1).add(this);
	}
	@Override
	public void removeOccupant(Patient patient) {
		this.patient = null;
		this.setState("free");
		
		
		ED edp = this.getEd();
		edp.getDbRadioRoom().get(1).remove(this);
		edp.getDbRadioRoom().get(0).add(this);
		
		//change patient db state : he goes to the WaitingRoom
		edp.getDbPatient().get(12).remove(patient);
		edp.getDbPatient().get(3).remove(patient);
		
	}
	@Override
	public void construct() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updatePatientCharge(Patient patient) {
		if (this.patient == patient){
			patient.setCharges(patient.getCharges()+this.getCost());
			}
		
	}
	
	public void radioTesting(){
		this.startTime = new TimeStamp() ;
		this.setDuration(new Uniform().randSample(10,20));
		int duree = (int)(this.getDuration());
		this.endTime = new TimeStamp(duree);
		
		this.patient.setState("radioTested");	
		ED edp = this.getEd();
		edp.getDbPatient().get(10).remove(patient);
		edp.getDbPatient().get(12).add(patient);
		this.patient.setHistory("(RadioTested, "+ this.startTime.toString() + "), ");
	}
	
	public void endMRITesting(){
		TimeStamp time = new TimeStamp();
		this.patient.setHistory("(Test End, "+ time.toString() + "), ");
		this.removeOccupant(this.patient);
	}

}
