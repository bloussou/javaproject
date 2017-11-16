package Rooms;

import Emergency.ED;
import HR.Patient;

public class BloodRoom extends Room{

	private static int compteurBloodRoomId;
	private Patient patient;
	
	public BloodRoom(ED ed, String name){
		super();
		
		BloodRoom.compteurBloodRoomId += 1;
		this.setId(BloodRoom.compteurBloodRoomId);
		
		this.setEd(ed);
		this.setName(name);
		this.setCapacity(1);
		this.setDist("Unif");
	}
	public BloodRoom(ED ed){
		super();
		
		BloodRoom.compteurBloodRoomId += 1;
		this.setId(BloodRoom.compteurBloodRoomId);
		
		this.setEd(ed);
		this.setName("BloodRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
		this.setDist("Unif");
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
	public void setPatient(Patient patient){
		
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
	
	public void bloodTesting(){
		
	}
}
