package Rooms;

import Emergency.ED;
import HR.Patient;

public class Corridor extends Room{

	private static int compteurCorridorId;
	private Patient[] occupants;
	
	public Corridor(ED ed, String name){
		super();
		
		Corridor.compteurCorridorId += 1;
		this.setId(Corridor.compteurCorridorId);
		
		this.setEd(ed);
		this.setName(name);
		this.setCapacity(1000000);
	}
	public Corridor(ED ed){
		super();
		
		Corridor.compteurCorridorId += 1;
		this.setId(Corridor.compteurCorridorId);
		
		this.setEd(ed);
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
