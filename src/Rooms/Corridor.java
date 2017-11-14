package Rooms;

import Factory.Room;
import HR.Patient;

public class Corridor extends Room{

	private static int compteurCorridorId;
	private Patient[] occupants;
	
	public Corridor(String edName, String name){
		super();
		
		Corridor.compteurCorridorId += 1;
		this.setId(Corridor.compteurCorridorId);
		
		this.setEdName(edName);
		this.setName(name);
		this.setCapacity(1000000);
	}
	public Corridor(String edName){
		super();
		
		Corridor.compteurCorridorId += 1;
		this.setId(Corridor.compteurCorridorId);
		
		this.setEdName(edName);
		this.setName("Corridor" + Integer.toString(this.getId()));
		this.setCapacity(100000);
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
