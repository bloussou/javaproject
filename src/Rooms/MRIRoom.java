package Rooms;

import java.util.ArrayList;

import Emergency.ED;
import Events.TimeStamp;
import HR.Patient;
import Proba.Uniform;

public class MRIRoom extends Room {

	private static int compteurMRIRoomId;
	private Patient patient;
	private TimeStamp startTime;
	private TimeStamp endTime;
	private double duration;
	
	
	public MRIRoom(ED ed, String name){
		super();
		
		MRIRoom.compteurMRIRoomId += 1;
		this.setId(MRIRoom.compteurMRIRoomId);
		
		this.setEd(ed);
		this.setName(name);
		this.setCapacity(1);
		this.setDist("Unif");


		this.setState("free");
		
	}
	public MRIRoom(ED ed){
		super();
		
		MRIRoom.compteurMRIRoomId += 1;
		this.setId(MRIRoom.compteurMRIRoomId);
		
		this.setEd(ed);
		this.setName("MRIRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);


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
	public static int getCompteurMRIRoomId() {
		return compteurMRIRoomId;
	}
	public static void setCompteurMRIRoomId(int compteurMRIRoomId) {
		MRIRoom.compteurMRIRoomId = compteurMRIRoomId;
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
		
		//change patient db state : he goes to the WaitingRoom with the state MRITested
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
	
	public void mriTesting(){
		this.startTime = new TimeStamp() ;
		this.setDuration(new Uniform().randSample(30,70));
		int duree = (int)(this.getDuration());
		this.endTime = new TimeStamp(duree);
		
		this.patient.setState("mriTested");	
		
		this.patient.setHistory("(MRItested, "+ this.startTime.toString() + "), ");
	}
	
	public void endMRITesting(){
		TimeStamp time = new TimeStamp();
		this.patient.setHistory("(Test End, "+ time.toString() + "), ");
		this.removeOccupant(this.patient);
	}
	
	@Override
	public void setState(String state){
		ArrayList<ArrayList<MRIRoom>> dbMRIRoom = this.ed.getDbMRIRoom();
		
		for (int i = 0; i<dbMRIRoom.size(); i++){
			dbMRIRoom.get(i).remove(this);
		}
		//add the MRIROOM to the state MRIROOM db
		if (state.equals("free")){
			this.ed.getDbMRIRoom().get(0).add(this);
			this.state = state;
		}
		else if (state.equals("occupied")){
			this.ed.getDbMRIRoom().get(1).add(this);
			this.state = state;
		}
		else{
			System.out.println("cet état n'existe pas");
		}
	}
	
	
}

