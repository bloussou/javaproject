package Rooms;

import java.util.ArrayList;

import Emergency.ED;
import Events.TimeStamp;
import HR.Patient;
import Proba.Uniform;

public class MRIRoom extends Room {

	/**
	 * a static int to give a unique id to each MRIdRoom
	 */
	private static int compteurMRIRoomId;
	/**
	 * The patient currently treated in this MRIRoom
	 */
	private Patient patient;
	/**
	 * The TimeStamp corresponding to the time at the beginning of the MRI test
	 */
	private TimeStamp startTime;
	/**
	 * The TimeStamp corresponding to the time at the end of the MRI test
	 */
	private TimeStamp endTime;
	/** 
	 * The duration (int) in minutes of the test
	 */
	private double duration;
	
	
	
	/**
	 * Create an MRIRoom with those parameters
	 * @param ed
	 * @param name
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see MRIRoom#setEd(ED)
	 * @see MRIoom#setName(String)
	 * @see MRIRoom#setDist(String)
	 * @see MRIRoom#setDistParam(float[])
	 */
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
	
	/**
	 * Create a MRIRoom named 'MRIRoom N' with those parameters
	 * @param ed
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see MRIRoom#setEd(ED)
	 * @see MRIRoom#setName(String)
	 * @see MRIRoom#setDist(String)
	 * @see MRIRoom#setDistParam(float[])
	 */
	public MRIRoom(ED ed){
		super();
		
		MRIRoom.compteurMRIRoomId += 1;
		this.setId(MRIRoom.compteurMRIRoomId);
		
		this.setEd(ed);
		this.setName("MRIRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
		this.setDist("Unif");

		this.setState("free");
	}
	

	/**
	 * Update the patient's charge with the cost of this room's MRI test
	 */
	@Override
	public void updatePatientCharge(Patient patient) {
		if (this.patient == patient){
			patient.setCharges(patient.getCharges()+this.getCost());
			}	
	}
	
	
	/**
	 * Process the MRI test.
	 * <li>Save the startTime</li>
	 * <li>Set the duration of this test to Unif.randSample(30,70) ... {@link Uniform#randSample(double, double)}</li>
	 * <li>Set the endTime</li>
	 * <li>Update the patient's history</li>
	 */
	public void mriTesting(){
		this.startTime = new TimeStamp() ;
		this.setDuration(Uniform.randSample(30,70));
		int duree = (int)(this.getDuration());
		this.endTime = new TimeStamp(this.getStartTime().getTimeStamp() + duree);
		this.getPatient().setState("testing");
		this.patient.setHistory("(Start Test, "+ this.startTime.toString() + "), ");
		System.out.println(patient.getHistory());
	}
	
	
	/**
	 * End the MRI test and update the patient's history
	 */
	public void endMRITesting(){
		TimeStamp time = new TimeStamp();
		this.patient.setHistory("(Test End, "+ time.toString() + "), ");
		
		this.patient.setState("mriTested");
		System.out.println(patient.getHistory());
		this.removeOccupant(this.getPatient());
		
	}
	
	
	

	@Override
	public void addOccupant(Patient patient) {
		this.patient = patient;
		this.patient.setLocation(this);
		this.setState("occupied");
		

	}
	@Override
	public void removeOccupant(Patient patient) {
		
		this.setState("free");
		this.patient = null;
		
	}
	@Override
	public void construct() {
		// TODO Auto-generated method stub
		
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

