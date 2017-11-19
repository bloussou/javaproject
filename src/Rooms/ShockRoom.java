package Rooms;

import Emergency.ED;
import HR.*;

public class ShockRoom extends Room{

	private static int compteurShockRoomId;
	private Patient patient;
	private Physician physician;
	
	public ShockRoom(ED ed, String name){
		super();
		
		ShockRoom.compteurShockRoomId += 1;
		this.setId(ShockRoom.compteurShockRoomId);
		
		this.setEd(ed);
		this.setName(name);
		this.setCapacity(1);
	}
	public ShockRoom(ED ed){
		super();
		
		ShockRoom.compteurShockRoomId += 1;
		this.setId(ShockRoom.compteurShockRoomId);
		
		this.setEd(ed);
		this.setName("ShockRoom" + Integer.toString(this.getId()));
		this.setCapacity(1);
	}
	
	
	public static int getCompteurShockRoomId() {
		return compteurShockRoomId;
	}
	public static void setCompteurShockRoomId(int compteurShockRoomId) {
		ShockRoom.compteurShockRoomId = compteurShockRoomId;
	}
	public Patient getPatient() {
		return patient;
	}
	public Physician getPhysician() {
		return physician;
	}
	public void setPhysician(Physician physician) {
		this.physician = physician;
	}
	
	@Override	
	public void addOccupant(Patient patient) {
		this.patient = patient;		
	}
	@Override
	public void removeOccupant(Patient patient) {
		this.patient = null;
			}
	@Override
	public void construct() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updatePatientCharge(Patient patient) {
		if (this.patient == patient){
		patient.setCharges(patient.getCharges()+this.getCost());
		}
	}
	
}
