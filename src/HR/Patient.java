package HR;

import Events.Time;

public class Patient extends Human{

	private static int compteurPatientId;
	private String healthInsurance;
	private String severityLevel;
	private Time arrivalTime;
	private Time departureTime;
	private String location;
	private String history;
	private float charges;
	
	public Patient(String edName, String name, String surname, String state, String healthInsurance, String severityLevel, Time arrivalTime){
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
	public Patient(String edName, String severityLevel, Time arrivalTime){
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

	public Time getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Time arrivalTime) {
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
	public void setHistory(String history) {
		this.history = history;
	}

	public float getCharges() {
		return charges;
	}
	public void setCharges(float charges) {
		this.charges = charges;
	}

	
	@Override
	public void create(){
		
	}

		
}
