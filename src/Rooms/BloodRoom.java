package Rooms;

import java.util.ArrayList;

import Emergency.ED;
import Events.TimeStamp;
import HR.Patient;
import Proba.*;

public class BloodRoom extends Room{

	
	/**
	 * a static int to give a unique id to each BloodRoom
	 */
	private static int compteurBloodRoomId;
	/**
	 * The patient currently treated in this BloodRoom
	 */
	private Patient patient;
	/**
	 * The TimeStamp corresponding to the time at the beginning of the blood test
	 */
	private TimeStamp startTime;
	/**
	 * The TimeStamp corresponding to the time at the end of the blood test
	 */
	private TimeStamp endTime;
	/** 
	 * The duration (int) in minutes of the test
	 */
	private double duration;
	
	
	
	/**
	 * Create a BloodRoom with those parameters
	 * @param ed
	 * @param name
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see BloodRoom#setEd(ED)
	 * @see BloodRoom#setName(String)
	 * @see BloodRoom#setDist(String)
	 * @see BloodRoom#setDistParam(float[])
	 */
	public BloodRoom(ED ed, String name, String distribution, float[] distParam){
		super();
		
		BloodRoom.compteurBloodRoomId += 1;
		this.setId(BloodRoom.compteurBloodRoomId);
		
		this.setEd(ed);
		this.setName(name);
		this.setCapacity(1);
		this.setDist(distribution);
		this.setDistParam(distParam);
		this.setState("free");
		
	}
	
	/**
	 * Create a BloodRoom named 'BloodRoom N' with those parameters
	 * @param ed
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see BloodRoom#setEd(ED)
	 * @see BloodRoom#setName(String)
	 * @see BloodRoom#setDist(String)
	 * @see BloodRoom#setDistParam(float[])
	 */
	public BloodRoom(ED ed, String distribution, float[] distParam){
		super();
		
		BloodRoom.compteurBloodRoomId += 1;
		this.setId(BloodRoom.compteurBloodRoomId);
		
		this.setEd(ed);
		this.setName("BloodRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
		this.setDist(distribution);
		this.setDistParam(distParam);
		this.setState("free");
	}
	
	
	
	/**
	 * Process the blood test.
	 * <li>Save the startTime</li>
	 * <li>Set the duration of this test to Unif.randSample(15,90) ... {@link Uniform#randSample(double, double)}</li>
	 * <li>Set the endTime</li>
	 * <li>Update the patient's history</li>
	 */
	public void bloodTesting(){
		this.startTime = new TimeStamp() ;
		
		
		if (this.getDist().equalsIgnoreCase("UNIFORM")){
			this.setDuration(Uniform.randSample(this.getDistParam()[0],this.getDistParam()[1]));
		}
		else if (this.getDist().equalsIgnoreCase("GAMMA")){
			this.setDuration(Gamma.randSample(this.getDistParam()[0],this.getDistParam()[1]));
		}
		else if (this.getDist().equalsIgnoreCase("EXP")){
			this.setDuration(Exp.randSample(this.getDistParam()[0]));
		}
		else if (this.getDist().equalsIgnoreCase("LOGNORM")){
			this.setDuration(LogNorm.randSample(this.getDistParam()[0],this.getDistParam()[1]));
		}
		else{
			System.out.println("Unable to blood Test --> unknown distribution");
		}
		
		
		int duree = (int)(this.getDuration());
		this.endTime = new TimeStamp(this.getStartTime().getTimeStamp() + duree);

		this.getPatient().setState("testing");
		this.patient.setHistory("(bloodtested, "+ this.startTime.toString() + "), ");
	}
	
	/**
	 * End the blood test and update the patient's history
	 */
	public void endBloodTesting(){
		TimeStamp time = new TimeStamp();
		this.patient.setHistory("(Test End, "+ time.toString() + "), ");
		
		this.patient.setState("bloodTested");
		this.removeOccupant(this.getPatient());
		
	}
	
	
	@Override
	public void addOccupant(Patient patient) {
		this.patient = patient;
		this.patient.setLocation(this);
		this.updatePatientCharge(patient);
		this.setState("occupied");
	}
	
	@Override
	public void removeOccupant(Patient patient) {
		this.patient = null;
		this.setState("free");
		
	}
	
	@Override
	public void construct() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Update the patient's charge with the cost of this room's blood test
	 */
	@Override
	public void updatePatientCharge(Patient patient) {
		Double discount;
		if (patient.getHealthInsurance().equalsIgnoreCase("SILVER")){discount = 0.5;}
		else if (patient.getHealthInsurance().equalsIgnoreCase("GOLD")){discount = 0.8;}
		else {discount = 0.0;}
		
		if (this.patient == patient){
			patient.setCharges(patient.getCharges()+(1-discount)*this.getCost());
		}
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
			System.out.println("cet �tat n'existe pas");
		}
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
		
}
