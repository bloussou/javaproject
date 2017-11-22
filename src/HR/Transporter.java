package HR;
import java.util.*;

import Emergency.ED;
import Events.TimeStamp;
import Rooms.Room;
import Rooms.WaitingRoom;


public class Transporter extends Human{
	
	private TimeStamp startTime;
	private TimeStamp endTime;
	final int duration = 4;
	private Room targetRoom;
	private String lastPatientState ;
	
	private static int compteurTransporterId;
	private ArrayList<Patient> patientTransported;

	
	public Transporter(ED ed, String name, String surname, String state){
		super();
		
		Transporter.compteurTransporterId += 1;
		this.setId(Transporter.compteurTransporterId);
		
		this.setEd(ed);
		this.setName(name);
		this.setSurname(surname);
		this.setState(state);
		
		//add the nurse to the state nurse db
		if (this.getState().equals("idle")){
			ed.getDbTransporter().get(0).add(this);
		}
		else if (this.getState().equals("transportation")){
			ed.getDbTransporter().get(1).add(this);
		}
		else {
			ed.getDbTransporter().get(2).add(this);
		}
		
		this.patientTransported = new ArrayList<Patient>();
	}
	public Transporter(ED ed){
		super();
		
		Transporter.compteurTransporterId += 1;
		this.setId(Transporter.compteurTransporterId);
		
		this.setEd(ed);
		this.setName("Transporter" + Integer.toString(this.getId()));
		this.setSurname("Transporter" + Integer.toString(this.getId()));
		this.setState("idle");
		ed.getDbTransporter().get(0).add(this);
		
		this.patientTransported = new ArrayList<Patient>();
	}
		
	@Override
	public void create() {
		
	}
	
	public void transport(Patient patient, Room targetRoom){
		ED edp = patient.getEd();
		
		
		//set the target room
		this.setTargetRoom(targetRoom);
		
		//memorize the patient state
		this.setLastPatientState(patient.getState());
		
		//set the state of the transporter
		if (this.getState().equalsIgnoreCase("idle")){
			edp.getDbTransporter().get(0).remove(this);
		}
		else{
			edp.getDbTransporter().get(2).remove(this);
		}
		edp.getDbTransporter().get(1).add(this);
		
		this.setState("transportation");
		
		//set the state of the patient
		if (patient.getState().equalsIgnoreCase("waitingForMRI")){
			edp.getDbPatient().get(5).remove(patient);
		}
		else if (patient.getState().equalsIgnoreCase("waitingForBloodTest")){
			edp.getDbPatient().get(6).remove(patient);
		}
		else if (patient.getState().equalsIgnoreCase("waitingForRadio")){
			edp.getDbPatient().get(7).remove(patient);
		}
		
		
		edp.getDbPatient().get(8).add(patient);
		
		
		patient.setState("transportation");
		
		
		//add the patient to patient transported
		this.patientTransported.add(patient);
		
		//set the start and the end of the transportation
		startTime = new TimeStamp() ;
		endTime = new TimeStamp(duration);
		
		
	}
	
	public void backToPhysician(Patient patient, WaitingRoom targetroom){
		ED edp = patient.getEd();
		
		
		//set the target room
		this.setTargetRoom(targetRoom);
		
		
		//set the state of the transporter
		if (this.getState().equalsIgnoreCase("idle")){
			edp.getDbTransporter().get(0).remove(this);
		}
		else{
			edp.getDbTransporter().get(2).remove(this);
		}
		edp.getDbTransporter().get(1).add(this);
		
		this.setState("transportation");
		
		//set the state of the patient
		if (patient.getState().equalsIgnoreCase("bloodtested")){
			edp.getDbPatient().get(12).remove(patient);
		}
		else if (patient.getState().equalsIgnoreCase("mritested")){
			edp.getDbPatient().get(13).remove(patient);
		}
		else if (patient.getState().equalsIgnoreCase("radiotested")){
			edp.getDbPatient().get(14).remove(patient);
		}
		
		
		edp.getDbPatient().get(8).add(patient);
		
		
		
		
		
		//add the patient to patient transported
		this.patientTransported.add(patient);
		
		//set the start and the end of the transportation
		startTime = new TimeStamp() ;
		endTime = new TimeStamp(duration);
	}
	
	public void dropPatient(Patient patient){
		ED edp = patient.getEd();
		
		
		
		if (this.getLastPatientState().equals("waitingForMRI")){
			//set patient state
			patient.setState("waitingForMRIT");
			edp.getDbPatient().get(5).remove(patient);
			edp.getDbPatient().get(9).add(patient);
			
			//ajout à la room 
			this.getTargetRoom().addOccupant(patient);	
		}
		else if (this.getLastPatientState().equals("waitingForBloodTest")){
			
			//set patient state
			patient.setState("waitingForBloodTestT");
			edp.getDbPatient().get(6).remove(patient);
			edp.getDbPatient().get(10).add(patient);
			
			//ajout à la room 
			this.getTargetRoom().addOccupant(patient);	
		}
		else if (this.getLastPatientState().equals("bloodTested") || this.getLastPatientState().equals("mriTested") || this.getLastPatientState().equals("radioTested")){
			
			
			edp.getDbPatient().get(8).remove(patient);
			edp.getDbPatient().get(3).add(patient);
			
			//ajout à la room 
			this.getTargetRoom().addOccupant(patient);	
		}
		else {
			//set patient state
			patient.setState("waitingForRadioT");
			edp.getDbPatient().get(7).remove(patient);
			edp.getDbPatient().get(11).add(patient);
			
			//ajout à la room 
			this.getTargetRoom().addOccupant(patient);
		}
		//set the state of the transporter
		this.setState("idle");
	}
	

	
	
	public String getLastPatientState() {
		return lastPatientState;
	}
	public void setLastPatientState(String lastPatientState) {
		this.lastPatientState = lastPatientState;
	}
	public Room getTargetRoom() {
		return targetRoom;
	}
	public void setTargetRoom(Room targetRoom) {
		this.targetRoom = targetRoom;
	}
	public TimeStamp getStartTime() {
		return startTime;
	}
	public TimeStamp getEndTime() {
		return endTime;
	}
	public ArrayList<Patient> getPatientTransported() {
		return patientTransported;
	}

	
}
