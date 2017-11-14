package Rooms;

import Factory.Room;
import HR.*;

public class ShockRoom extends Room{

	private static int compteurShockRoomId;
	private Patient patient;
	private Physician physician;
	
	public ShockRoom(String edName, String name){
		super();
		
		ShockRoom.compteurShockRoomId += 1;
		this.setId(ShockRoom.compteurShockRoomId);
		
		this.setEdName(edName);
		this.setName(name);
		this.setCapacity(1);
	}
	public ShockRoom(String edName){
		super();
		
		ShockRoom.compteurShockRoomId += 1;
		this.setId(ShockRoom.compteurShockRoomId);
		
		this.setEdName(edName);
		this.setName("ShockRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeOccupant(Patient patient) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void construct() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updatePatientCharge() {
		// TODO Auto-generated method stub
		
	}

	
}
