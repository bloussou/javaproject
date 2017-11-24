package HR;

import java.util.*;

import Emergency.ED;
import Events.TimeStamp;
import Rooms.Room;
import Rooms.WaitingRoom;

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
		
		//add the nurse to the state nurse db
		if (this.getState().equals("idle")){
			ed.getDbNurse().get(0).add(this);
		}
		else if (this.getState().equals("transporting")){
			ed.getDbNurse().get(1).add(this);
		}
		else {
			ed.getDbNurse().get(2).add(this);
		}
		
		
		
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
		this.setState("Idle");
		
		//add the nurse to the idle nurse
		ed.getDbNurse().get(0).add(this);
		
		
		this.patientRegistered = new ArrayList<Patient>();
		this.patientTransported = new ArrayList<Patient>();
	}
	
	
	@Override
	public void create(){
		System.out.println("Création d'une infirmière :\n" + this.toString());
	}
	
	public void register(Patient patient){
		
		patient.getEd().addPatientToEdRegister(patient);

		patient.setState("Registered");
		this.addPatientRegistered(patient);

		//move the patient to the good db list
		ED edp = patient.getEd();
		edp.getDbPatient().get(0).remove(patient);
		edp.getDbPatient().get(1).add(patient);
		
	}
	
	public void transport(Patient patient, WaitingRoom targetRoom){
		ED edp = patient.getEd();
		String nurseState = this.getState();
		String patientState = patient.getState();
		
		
		//set the target room
		this.setTargetRoom(targetRoom);
		
		
		
		//set the state of the nurse
		this.setState("transporting");
		
		if (nurseState.equalsIgnoreCase("idle")){
			edp.getDbNurse().get(0).remove(this);
		}
		else{
			edp.getDbNurse().get(2).remove(this);
		}
		edp.getDbNurse().get(1).add(this);
		
		
		
		
		//set the state of the patient
		patient.setState("transporting");
		if (patientState.equalsIgnoreCase("registred")){
			edp.getDbPatient().get(0).remove(patient);
		}
		else{
			System.out.println("il y a un problème dans l'algo");
		}
		edp.getDbPatient().get(1).remove(patient);
		
		
		//add the patient to patient transported
		this.patientTransported.add(patient);
		
		//set the start and the end of the transportation
		startTime = new TimeStamp() ;
		endTime = new TimeStamp(duration);
	}

	public void dropPatient(Patient patient){
		ED edp = patient.getEd();
		
		
		if (patient.getState().equalsIgnoreCase("transporting")){
			edp.getDbPatient().get(2).remove(patient);
		}
		else{
			System.out.println("il y a un problème dans l'algo");
		}
		edp.getDbPatient().get(3).add(patient);
		patient.setState("waitingForConsultation");
		
			
		//ajout à la room 
		this.getTargetRoom().addOccupant(patient);
	
		//set the state of the nurse
		this.setState("idle");
		edp.getDbNurse().get(1).remove(this);
		edp.getDbNurse().get(0).add(this);
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
	public void addPatientRegistered(Patient patientRegistered) {
		this.patientRegistered.add(patientRegistered);
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
