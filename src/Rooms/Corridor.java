package Rooms;

import java.util.ArrayList;
import Emergency.ED;
import HR.Patient;

public class Corridor extends Room{

	/**
	 * a static int to give a unique id to each ShockRoom
	 */
	private static int compteurCorridorId;
	
	/**
	 * an array which represents the list of the patients waiting in the corridor (no place in WaitingRooms)
	 */
	private ArrayList<Patient> occupants;
	
	
	/**
	 * Create a Corridor with those parameters
	 * @param ed
	 * @param name
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see Corridor#setEd(ED)
	 * @see Corridor#setName(String)
	 */
	public Corridor(ED ed, String name){
		super();
		
		Corridor.compteurCorridorId += 1;
		this.setId(Corridor.compteurCorridorId);
		
		this.setEd(ed);
		this.setName(name);
		this.setCapacity(1000000);
	}
	
	/**
	 * Create a Corridor called 'Corridor N' with those parameters
	 * @param ed
	 * @see Room#getId()
	 * @see Room#getEd()
	 * @see Corridor#setEd(ED)
	 * @see Corridor#setName(String)
	 */
	public Corridor(ED ed){
		super();
		
		Corridor.compteurCorridorId += 1;
		this.setId(Corridor.compteurCorridorId);
		
		this.setEd(ed);
		this.setName("Corridor" + Integer.toString(this.getId()));
		this.setCapacity(100000);
	}

	
	
	public static int getCompteurCorridorId() {
		return compteurCorridorId;
	}
	public static void setCompteurCorridorId(int compteurCorridorId) {
		Corridor.compteurCorridorId = compteurCorridorId;
	}
	public ArrayList<Patient> getOccupants(){
		return occupants;
	}
	@Override
	public void addOccupant(Patient patient) {
		this.occupants.add(patient);
		patient.setLocation(this);
		
	}
	@Override
	public void removeOccupant(Patient patient) {
		this.occupants.remove(patient);
		
	}

	@Override
	public void construct() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updatePatientCharge(Patient patient) {
		if (occupants.contains(patient)){
		patient.setCharges(patient.getCharges()+this.getCost());
		}
	}

}
