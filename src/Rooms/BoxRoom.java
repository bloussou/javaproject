package Rooms;

import Factory.Room;
import HR.Patient;
import HR.Physician;

public class BoxRoom extends Room{
	
	private static int compteurBoxRoomId;
	private Patient patient;
	private Physician physician;
	

	public BoxRoom(String edName, String name){
		super();
		this.setCapacity(1);  // the default value of capacity is one
		BoxRoom.compteurBoxRoomId += 1;
		this.setId(BoxRoom.compteurBoxRoomId);
		
		this.setEdName(edName);
		this.setName(name);
		this.setCapacity(1);
		
	}
	
	public BoxRoom(String edName){
		super();
		
		BoxRoom.compteurBoxRoomId += 1;
		this.setId(BoxRoom.compteurBoxRoomId);
		
		this.setEdName(edName);
		this.setName("BoxRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
	}
	
	
	
	
	
	public static int getCompteurBoxRoomId() {
		return compteurBoxRoomId;
	}
	
	public static void setCompteurBoxRoomId(int compteurBoxRoomId) {
		BoxRoom.compteurBoxRoomId = compteurBoxRoomId;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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
		
	}
	@Override
	public void removeOccupant(Patient patient){
		
	}
	@Override
	public void construct(){
		
	}
	@Override
	public void updatePatientCharge(){
		
	}
	
	
}
