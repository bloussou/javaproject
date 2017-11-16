package HR;
import java.util.*;

import Emergency.ED;
import Events.TimeStamp;
import Proba.Uniform;
import Rooms.Room;


public class Transporter extends Human{
	
	private TimeStamp startTime;
	private TimeStamp endTime;
	final int duration = 4;
	private Room targetRoom;
	
	private static int compteurTransporterId;
	private ArrayList<Patient> patientTransported;
	
	
	
	
	
	
	
	
	
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
		
		//set the state of the transporter
		this.setState("transportation");
		
		//set the state of the patient
		patient.setState("transportation");
		
		//add the patient to patient transported
		this.patientTransported.add(patient);
		
		//set the start and the end of the transportation
		startTime = new TimeStamp() ;
		endTime = new TimeStamp(duration);
		
		
	}
	
	public void dropPatient(Patient patient){
		if (patient.getState().equals("waitingForMRI")){
			//set patient state
			patient.setState("waitingForMRIT");
			
			//ajout à la room 
			this.getTargetRoom().addOccupant(patient);	
		}
		else if (patient.getState().equals("waitingForBloodTest")){
			
			//set patient state
			patient.setState("waitingForBloodTestT");
			
			//ajout à la room 
			this.getTargetRoom().addOccupant(patient);	
		}
		else {
			//set patient state
			patient.setState("waitingForRadioT");
			
			//ajout à la room 
			this.getTargetRoom().addOccupant(patient);
		}
		//set the state of the transporter
		this.setState("idle");
	}
	
	
}
