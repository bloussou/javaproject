package Rooms;

import java.util.ArrayList;

import Emergency.ED;
import HR.*;

public class ShockRoom extends Room{

	private static int compteurShockRoomId;
	private Patient patient;
	private Physician physician;
	
	public ShockRoom(ED ed, String name){
		super();
		
		ShockRoom.compteurShockRoomId += 1;
		this.setId(ShockRoom.compteurShockRoomId);
		
		this.setEd(ed);
		this.setName(name);
		this.setCapacity(1);
		
		this.setState("free");
	}
	public ShockRoom(ED ed){
		super();
		
		ShockRoom.compteurShockRoomId += 1;
		this.setId(ShockRoom.compteurShockRoomId);
		
		this.setEd(ed);
		this.setName("ShockRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
		
		this.setState("free");
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
	
	@Override	
	public void addOccupant(Patient patient) {
		this.patient = patient;		
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
	@Override
	public void updatePatientCharge(Patient patient) {
		if (this.patient == patient){
		patient.setCharges(patient.getCharges()+this.getCost());
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
			System.out.println("cet état n'existe pas");
		}
	}
	
}
