package HR;
import java.util.*;

import Emergency.ED;
import Events.TimeStamp;
import Rooms.Room;
import Rooms.WaitingRoom;


public class Transporter extends Human{
	
	/**
	 * Start time of the transportation
	 */
	private TimeStamp startTime;
	/**
	 * end time of the transportation
	 */
	private TimeStamp endTime;
	/**
	 * duration of the transportation = 4min
	 */
	final int duration = 4;
	/**
	 * the target room where the patient will be dropped
	 */
	private Room targetRoom;
	/**
	 * the last patient state to remember his last state
	 */
	private String lastPatientState ;
	/**
	 * an int to give a unique id to each transporter
	 */
	private static int compteurTransporterId;
	/**
	 * The list of all the patient the transporter has transported
	 */
	private ArrayList<Patient> patientTransported;

	
	
	/**
	 * The constructor of the patient
	 * @param ed
	 * @param name
	 * @param surname
	 * @param state
	 */
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
	
	/**
	 * The constructor of the patient. 
	 * <li>set the state of the transporter to idle</li>
	 * <li>set the name and surname to Transporter+id</li>
	 * @param ed
	 */
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
	
	
	
	/**
	 * A method to transport a patient, it :
	 * <li>set the {@link Transporter#targetRoom}</li>
	 * <li>set the {@link Transporter#lastPatientState}</li>
	 * <li>set the patient state to transportation</li>
	 * <li>set the transporter state to transportation</li>
	 * <li>add the patient to the list {@link Transporter#patientTransported}</li>
	 * <li>set the {@link Transporter#startTime}</li>
	 * <li>set the {@link Transporter#endTime}</li>
	 * @param patient
	 * @param targetRoom
	 * @see Patient#setState(String)
	 * @see Transporter#setState(String)
	 */
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
		this.startTime = new TimeStamp() ;
		this.endTime = new TimeStamp(this.getStartTime().getTimeStamp() + this.duration);
		
		patient.setHistory("("+patient.getState() +", "+ this.getStartTime().toString() + "), ");
	}
	
	
	/**
	 * drop the patient in the {@link Transporter#targetRoom} and set the patient to the good state :
	 * <li>from waitingForMRI to transportation to waitingForMRIT </li>
	 * <li>from waitingForBloodTest to transportation to waitingForBloodTestT </li>
	 * <li>from waitingForRadio to transportation to waitingForRadioT </li>
	 * <li>from bloodTested/mriTested/radioTested to transportation to WaitingForConsultation </li>
	 * <li>set the state of the transporter to idle</li>
	 * @param patient
	 * @see Transporter#setState(String)
	 * @see Patient#setState(String)
	 */
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
		
		else if (this.getLastPatientState().equalsIgnoreCase("waitingForRadio")){
			patient.setState("waitingForRadioT");
		}
		
		else {
			patient.setState("waitingForConsultation");
		}
		
		//set the state of the transporter
		this.setState("idle");
		
		
		patient.setHistory("("+patient.getState() +", "+ this.getEndTime().toString() + "), ");
	}
	

	
	
//	/**
//	 * The method to transport a patient after an exam back to his physician, then the physician emit a verdict
//	 * <li>set the {@link Transporter#targetRoom}</li>
//	 * <li>set the {@link Transporter#lastPatientState}</li>
//	 * <li>set the patient state to transportation</li>
//	 * <li>set the transporter state to transportation</li>
//	 * <li>add the patient to the list {@link Transporter#patientTransported}</li>
//	 * <li>set the {@link Transporter#startTime}</li>
//	 * <li>set the {@link Transporter#endTime}</li>
//	 * @param patient
//	 * @param targetroom
//	 */
//	public void backToPhysician(Patient patient, Room targetRoom){
//		this.setState("transportation");
//		
//		//set the target room
//		this.setTargetRoom(targetRoom);
//		
//		
//		this.setLastPatientState(patient.getState());
//		
//		
//		patient.setState("transportation");
//		
//		
//		//add the patient to patient transported
//		this.patientTransported.add(patient);
//		
//		//set the start and the end of the transportation
//		startTime = new TimeStamp() ;
//		endTime = new TimeStamp(this.getStartTime().getTimeStamp() + this.duration);
//	}

	
	/**
	 * 
	 * @return {@link Transporter#lastPatientState}
	 */
	public String getLastPatientState() {
		return lastPatientState;
	}
	/**
	 * Set the {@link Transporter#lastPatientState}
	 * @param lastPatientState
	 */
	public void setLastPatientState(String lastPatientState) {
		this.lastPatientState = lastPatientState;
	}
	/**
	 * 
	 * @return {@link Transporter#targetRoom}
	 */
	public Room getTargetRoom() {
		return targetRoom;
	}
	/**
	 * Set the {@link Transporter#targetRoom}
	 * @param targetRoom
	 */
	public void setTargetRoom(Room targetRoom) {
		this.targetRoom = targetRoom;
	}
	/**
	 * 
	 * @return {@link Transporter#startTime}
	 */
	public TimeStamp getStartTime() {
		return startTime;
	}
	/**
	 * 
	 * @return {@link Transporter#endTime}
	 */
	public TimeStamp getEndTime() {
		return endTime;
	}
	/**
	 * 
	 * @return {@link Transporter#patientTransported}
	 */
	public ArrayList<Patient> getPatientTransported() {
		return patientTransported;
	}
	
	
	/**
	 * Sets the state of the Transporter and moves him/her to the good list of {@link ED#getDbTransporter()}, it :
	 * <li>removes it from its last db state</li>
	 * <li>put it in the good db</li>
	 * 
	 * @param String state
	 * @see ED#getDbTransporter()
	 */
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
