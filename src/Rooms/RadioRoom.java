package Rooms;

import java.util.ArrayList;

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
		
		this.setState("free");
	}
	public RadioRoom(ED ed){
		super();
		
		RadioRoom.compteurRadioRoomId += 1;
		this.setId(RadioRoom.compteurRadioRoomId);
		
		this.setEd(ed);
		this.setName("RadioRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
		this.setDist("Unif");
		
		this.setState("free");
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
		
		
	}
	@Override
	public void removeOccupant(Patient patient) {
		this.patient = null;
		this.setState("free");
		
		
		ED edp = this.getEd();

		
		//change patient db state : he goes to the WaitingRoom with the state radio tested
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
		
		this.patient.setHistory("(RadioTested, "+ this.startTime.toString() + "), ");
	}
	
	public void endRadioTesting(){
		TimeStamp time = new TimeStamp();
		this.patient.setHistory("(Test End, "+ time.toString() + "), ");
		this.removeOccupant(this.patient);
	}
	
	@Override
	public void setState(String state){
		ArrayList<ArrayList<RadioRoom>> dbRadioRoom = this.ed.getDbRadioRoom();
		
		for (int i = 0; i<dbRadioRoom.size(); i++){
			dbRadioRoom.get(i).remove(this);
		}
		//add the MRIROOM to the state MRIROOM db
		if (state.equals("free")){
			this.ed.getDbRadioRoom().get(0).add(this);
			this.state = state;
		}
		else if (state.equals("occupied")){
			this.ed.getDbRadioRoom().get(1).add(this);
			this.state = state;
		}
		else{
			System.out.println("cet état n'existe pas");
		}
	}

}
