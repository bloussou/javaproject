package Rooms;

import Factory.Room;
import HR.Patient;

public class MRIRoom extends Room {

	private static int compteurMRIRoomId;
	private Patient patient;
	
	public MRIRoom(String edName, String name){
		super();
		
		MRIRoom.compteurMRIRoomId += 1;
		this.setId(MRIRoom.compteurMRIRoomId);
		
		this.setEdName(edName);
		this.setName(name);
		this.setCapacity(1);
		this.setDist("Unif");
		
	}
	public MRIRoom(String edName){
		super();
		
		MRIRoom.compteurMRIRoomId += 1;
		this.setId(MRIRoom.compteurMRIRoomId);
		
		this.setEdName(edName);
		this.setName("MRIRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
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
	public void setPatient(Patient patient) {
		this.patient = patient;
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

