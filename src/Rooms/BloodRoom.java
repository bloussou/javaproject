package Rooms;

import java.util.ArrayList;

import Emergency.ED;
import Events.TimeStamp;
import HR.Nurse;
import HR.Patient;
import Proba.Uniform;

public class BloodRoom extends Room{

	private static int compteurBloodRoomId;
	private Patient patient;
	private TimeStamp startTime;
	private TimeStamp endTime;
	private double duration;
	
	
	public BloodRoom(ED ed, String name){
		super();
		
		BloodRoom.compteurBloodRoomId += 1;
		this.setId(BloodRoom.compteurBloodRoomId);
		
		this.setEd(ed);
		this.setName(name);
		this.setCapacity(1);
		this.setDist("Unif");
		this.setState("free");
		
	}
	public BloodRoom(ED ed){
		super();
		
		BloodRoom.compteurBloodRoomId += 1;
		this.setId(BloodRoom.compteurBloodRoomId);
		
		this.setEd(ed);
		this.setName("BloodRoom" + Integer.toString(this.getId()));
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
	public static int getCompteurBloodRoomId() {
		return compteurBloodRoomId;
	}
	public static void setCompteurBloodRoomId(int compteurBloodRoomId) {
		BloodRoom.compteurBloodRoomId = compteurBloodRoomId;
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
		
		//change patient db state : he goes to the WaitingRoom with the state bloodtested
		edp.getDbPatient().get(12).remove(patient);
		edp.getDbPatient().get(3).add(patient);
		
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
	
	public void bloodTesting(){
		this.startTime = new TimeStamp() ;
		this.setDuration(new Uniform().randSample(15,90));
		int duree = (int)(this.getDuration());
		this.endTime = new TimeStamp(duree);
		
		this.patient.setState("bloodTested");
		
		this.patient.setHistory("(bloodtested, "+ this.startTime.toString() + "), ");
	}
	
	public void endBloodTesting(){
		TimeStamp time = new TimeStamp();
		this.patient.setHistory("(Test End, "+ time.toString() + "), ");
		
		
		
		this.removeOccupant(this.getPatient());
		
	}
	
	@Override
	public void setState(String state){
		ArrayList<ArrayList<BloodRoom>> dbBloodRoom = this.ed.getDbBloodRoom();
		
		for (int i = 0; i<dbBloodRoom.size(); i++){
			dbBloodRoom.get(i).remove(this);
		}
		//add the bloodroom to the state nurse db
		if (state.equals("free")){
			this.ed.getDbBloodRoom().get(0).add(this);
			this.state = state;
		}
		else if (state.equals("occupied")){
			this.ed.getDbBloodRoom().get(1).add(this);
			this.state = state;
		}
		else{
			System.out.println("cet état n'existe pas");
		}
	}
	
	
}
