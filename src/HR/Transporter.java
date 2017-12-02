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
		
		this.patientTransported = new ArrayList<Patient>();
	}
		
	@Override
	public void create() {
		
	}
	
	public void transport(Patient patient, Room targetRoom){
		//set the target room
		this.setTargetRoom(targetRoom);
		
		//memorize the patient state
		this.setLastPatientState(patient.getState());
		
		
		this.setState("transportation");
		
		//set the state of the patient
		patient.setState("transportation");
		
		
		//add the patient to patient transported
		this.patientTransported.add(patient);
		
		//set the start and the end of the transportation
		startTime = new TimeStamp() ;
		endTime = new TimeStamp(duration);
		
		
	}
	
	public void backToPhysician(Patient patient, WaitingRoom targetroom){
		this.setState("transportation");
		
		//set the target room
		this.setTargetRoom(targetroom);
		
		
		this.setLastPatientState(patient.getState());
		
		
		patient.setState("transportation");
		
		
		//add the patient to patient transported
		this.patientTransported.add(patient);
		
		//set the start and the end of the transportation
		startTime = new TimeStamp() ;
		endTime = new TimeStamp(duration);
	}
	
	public void dropPatient(Patient patient){
		//ajout à la room 
		this.getTargetRoom().addOccupant(patient);
		
		//set patient state
		if (this.getLastPatientState().equalsIgnoreCase("waitingForMRI")){
			patient.setState("waitingForMRIT");		
		}
		
		else if (this.getLastPatientState().equalsIgnoreCase("waitingForBloodTest")){
			patient.setState("waitingForBloodTestT");
		}
		
		else if (this.getLastPatientState().equalsIgnoreCase("bloodTested") || this.getLastPatientState().equalsIgnoreCase("mriTested") || this.getLastPatientState().equalsIgnoreCase("radioTested")){
			patient.setState("WaitingForConsultation");
		}
		
		else {
			patient.setState("waitingForRadioT");
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
	
	@Override
	public void setState(String state) {
		ArrayList<ArrayList<Transporter>> dbTransporter = this.ed.getDbTransporter();
		
		
		for (int i = 0; i<dbTransporter.size(); i++){
			dbTransporter.get(i).remove(this);
		}
		
		
		//add the transporter to the state transporter db
		if (state.equals("idle")){
			this.ed.getDbTransporter().get(0).add(this);
			this.state = state;
		}
		else if (state.equals("transportation")){
			this.ed.getDbTransporter().get(1).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("ofDuty")){
			this.ed.getDbTransporter().get(2).add(this);
			this.state = state;
		}
		else{
			System.out.println("cet état n'existe pas");
		}
	}

	
}
