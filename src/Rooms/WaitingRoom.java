package Rooms;

import Emergency.ED;
import HR.Patient;

public class WaitingRoom extends Room{

	private static int compteurWaitingRoomId;
	Patient[] occupants;
	
	public WaitingRoom(ED ed, String name){
		super();
		
		WaitingRoom.compteurWaitingRoomId += 1;
		this.setId(WaitingRoom.compteurWaitingRoomId);
		
		this.setEd(ed);
		this.setName(name);
		this.setCapacity(20);
	}
	public WaitingRoom(ED ed){
		super();
		
		WaitingRoom.compteurWaitingRoomId += 1;
		this.setId(WaitingRoom.compteurWaitingRoomId);
		
		this.setEd(ed);
		this.setName("WaintingRoom" + Integer.toString(this.getId()));
		this.setCapacity(20);
	}
	
	
	public static int getCompteurWaitingRoomId() {
		return compteurWaitingRoomId;
	}
	public static void setCompteurWaitingRoomId(int compteurWaitingRoomId) {
		WaitingRoom.compteurWaitingRoomId = compteurWaitingRoomId;
	}
	
	public void getOccupant(){
		
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
