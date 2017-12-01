package HR;

import java.util.ArrayList;

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
		this.setState("Arrived");
		
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
	
	
	@Override
	public void setState(String state){
		
		ArrayList<ArrayList<Patient>> dbPatient = this.ed.getDbPatient();
		
		
		for (int i = 0; i<dbPatient.size(); i++){
			dbPatient.get(i).remove(this);
		}
		
		//add the patient to the state patient db
		if (state.equalsIgnoreCase("arrived")){
			this.ed.getDbPatient().get(0).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("registered")){
			this.ed.getDbPatient().get(1).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("transporting")){
			this.ed.getDbPatient().get(2).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("waitingForConsultation")){
			this.ed.getDbPatient().get(3).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("inConsultation")){
			this.ed.getDbPatient().get(4).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("waitingForMRI")){
			this.ed.getDbPatient().get(5).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("waitingForBloodTest")){
			this.ed.getDbPatient().get(6).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("waitingForRadio")){
			this.ed.getDbPatient().get(7).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("transportation")){
			this.ed.getDbPatient().get(8).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("waitingForMRIT")){
			this.ed.getDbPatient().get(9).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("waitingForBloodTestT")){
			this.ed.getDbPatient().get(10).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("waitingForRadioT")){
			this.ed.getDbPatient().get(11).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("bloodTested")){
			this.ed.getDbPatient().get(12).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("mriTested")){
			this.ed.getDbPatient().get(13).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("radioTested")){
			this.ed.getDbPatient().get(14).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("released")){
			this.ed.getDbPatient().get(15).add(this);
			this.state = state;
		}
		
		else{
			System.out.println("cet état n'existe pas");
		}		
	}

		
}
