package HR;

import Emergency.ED;
import Events.*;
import Rooms.Room;

public class Patient extends Human{

	private static int compteurPatientId;
	private String healthInsurance;
	private String severityLevel;
	private TimeStamp arrivalTime;
	private TimeStamp departureTime;
	private Room location;
	private String history;
	private float charges;
	private Physician physician;
	
	public Patient(ED ed, String name, String surname, String state, String healthInsurance, String severityLevel, TimeStamp arrivalTime){
		super();
		
		Patient.compteurPatientId += 1;
		this.setId(Patient.compteurPatientId);
		
		this.setEd(ed);
		this.setName(name);
		this.setSurname(surname);
		this.setState(state);
		
		this.healthInsurance = healthInsurance;
		this.severityLevel = severityLevel;
		this.arrivalTime = arrivalTime;
		this.location = null;
		this.history = "";
		this.charges = 0;
	}
	public Patient(ED ed, String severityLevel, TimeStamp arrivalTime){
		super();
		
		Patient.compteurPatientId += 1;
		this.setId(Patient.compteurPatientId);
		
		this.setEd(ed);
		this.setName("Patient" + Integer.toString(this.getId()));
		this.setSurname("Patient" + Integer.toString(this.getId()));
		this.setState("Arriving");
		this.healthInsurance = "NO_INSURANCE";
		this.severityLevel = severityLevel;
		this.arrivalTime = arrivalTime;
		this.location = null;
		this.history = "";
		this.charges = 0;
	}
	
	
	public String getHealthInsurance() {
		return healthInsurance;
	}
	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	public String getSeverityLevel() {
		return severityLevel;
	}
	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}

	public TimeStamp getArrivalTime() {
		return this.arrivalTime;
	}
	public void setArrivalTime(TimeStamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public TimeStamp getDepartureTime() {
		return this.departureTime;
	}
	public void setDepartureTime(TimeStamp departureTime) {
		this.departureTime = departureTime;
	}

	
	
	public Room getLocation() {
		return location;
	}
	public void setLocation(Room location) {
		this.location = location;
	}

	public String getHistory() {
		return history;
	}
	public void setHistory(String historyUpdates) {
		this.history = this.history + historyUpdates;
	}

	public float getCharges() {
		return charges;
	}
	public void setCharges(float charges) {
		this.charges = charges;
	}
	public Physician getPhysician() {
		return physician;
	}
	public void setPhysician(Physician physician) {
		this.physician = physician;
	}
	
	
	@Override
	public void create(){
		
	}

		
}
