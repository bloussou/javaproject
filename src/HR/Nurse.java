package HR;

import java.util.*;

import Emergency.ED;
import Events.TimeStamp;
import Rooms.Room;
import Rooms.WaitingRoom;

public class Nurse extends Human{
	/**
	 * a int to give a unique id to each Nurse
	 */
	private static int compteurNurseId;
	/**
	 * An array which contains the patient registered by this Nurse
	 */
	private ArrayList<Patient> patientRegistered;
	/**
	 * An array which contains the patient transported by this Nurse
	 */
	private ArrayList<Patient> patientTransported;
	/**
	 * the start time of the event the nurse is doing
	 */
	private TimeStamp startTime;
	/**
	 * the end time of the event the nurse is doing
	 */
	private TimeStamp endTime;
	/**
	 * the duration of a transportation
	 * 2 min
	 */
	final int duration = 2;
	/**
	 * The target room of the Nurse to drop the patient
	 */
	private Room targetRoom;
	
	/**
	 * Create a Nurse with this parameters
	 * @param ed
	 * @param name
	 * @param surname
	 * @param state
	 * @see Human#getId()
	 * @see Human#setId(int)
	 * @see Nurse#patientRegistered
	 * @see Nurse#patientTransported
	 */
	public Nurse(ED ed, String name, String surname, String state){
		super();
		
		Nurse.compteurNurseId += 1;
		this.setId(Nurse.compteurNurseId);
		
		this.setEd(ed);
		this.setName(name);
		this.setSurname(surname);
		this.setState(state);
		
		this.patientRegistered = new ArrayList<Patient>();
		this.patientTransported = new ArrayList<Patient>();
	}	
	
	
	/**
	 * Create a Nurse with only a parameters
	 * @param ed
	 * @see Human#getId()
	 * @see Human#setId(int)
	 * @see Nurse#patientRegistered
	 * @see Nurse#patientTransported
	 */
	public Nurse(ED ed){
		super();

		Nurse.compteurNurseId += 1;
		this.setId(Nurse.compteurNurseId);

		this.setEd(ed);
		this.setName("Nurse" + Integer.toString(this.getId()));
		this.setSurname("Nurse" + Integer.toString(this.getId()));
		this.setState("idle");
		
		
		
		this.patientRegistered = new ArrayList<Patient>();
		this.patientTransported = new ArrayList<Patient>();
	}
	
	
	
	/**
	 * Display the message :
	 * "Création d'une infirmière :\n" + this.toString()
	 * @see Human#toString()
	 */
	@Override
	public void create(){
		System.out.println("Création d'une infirmière :\n" + this.toString());
	}
	
	
	/**
	 * A method to register a patient it :
	 * <li>adds the patient to {@link ED#getEdRegister()}</li>
	 * <li>set the state of the patient an dmove it to the good list of {@link ED#getDbPatient()}</li>
	 * @param patient
	 * @see ED#addPatientToEdRegister(Patient)
	 * @see Patient#setState(String)
	 */
	public void register(Patient patient){
		
		patient.getEd().addPatientToEdRegister(patient);

		patient.setState("Registered");
		this.addPatientRegistered(patient);
		
		//set the history of the patient
		this.startTime = new TimeStamp();
		patient.setHistory("(registered, "+ this.getStartTime().toString() + "), ");

		
	}
	
	
	
	/**
	 * A method to transport the patient to the good target room (here a waiting room) :
	 * it :
	 * <p>
	 * <li>set the target room</li>
	 * <li>set the state of the nurse to transporting</li>
	 * <li>set the state of the patient to transporting</li>
	 * <li>add the patient to {@link Nurse#patientTransported}</li>
	 * <li>set the start time and the end time</li>
	 * </p>
	 * @param patient
	 * @param targetRoom
	 * @see Nurse#setState(String)
	 * @see Patient#setState(String)
	 * @see Nurse#patientTransported
	 * @see TimeStamp
	 * @see Nurse#startTime
	 * @see Nurse#endTime
	 * @see Nurse#duration
	 */
	public void transport(Patient patient, Room targetRoom){
		//set the target room
		this.setTargetRoom(targetRoom);
		
		//add the patient to patienttransported
		this.getPatientTransported().add(patient);
		
		//set the state of the nurse
		this.setState("transporting");
		
		
		//set the state of the patient
		patient.setState("transporting");
		
		//set the history of the patient
		this.startTime = new TimeStamp();
		this.setEndTime(new TimeStamp(2));
		patient.setHistory("(transporting, "+ this.getStartTime().toString() + "), ");
		
	}
	/**
	 * Drop the patient in the targeted waiting room,
	 * it :
	 * <li>set the patient state to waitingForConsultation</li>
	 * <li>add the patient to the target room</li>
	 * <li>the the nurse state to idle</li>
	 * @param patient
	 * @see Patient#setState(String)
	 * @see Nurse#setState
	 * @see Nurse#targetRoom
	 */
	public void dropPatient(Patient patient){
		patient.setState("waitingForConsultation");
			
		//ajout à la room 
		this.getTargetRoom().addOccupant(patient);
	
		//set the state of the nurse
		this.setState("idle");	
		
		//set the history of the patient
		this.startTime = new TimeStamp();
		patient.setHistory("(dropedInWaitingRoom, "+ this.getStartTime().toString() + "), ");
	}
	
	
	/**
	 * 
	 * @return {@link Nurse#compteurNurseId}
	 */
	public static int getCompteurNurseId() {
		return compteurNurseId;
	}
	/**
	 * sets the {@link Nurse#compteurNurseId}
	 * @param compteurNurseId
	 */
	public static void setCompteurNurseId(int compteurNurseId) {
		Nurse.compteurNurseId = compteurNurseId;
	}
	/**
	 * 
	 * @return {@link Nurse#patientRegistered}
	 */
	public ArrayList<Patient> getPatientRegistered() {
		return patientRegistered;
	}
	/**
	 * add a patient to the list {@link Nurse#patientRegistered}
	 * @param patientRegistered
	 */
	public void addPatientRegistered(Patient patientRegistered) {
		this.patientRegistered.add(patientRegistered);
	}
	/**
	 * 
	 * @return {@link Nurse#patientTransported}
	 */
	public ArrayList<Patient> getPatientTransported() {
		return patientTransported;
	}
	/**
	 * 
	 * @return {@link Nurse#startTime}
	 */
	public TimeStamp getStartTime() {
		return startTime;
	}
	/**
	 * Sets the {@link Nurse#startTime}
	 * @param startTime
	 */
	public void setStartTime(TimeStamp startTime) {
		this.startTime = startTime;
	}
	/**
	 * 
	 * @return {@link Nurse#endTime}
	 */
	public TimeStamp getEndTime() {
		return endTime;
	}
	/**
	 * Sets the {@link Nurse#endTime}
	 * @param endTime
	 */
	public void setEndTime(TimeStamp endTime) {
		this.endTime = endTime;
	}
	/**
	 * 
	 * @return {@link Nurse#targetRoom}
	 */
	public Room getTargetRoom() {
		return targetRoom;
	}
	/**
	 * Sets the {@link Nurse#targetRoom}
	 * @param targetRoom
	 */
	public void setTargetRoom(Room targetRoom) {
		this.targetRoom = targetRoom;
	}
	
	/**
	 * Sets the state of the nurse and moves him/her to the good list of {@link ED#getDbNurse()}, it :
	 * <li>removes it from its last db state</li>
	 * <li>put it in the good db</li>
	 * 
	 * @param String state
	 * @see ED#getDbNurse()
	 */
	@Override
	public void setState(String state){
		
		ArrayList<ArrayList<Nurse>> dbNurse = this.ed.getDbNurse();
		for (int i = 0; i<dbNurse.size(); i++){
			dbNurse.get(i).remove(this);
		}
		//add the nurse to the state nurse db
		if (state.equals("idle")){
			this.ed.getDbNurse().get(0).add(this);
			this.state = state;
		}
		else if (state.equals("transporting")){
			this.ed.getDbNurse().get(1).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("ofDuty")){
			this.ed.getDbNurse().get(2).add(this);
			this.state = state;
		}
		else{
			System.out.println("cet état n'existe pas");
		}
	}

	
}
