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
	public void transport(Patient patient, WaitingRoom targetRoom){
		//set the target room
		this.setTargetRoom(targetRoom);
		
		
		//set the state of the nurse
		this.setState("transporting");
		
		
		//set the state of the patient
		patient.setState("transporting");
		
		//add the patient to patient transported
		this.patientTransported.add(patient);
		
		//set the start and the end of the transportation
		startTime = new TimeStamp() ;
		endTime = new TimeStamp(duration);
	}
	/**
	 * Drop the patient in the targeted waiting room
	 * 
	 * @param patient
	 */
	public void dropPatient(Patient patient){
		patient.setState("waitingForConsultation");
		
			
		//ajout à la room 
		this.getTargetRoom().addOccupant(patient);
	
		//set the state of the nurse
		this.setState("idle");
		
		
	}
	
	
	
	public static int getCompteurNurseId() {
		return compteurNurseId;
	}
	public static void setCompteurNurseId(int compteurNurseId) {
		Nurse.compteurNurseId = compteurNurseId;
	}
	public ArrayList<Patient> getPatientRegistered() {
		return patientRegistered;
	}
	public void addPatientRegistered(Patient patientRegistered) {
		this.patientRegistered.add(patientRegistered);
	}
	public ArrayList<Patient> getPatientTransported() {
		return patientTransported;
	}
	public void setPatientTransported(ArrayList<Patient> patientTransported) {
		this.patientTransported = patientTransported;
	}
	public TimeStamp getStartTime() {
		return startTime;
	}
	public void setStartTime(TimeStamp startTime) {
		this.startTime = startTime;
	}
	public TimeStamp getEndTime() {
		return endTime;
	}
	public void setEndTime(TimeStamp endTime) {
		this.endTime = endTime;
	}
	public Room getTargetRoom() {
		return targetRoom;
	}
	public void setTargetRoom(Room targetRoom) {
		this.targetRoom = targetRoom;
	}
	
	
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
