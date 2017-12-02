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
		this.occupants = new ArrayList<Patient>();
		
		
		
		this.setState("available");
	}
	public WaitingRoom(ED ed){
		super();
		
		WaitingRoom.compteurWaitingRoomId += 1;
		this.setId(WaitingRoom.compteurWaitingRoomId);
		
		this.setEd(ed);
		this.setName("WaintingRoom" + Integer.toString(this.getId()));
		this.setCapacity(20);
		this.occupants = new ArrayList<Patient>();
		
		this.setState("avalaible");
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
			this.setState("full");
		}
		else {
			this.setState("available");
		}

	}
	@Override
	public void removeOccupant(Patient patient){
		occupants.remove(patient);
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
	
	@Override
	public void setState(String state){
		ArrayList<ArrayList<WaitingRoom>> dbWaitingRoom = this.ed.getDbWaitingRoom();
		
		for (int i = 0; i<dbWaitingRoom.size(); i++){
			dbWaitingRoom.get(i).remove(this);
		}
		//add the shock room to the state shock room db
		if (state.equalsIgnoreCase("available")){
			this.ed.getDbWaitingRoom().get(0).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("full")){
			this.ed.getDbWaitingRoom().get(1).add(this);
			this.state = state;
		}
		else{
			System.out.println("cet état n'existe pas");
		}
	}
	
	
}
