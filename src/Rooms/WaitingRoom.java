package Rooms;

import java.util.ArrayList;

import Emergency.ED;
import HR.Human;
import HR.Nurse;
import HR.Patient;

public class WaitingRoom extends Room{

	/**
	 * a static int to give a unique id to each WaitingRoom
	 */
	private static int compteurWaitingRoomId;
	
	/**
	 * an array which contains the lists of patients in this WaitingRoom
	 */
	private ArrayList<Patient> occupants;
	
	
	/**
	 * Create a WaitingRoom with those parameters
	 * @param ed
	 * @param name
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see WaitingRoom#setEd(ED)
	 * @see WaitingRoom#setName(String)
	 */
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
	
	/**
	 * Create a WaitingRoom named 'WaitingRoom N' with those parameters
	 * @param ed
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see WaitingRoom#setEd(ED)
	 * @see WaitingRoom#setName(String)
	 */
	public WaitingRoom(ED ed){
		super();
		
		WaitingRoom.compteurWaitingRoomId += 1;
		this.setId(WaitingRoom.compteurWaitingRoomId);
		
		this.setEd(ed);
		this.setName("WaitingRoom" + Integer.toString(this.getId()));
		this.setCapacity(20);
		this.occupants = new ArrayList<Patient>();
		
		this.setState("available");
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
	
	/**
	 * Display the message :
	 * "Construction d'une WaitingRoom :\n" + this.toString()
	 * @see Room#toString()
	 */
	@Override
	public void construct(){
		System.out.println("Construction d'une WaitingRoom : \n" + this.toString());
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
	
	public static int getCompteurWaitingRoomId() {
		return compteurWaitingRoomId;
	}
	public static void setCompteurWaitingRoomId(int compteurWaitingRoomId) {
		WaitingRoom.compteurWaitingRoomId = compteurWaitingRoomId;
	}
	
	
	public ArrayList<Patient> getOccupants(){
		return occupants;
	}
	

	
	
}
