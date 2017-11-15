package HR;

import Events.*;

public class Patient extends Human{

	private static int compteurPatientId;
	private String healthInsurance;
	private String severityLevel;
	private TimeStamp arrivalTime;
	private TimeStamp departureTime;
	private String location;
	private String history;
	private float charges;
	private Physician physician;
	
	public Patient(String edName, String name, String surname, String state, String healthInsurance, String severityLevel, TimeStamp arrivalTime){
		super();
		
		Patient.compteurPatientId += 1;
		this.setId(Patient.compteurPatientId);
		
		this.setEdName(edName);
		this.setName(name);
		this.setSurname(surname);
		this.setState(state);
		
		this.healthInsurance = healthInsurance;
		this.severityLevel = severityLevel;
		this.arrivalTime = arrivalTime;
		this.location = "";
		this.history = "";
		this.charges = 0;
	}
	public Patient(String edName, String severityLevel, TimeStamp arrivalTime){
		super();
		
		Patient.compteurPatientId += 1;
		this.setId(Patient.compteurPatientId);
		
		this.setEdName(edName);
		this.setName("Patient" + Integer.toString(this.getId()));
		this.setSurname("Patient" + Integer.toString(this.getId()));
		this.setState("Arriving");
		this.healthInsurance = "NO_INSURANCE";
		this.severityLevel = severityLevel;
		this.arrivalTime = arrivalTime;
		this.location = "";
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

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
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
