package Rooms;

import java.util.ArrayList;

import Emergency.ED;
import HR.*;

public class ShockRoom extends Room{

	/**
	 * a static int to give a unique id to each ShockRoom
	 */
	private static int compteurShockRoomId;
	/**
	 * The patient occupying this ShockRoom (Severity-Level in {'L1','L2'})
	 */
	private Patient patient;
	/**
	 * The physician occupying this ShockRoom
	 */
	private Physician physician;
	
	
	
	/**
	 * Create a ShockRoom with those parameters
	 * @param ed
	 * @param name
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see ShockRoom#setEd(ED)
	 * @see ShockRoom#setName(String)
	 */
	public ShockRoom(ED ed, String name){
		super();
		
		ShockRoom.compteurShockRoomId += 1;
		this.setId(ShockRoom.compteurShockRoomId);
		
		this.setEd(ed);
		this.setName(name);
		this.setCapacity(1);
		
		this.setState("free");
	}

	/**
	 * Create a ShockRoom named 'ShockRoom N' with those parameters
	 * @param ed
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see ShockRoom#setEd(ED)
	 * @see ShockRoom#setName(String)
	 * @see ShockRoom#addOccupant(Patient)
	 */
	public ShockRoom(ED ed){
		super();
		
		ShockRoom.compteurShockRoomId += 1;
		this.setId(ShockRoom.compteurShockRoomId);
		
		this.setEd(ed);
		this.setName("ShockRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
		
		this.setState("free");
	}
	
	

	@Override	
	public void addOccupant(Patient patient) {
		this.patient = patient;		
		patient.setLocation(this);
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
		System.out.println("Construction d'une ShockRoom : \n" + this.toString());
		
	}
	
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
		ArrayList<ArrayList<ShockRoom>> dbShockRoom = this.ed.getDbShockRoom();
		
		for (int i = 0; i<dbShockRoom.size(); i++){
			dbShockRoom.get(i).remove(this);
		}
		//add the shock room to the state shock room db
		if (state.equals("free")){
			this.ed.getDbShockRoom().get(0).add(this);
			this.state = state;
		}
		else if (state.equals("occupied")){
			this.ed.getDbShockRoom().get(1).add(this);
			this.state = state;
		}
		else{
			System.out.println("cet �tat n'existe pas");
		}
	}
	
	
	
	public static int getCompteurShockRoomId() {
		return compteurShockRoomId;
	}
	public static void setCompteurShockRoomId(int compteurShockRoomId) {
		ShockRoom.compteurShockRoomId = compteurShockRoomId;
	}
	public Patient getPatient() {
		return patient;
	}
	public Physician getPhysician() {
		return physician;
	}
	public void setPhysician(Physician physician) {
		this.physician = physician;
	}
	
}
