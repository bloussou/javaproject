package Rooms;

import java.util.ArrayList;

import Emergency.ED;
import HR.Patient;

public class WaitingRoom extends Room{

	private static int compteurWaitingRoomId;
	private ArrayList<Patient> occupants;
	
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
	
	
	public ArrayList<Patient> getOccupants(){
		return occupants;
	}
	@Override
	public void addOccupant(Patient patient){
		occupants.add(patient);
		if (occupants.size() == this.getCapacity()){
			this.getEd().getDbWaitingRoom().get(0).remove(this);
			this.getEd().getDbWaitingRoom().get(1).add(this);
			this.setState("full");
		}

	}
	@Override
	public void removeOccupant(Patient patient){
		occupants.remove(patient);
		this.getEd().getDbWaitingRoom().get(1).remove(this);
		this.getEd().getDbWaitingRoom().get(0).add(this);
		this.setState("available");
	}
	@Override
	public void construct(){
		
	}
	@Override
	public void updatePatientCharge(Patient patient){
		if (occupants.contains(patient)){
		patient.setCharges(patient.getCharges()+this.getCost());
		}
	}
	
	
}
