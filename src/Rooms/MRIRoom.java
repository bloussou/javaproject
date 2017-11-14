package Rooms;

import Factory.Room;
import HR.Patient;

public class MRIRoom extends Room {

	private static int compteurMRIRoomId;
	Patient patient;
	
	public MRIRoom(String edName, String name, int capacity){
		super();
		
		MRIRoom.compteurMRIRoomId += 1;
		this.setId(MRIRoom.compteurMRIRoomId);
		
		this.setEdName(edName);
		this.setName(name);
		this.setCapacity(1);
		this.setDist("Unif");
		
	}
	public MRIRoom(String edName, int capacity){
		super();
		
		MRIRoom.compteurMRIRoomId += 1;
		this.setId(MRIRoom.compteurMRIRoomId);
		
		this.setEdName(edName);
		this.setName("MRIRoom" + Integer.toString(this.getId()));
		this.setCapacity(capacity);
	}
	
	
	public static int getCompteurMRIRoomId() {
		return compteurMRIRoomId;
	}
	public static void setCompteurMRIRoomId(int compteurMRIRoomId) {
		MRIRoom.compteurMRIRoomId = compteurMRIRoomId;
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

