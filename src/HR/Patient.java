package HR;

import java.util.ArrayList;

import Emergency.ED;
import Events.*;
import Factory.FactoryCreator;
import Factory.PeopleFactory;
import Rooms.Room;

public class Patient extends Human{
	
	/**
	 * An int to give a unique id to each Patient
	 */
	private static int compteurPatientId;
	/**
	 * The health insurance of this patient
	 */
	private String healthInsurance;
	/**
	 * The severity level of the patient
	 * <li>L1</li>
	 * <li>L2</li>
	 * <li>L3</li>
	 * <li>L4</li>
	 * <li>L5</li>
	 */
	private String severityLevel;
	/**
	 * The arrival time of the patient to the ED
	 */
	private TimeStamp arrivalTime;
	/**
	 * The departure time of the patient to the ED
	 */
	private TimeStamp departureTime;
	/**
	 * The location of the patient at each time
	 */
	private Room location;
	/**
	 * The history of the patient
	 */
	private String history = "";
	/**
	 * the total charges of the patient
	 */
	private double charges;
	/**
	 * the unique physician of this patient
	 */
	private Physician physician;
	/**
	 * the time hen the patient start the consultation
	 */
	private TimeStamp firstConsultationTime;
	
	
	
	
	/**
	 * The constructor of the patient
	 * <li>set the charges to zero</li>
	 * <li>set the history to ""</li>
	 * <li>set the location to null</li>
	 * @param ed
	 * @param name
	 * @param surname
	 * @param state
	 * @param healthInsurance
	 * @param severityLevel
	 * @param arrivalTime
	 * @see Patient#healthInsurance
	 * @see Patient#severityLevel
	 * @see Patient#arrivalTime
	 * @see Patient#location
	 * @see Patient#history
	 * @see Patient#charges
	 */
	public Patient(ED ed, String name, String surname, String state, String healthInsurance, String severityLevel, TimeStamp arrivalTime){
		super();
		
		Patient.compteurPatientId += 1;
		this.setId(Patient.compteurPatientId);
		
		this.setEd(ed);
		this.setName(name);
		this.setSurname(surname);
		
		this.setHealthInsurance(healthInsurance);
		this.severityLevel = severityLevel;
		
		//set the history of the patient
		this.arrivalTime = arrivalTime;
		this.setState(state);
		this.setHistory("(arriving, "+ arrivalTime.toString() + "), ");

		this.location = null;
		this.charges = 0;
	}
	
	/**
	 * The constructor of the patient
	 * <li>set the charges to zero</li>
	 * <li>set the history to ""</li>
	 * <li>set the location to null</li>
	 * <li>set the patient state to arrived</li>
	 * <li>set the name and surname to patient+id</li>
	 * @param ed
	 * @param severityLevel
	 * @param arrivalTime
	 * @see Patient#healthInsurance
	 * @see Patient#severityLevel
	 * @see Patient#arrivalTime
	 * @see Patient#location
	 * @see Patient#history
	 * @see Patient#charges
	 */
	public Patient(ED ed, String severityLevel, TimeStamp arrivalTime){
		super();
		
		Patient.compteurPatientId += 1;
		this.setId(Patient.compteurPatientId);
		
		this.setEd(ed);
		this.setName("Patient" + Integer.toString(this.getId()));
		this.setSurname("Patient" + Integer.toString(this.getId()));
		
		this.setHealthInsurance("NOINSURANCE");
		this.severityLevel = severityLevel;
		
		//set the history of the patient
		this.arrivalTime = arrivalTime;
		this.setState("Arriving");
		this.setHistory("(arriving, "+ arrivalTime.toString() + "), ");
		
		this.location = null;
		this.charges = 0;
	}
	
	
	
	/**
	 * 
	 * @return {@link Patient#healthInsurance}
	 */
	public String getHealthInsurance() {
		return healthInsurance;
	}
	/**
	 * Set the {@link Patient#healthInsurance}
	 * @param healthInsurance
	 */
	public void setHealthInsurance(String healthInsurance) {
		if( healthInsurance.equalsIgnoreCase("NOINSURANCE")|| healthInsurance.equalsIgnoreCase("SILVER") || healthInsurance.equalsIgnoreCase("GOLD")){
			this.healthInsurance = healthInsurance;
		}
		else {
			System.out.println("This qualification of insurance is unknown. It should be among {NOINSURANCE, SILVER, GOLD}");
			this.healthInsurance = "NOINSURANCE";
		}
	}
	/**
	 * 
	 * @return {@link Patient#severityLevel}
	 */

	public String getSeverityLevel() {
		return severityLevel;
	}
	/**
	 * set the {@link Patient#severityLevel}
	 * @param severityLevel
	 */
	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}
	
	/**
	 * 
	 * @return {@link Patient#arrivalTime}
	 */
	public TimeStamp getArrivalTime() {
		return this.arrivalTime;
	}
	/**
	 * set the {@link Patient#arrivalTime}
	 * @param arrivalTime
	 */
	public void setArrivalTime(TimeStamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	/**
	 * 
	 * @return {@link Patient#departureTime}
	 */
	public TimeStamp getDepartureTime() {
		return this.departureTime;
	}
	/**
	 * set the {@link Patient#departureTime}
	 * @param departureTime
	 */
	public void setDepartureTime(TimeStamp departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * 
	 * @return {@link Patient#location}
	 */
	public Room getLocation() {
		return location;
	}
	/**
	 * Set the {@link Patient#location}
	 * @param location
	 */
	public void setLocation(Room location) {
		this.location = location;
	}
	
	/**
	 * 
	 * @return {@link Patient#history}
	 */
	public String getHistory() {
		return this.history;
	}
	/**
	 * Add the history updates to the {@link Patient#history}
	 * @param historyUpdates
	 */
	public void setHistory(String historyUpdates) {
		this.history = this.history + historyUpdates;
	}
	
	/**
	 * 
	 * @return {@link Patient#charges}
	 */
	public double getCharges() {
		return this.charges;
	}
	/**
	 * Add the new charges to {@link Patient#charges}
	 * @param charges
	 */
	public void setCharges(double newCharges) {
		this.charges += newCharges;
	}
	
	/**
	 * 
	 * @return {@link Patient#physician}
	 */
	public Physician getPhysician() {
		return this.physician;
	}
	/**
	 * Set the {@link Patient#physician}
	 * @param physician
	 */
	public void setPhysician(Physician physician) {
		this.physician = physician;
	}
	
	
	
	@Override
	public void create(){}
	
	
	
	/**
	 * Sets the state of the Patient and moves him/her to the good list of {@link ED#getDbPatient()}, it :
	 * <li>removes it from its last db state</li>
	 * <li>put it in the good db</li>
	 * 
	 * @param String state
	 * @see ED#getDbPatient()
	 */
	@Override
	public void setState(String state){
		
		ArrayList<ArrayList<Patient>> dbPatient = this.ed.getDbPatient();
		
		
		for (int i = 0; i<dbPatient.size(); i++){
			dbPatient.get(i).remove(this);
		}
		
		//add the patient to the state patient db
		if (state.equalsIgnoreCase("arriving")){
			int insertionIndex = 0;
			boolean indexFound = false;
			
			while(indexFound == false && insertionIndex < this.getEd().getDbPatient().get(0).size()){
				Patient comparedFeature = this.getEd().getDbPatient().get(0).get(insertionIndex);
				if(comparedFeature.getArrivalTime().getTimeStamp()<this.getArrivalTime().getTimeStamp()){
					insertionIndex += 1;
				}
				else {
					indexFound = true;
				}
			}
			
			this.getEd().getDbPatient().get(0).add(insertionIndex, this);			
			this.state = state;
		}
		else if (state.equalsIgnoreCase("registered")){
			int insertionIndex = 0;
			boolean indexFound = false;
			
			while(indexFound == false && insertionIndex < this.getEd().getDbPatient().get(1).size()){
				Patient comparedFeature = this.getEd().getDbPatient().get(1).get(insertionIndex);
				if((int) comparedFeature.getSeverityLevel().charAt(1) <= (int)this.getSeverityLevel().charAt(1) ){
					insertionIndex += 1;
				}
				else {
					indexFound = true;
				}
			}
			
			this.getEd().getDbPatient().get(1).add(insertionIndex, this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("transporting")){
			this.ed.getDbPatient().get(2).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("waitingForConsultation")){
			int insertionIndex = 0;
			boolean indexFound = false;
			
			while(indexFound == false && insertionIndex < this.getEd().getDbPatient().get(3).size()){
				Patient comparedFeature = this.getEd().getDbPatient().get(3).get(insertionIndex);
				if((int) comparedFeature.getSeverityLevel().charAt(1) <= (int)this.getSeverityLevel().charAt(1) ){
					insertionIndex += 1;
				}
				else {
					indexFound = true;
				}
			}
			
			this.getEd().getDbPatient().get(3).add(insertionIndex, this);
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
			this.departureTime = new TimeStamp();
		}
		else if (state.equalsIgnoreCase("testing")){
			this.ed.getDbPatient().get(16).add(this);
			this.state = state;
			this.departureTime = new TimeStamp();
		}
		
		else{
			System.out.println("cet �tat n'existe pas");
		}		
	}
	
	@Override
	public String toString(){
		return "(Patient) [id=" + this.getId() + "]"; //+ ", name=" + this.getName() + ", surname=" + this.getSurname() + ", ED=" + this.ed.getName() + ", state=" + this.getState() + ", sevLevel=" + this.getSeverityLevel();
	}

	public TimeStamp getFirstConsultationTime() {
		return firstConsultationTime;
	}

	public void setFirstConsultationTime(TimeStamp firstConsultationTime) {
		this.firstConsultationTime = firstConsultationTime;
	}
	


	
		
}
