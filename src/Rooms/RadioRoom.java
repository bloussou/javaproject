package Rooms;

import Emergency.ED;
import HR.Patient;

public class RadioRoom extends Room{

	private static int compteurRadioRoomId;
	private Patient patient;
	
	public RadioRoom(ED ed, String name){
		super();
		
		RadioRoom.compteurRadioRoomId += 1;
		this.setId(RadioRoom.compteurRadioRoomId);
		
		this.setEd(ed);
		this.setName(name);
		this.setCapacity(1);
		this.setDist("Unif");
	}
	public RadioRoom(ED ed){
		super();
		
		RadioRoom.compteurRadioRoomId += 1;
		this.setId(RadioRoom.compteurRadioRoomId);
		
		this.setEd(ed);
		this.setName("RadioRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
		this.setDist("Unif");
	}
	

	public static int getCompteurRadioRoomId() {
		return compteurRadioRoomId;
	}
	public static void setCompteurRadioRoomId(int compteurRadioRoomId) {
		RadioRoom.compteurRadioRoomId = compteurRadioRoomId;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
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
