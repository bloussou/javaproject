package Rooms;

import java.util.ArrayList;

import Emergency.ED;
import HR.Patient;
import HR.Physician;

public class BoxRoom extends Room{
	
	/**
	 * a static int to give a unique id to each BoxRoom
	 */
	private static int compteurBoxRoomId;
	
	/**
	 * The patient occupying this BoxRoom (all Severity-Level accepted)
	 */
	private Patient patient;
	
	/**
	 * The physician occupying this BoxRoom
	 */
	private Physician physician;
	

	/**
	 * Create a BoxRoom with those parameters
	 * @param ed
	 * @param name
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see BoxRoom#setEd(ED)
	 * @see BoxRoom#setName(String)
	 */
	public BoxRoom(ED ed, String name){
		super();
		this.setCapacity(1);  // the default value of capacity is one
		BoxRoom.compteurBoxRoomId += 1;
		this.setId(BoxRoom.compteurBoxRoomId);
		
		this.setEd(ed);
		this.setName(name);
		this.setCapacity(1);
		
		this.setState("free");
	}
	
	
	/**
	 * Create a BoxRoom named 'BoxRoom N' with those parameters
	 * @param ed
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see BoxRoom#setEd(ED)
	 * @see BoxRoom#setName(String)
	 * @see BoxRoom#addOccupant(Patient)
	 */
	public BoxRoom(ED ed){
		super();
		
		BoxRoom.compteurBoxRoomId += 1;
		this.setId(BoxRoom.compteurBoxRoomId);
		
		this.setEd(ed);
		this.setName("BoxRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
		
		this.setState("free");
	}
	
	
	@Override
	public void addOccupant(Patient patient){
		this.patient = patient;
		this.setState("occupied");
	}
	
	@Override
	public void removeOccupant(Patient patient){
		this.patient = null;
		this.setState("free");
	}
	
	
	@Override
	public void construct(){
		
	}
	@Override
	public void updatePatientCharge(Patient patient){
		if (this.patient == patient){
			patient.setCharges(patient.getCharges()+this.getCost());
		}
	}
	
	@Override
	public void setState(String state){
		ArrayList<ArrayList<BoxRoom>> dbBoxRoom = this.ed.getDbBoxRoom();
		
		for (int i = 0; i<dbBoxRoom.size(); i++){
			dbBoxRoom.get(i).remove(this);
		}
		//add the box room to the state box room db
		if (state.equals("free")){
			this.ed.getDbBoxRoom().get(0).add(this);
			this.state = state;
		}
		else if (state.equals("occupied")){
			this.ed.getDbBoxRoom().get(1).add(this);
			this.state = state;
		}
		else{
			System.out.println("cet état n'existe pas");
		}
	}

	
	
	public static int getCompteurBoxRoomId() {
		return compteurBoxRoomId;
	}
	public static void setCompteurBoxRoomId(int compteurBoxRoomId) {
		BoxRoom.compteurBoxRoomId = compteurBoxRoomId;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}
	
	public Patient getPatient() {
		return patient;
	}
	public Physician getPhysician() {
		return physician;
	}

	
		
	
}
