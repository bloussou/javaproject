package Rooms;

import java.util.ArrayList;

import Emergency.ED;
import Events.TimeStamp;
import HR.Patient;
import Proba.Uniform;

public class RadioRoom extends Room{

	/**
	 * a static int to give a unique id to each RadiodRoom
	 */
	private static int compteurRadioRoomId;
	
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
	 * Create a RadioRoom with those parameters
	 * @param ed
	 * @param name
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see RadioRoom#setEd(ED)
	 * @see Radiooom#setName(String)
	 * @see RadioRoom#setDist(String)
	 * @see RadioRoom#setDistParam(float[])
	 */
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
	
	/**
	 * Create a RadioRoom called 'RadioRoom N' with those parameters
	 * @param ed
	 * @param name
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see RadioRoom#setEd(ED)
	 * @see Radiooom#setName(String)
	 * @see RadioRoom#setDist(String)
	 * @see RadioRoom#setDistParam(float[])
	 */
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
		
		this.setState("free");
		this.patient = null;
		
	}
	
	
	@Override
	public void construct() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Update the patient's charge with the cost of this room's Radio test
	 */
	@Override
	public void updatePatientCharge(Patient patient) {
		if (this.patient == patient){
			patient.setCharges(patient.getCharges()+this.getCost());
			}
		
	}
	
	/**
	 * Process the Radio test.
	 * <li>Save the startTime</li>
	 * <li>Set the duration of this test to Unif.randSample(10,20) ... {@link Uniform#randSample(double, double)}</li>
	 * <li>Set the endTime</li>
	 * <li>Update the patient's history</li>
	 */
	public void radioTesting(){
		this.startTime = new TimeStamp() ;
		this.setDuration(new Uniform().randSample(10,20));

		
		int duree = (int)(this.getDuration());
		this.endTime = new TimeStamp(duree);
		
			
		this.getPatient().setState("testing");
		this.patient.setHistory("(RadioTested, "+ this.startTime.toString() + "), ");
	}
	
	/**
	 * End the Radio test and update the patient's history
	 */
	public void endRadioTesting(){
		TimeStamp time = new TimeStamp();
		this.patient.setHistory("(Test End, "+ time.toString() + "), ");
		
		this.patient.setState("radioTested");
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
