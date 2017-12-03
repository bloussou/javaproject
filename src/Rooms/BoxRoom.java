package Rooms;

import java.util.ArrayList;

import Emergency.ED;
import HR.Patient;
import HR.Physician;

public class BoxRoom extends Room{
	
	private static int compteurBoxRoomId;
	private Patient patient;
	private Physician physician;
	

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
	public BoxRoom(ED ed){
		super();
		
		BoxRoom.compteurBoxRoomId += 1;
		this.setId(BoxRoom.compteurBoxRoomId);
		
		this.setEd(ed);
		this.setName("BoxRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
		
		this.setState("free");
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
	
	
}
