package HR;

import java.util.*;

import Emergency.ED;
import Events.TimeStamp;
import Rooms.Room;

public class Nurse extends Human{
	
	private static int compteurNurseId;
	private ArrayList<Patient> patientRegistered;
	private ArrayList<Patient> patientTransported;
	private TimeStamp startTime;
	private TimeStamp endTime;
	final int duration = 2;
	private Room targetRoom;
	

	public Nurse(ED ed, String name, String surname, String state){
		super();
		
		Nurse.compteurNurseId += 1;
		this.setId(Nurse.compteurNurseId);
		
		this.setEd(ed);
		this.setName(name);
		this.setSurname(surname);
		this.setState(state);
		
		this.patientRegistered = new ArrayList<Patient>();
		this.patientTransported = new ArrayList<Patient>();
	}	
	public Nurse(ED ed){
		super();

		Nurse.compteurNurseId += 1;
		this.setId(Nurse.compteurNurseId);

		this.setEd(ed);
		this.setName("Nurse" + Integer.toString(this.getId()));
		this.setSurname("Nurse" + Integer.toString(this.getId()));
		this.setState("Free");
		
		this.patientRegistered = new ArrayList<Patient>();
		this.patientTransported = new ArrayList<Patient>();
	}
	
	
	@Override
	public void create(){
		System.out.println("Création d'une infirmière :\n" + this.toString());
	}
	
	public void register(Patient patient){
		patient.setState("Registered");
	}
	
	public void transport(Patient patient, Room targetRoom){
		
	}

	public void dropPatient(Patient patient){
		
	}
	
	
	
	public static int getCompteurNurseId() {
		return compteurNurseId;
	}
	public static void setCompteurNurseId(int compteurNurseId) {
		Nurse.compteurNurseId = compteurNurseId;
	}
	public ArrayList<Patient> getPatientRegistered() {
		return patientRegistered;
	}
	public void setPatientRegistered(ArrayList<Patient> patientRegistered) {
		this.patientRegistered = patientRegistered;
	}
	public ArrayList<Patient> getPatientTransported() {
		return patientTransported;
	}
	public void setPatientTransported(ArrayList<Patient> patientTransported) {
		this.patientTransported = patientTransported;
	}
	public TimeStamp getStartTime() {
		return startTime;
	}
	public void setStartTime(TimeStamp startTime) {
		this.startTime = startTime;
	}
	public TimeStamp getEndTime() {
		return endTime;
	}
	public void setEndTime(TimeStamp endTime) {
		this.endTime = endTime;
	}
	public Room getTargetRoom() {
		return targetRoom;
	}
	public void setTargetRoom(Room targetRoom) {
		this.targetRoom = targetRoom;
	}

	
}
