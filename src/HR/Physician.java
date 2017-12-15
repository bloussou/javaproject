package HR;
import java.util.*;

import Emergency.ED;
import Proba.Uniform;
import Rooms.*;
import Events.TimeStamp;
import MessageBox.Message;




public class Physician extends Human{
	
	/**
	 * An int to give a unique id to each physician
	 */
	private static int compteurPhysicianId;
	/**
	 * An array with the patient overseeing by this physician
	 */
	private ArrayList<Patient> patientOverseeing;	
	/**
	 * An array with the patient the physician had been treating
	 */
	private ArrayList<Patient> patientAlreadyTreated;
	/**
	 * the start time of the current action of this physician
	 */
	private TimeStamp startTime;
	/**
	 * the end time of the current action of this physician
	 */
	private TimeStamp endTime;
	/**
	 * the duration of the current action of this physician
	 */
	private double duration;
	/**
	 * The mailbox of this physician :
	 * <p>mailBox=[unread,already read]
	 */
	private ArrayList<ArrayList<Message>> mailBox;
	
	
	
	/**
	 * Constructor of the physician
	 * @param ed
	 * @param name
	 * @param surname
	 * @param state
	 */
	public Physician(ED ed, String name, String surname, String state){
		super();
		
		Physician.compteurPhysicianId += 1;
		this.setId(Physician.compteurPhysicianId);
		
		this.setEd(ed);
		this.setName(name);
		this.setSurname(surname);
		this.setState(state);
		
		this.patientOverseeing = new ArrayList<Patient>();
		this.patientAlreadyTreated = new ArrayList<Patient>();
		this.mailBox = new ArrayList<ArrayList<Message>>();
		
		this.mailBox.add(new ArrayList<Message>());
		this.mailBox.add(new ArrayList<Message>());
	}
	
	/**
	 * Constructor of the physician :
	 * <li>set the state of the physician to idle</li>
	 * <li>set the name and surname of the physician to Physician+id</li>
	 * @param ed
	 */
	public Physician(ED ed){
		super();
		
		Physician.compteurPhysicianId += 1;
		this.setId(Physician.compteurPhysicianId);
		
		this.setEd(ed);
		this.setName("Physician" + Integer.toString(this.getId()));
		this.setSurname("Physician" + Integer.toString(this.getId()));
		this.setState("idle");
		
		this.patientOverseeing = new ArrayList<Patient>();
		this.patientAlreadyTreated = new ArrayList<Patient>();
		this.mailBox = new ArrayList<ArrayList<Message>>();
		
		//initialize the messageBox=[unread,already read]
		this.mailBox.add(new ArrayList<Message>());
		this.mailBox.add(new ArrayList<Message>());
		
	}
	
	
	
	/**
	 * A method where the physician handle a new patient :
	 * <li>set the state of the patient to inConsultation</li>
	 * <li>set the state of the physician to visiting</li>
	 * <li>add the patient to the {@link Physician#patientOverseeing}</li>
	 * <li>set the state of the room to occupied</li>
	 * <li>set the {@link Patient#setPhysician(Physician)}</li>
	 * <li>add this event to the patient history</li>
	 * <li>set the {@link Patient#setDtdtime(TimeStamp)}</li>
	 * @param patient
	 * @param consultationRoom
	 * @see Patient#setState(String)
	 * @see Physician#setState(String)
	 * @see BoxRoom#setState(String)
	 * @see ShockRoom#setState(String)
	 */
	public void handleNewPatient(Patient patient, Room consultationRoom){
		
		//set the state of the physician
		this.setState("visiting");
		
		//set the state of the physician
		consultationRoom.addOccupant(patient);
		
		//add the patient to the list patientOverseeing
		this.patientOverseeing.add(patient);
		
		//set the state of the patient
		patient.setState("inConsultation");
		
		
		//set the history of the patient
		this.startTime = new TimeStamp();
		patient.setHistory("(visited, "+ this.getStartTime().toString() + "), ");
		
		if(patient.getPhysician()==null){
			//set the dtdt time of the patient
			patient.setFirstConsultationTime(new TimeStamp());
		}
		
		//set the physician of the patient
		patient.setPhysician(this);
		
	}
	
	/**
	 * Emit the verdict a the end of the exam : 
	 * <li>set the patient state to released</li>
	 * <li>add this event to the patient history</li>
	 * <li>set the departure time of the patient</li>
	 * <li>remove the patient from the {@link Physician#patientOverseeing}</li>
	 * <li>add the patient to the {@link Physician#patientAlreadyTreated}</li>
	 * @param patient
	 */
	public void emitVerdict(Patient patient){
		TimeStamp departureTime = new TimeStamp();
		patient.setState("released");
		
		patient.setHistory("(released, "+ departureTime.toString() + "), ");
		System.out.println("PATIENT RELEASED : " + patient.getName()+ " " + patient.getSurname() + " :" + patient.getHistory());
		patient.setDepartureTime(departureTime);
		
		//patient.setHistory("("+patient.getState() +", "+ this.getStartTime().toString() + "), ");
		
		//remove the patient of the list patient
		this.patientOverseeing.remove(patient);
		
		
		//add the patient to the array patientTreated
		this.patientAlreadyTreated.add(patient);		
	}
	
	/**
	 * Using the probability it gives a prescription add the end of the consultation to the patient . Set patient's state to the state to the prescription :
	 * <li>Released and use {@link Physician#emitVerdict(Patient)}</li>
	 * <li>waitingForRadio</li>
	 * <li>waitingForBloodTest</li>
	 * <li>waitingForMRI</li>
	 * <li>add it to the history</li>
	 * <li>set the state of the consultation room to free</li>
	 * <li>set the physician state to idle</li>
	 * @param patient
	 * @param consultationRoom
	 * @see Uniform#randSample(double, double)
	 * @see Patient#setState(String)
	 * @see Physician#setState(String)
	 * @see Room#setState(String)
	 */
	public void prescribe(Patient patient, Room consultationRoom){
		
		
		//building the prescription
		String prescription = "" ;
		double num = new Uniform().randSample(0, 100);
		TimeStamp time = new TimeStamp();
		
		if (num<=35){
			this.emitVerdict(patient);
			//prescription = "released";
			
		}
		else if(num <= 55){
			prescription = "waitingForRadio";
			//set history with the prescription
			patient.setHistory("(" + prescription +", "+ time.toString() + "), ");
			
			//set the state of the patient
			patient.setState(prescription);
		}
		else if(num<=95){
			prescription = "waitingForBloodTest";
			//set history with the prescription
			patient.setHistory("(" + prescription +", "+ time.toString() + "), ");
			
			//set the state of the patient
			patient.setState(prescription);
		}
		else {
			prescription = "waitingForMRI";
			//set history with the prescription
			patient.setHistory("(" + prescription +", "+ time.toString() + "), ");
			
			//set the state of the patient
			patient.setState(prescription);
		}
		

		
		
		//set the state of the physician
		this.setState("idle");


		//set the state of the room
		consultationRoom.removeOccupant(patient);
	}
	
	
	
	/**
	 * Write a message about a patient to a physician with an object and a content
	 * @param patient
	 * @param physician
	 * @param obj
	 * @param content
	 * @see Message#Message(Patient, Physician, String, String)
	 */
	public void writeMessage(Patient patient, Physician physician, String obj, String content){
		new Message(patient, physician, obj, content);
	}
	
	/**
	 * Read all the unread message of the physician and display "the mail box is empty !" if there is no message unread
	 * @see Message#read()
	 */
	public void readMessage() {
		ArrayList<Message> unread = this.mailBox.get(0);
		if (unread.isEmpty()) {
			System.out.println("the mail box is empty !");
		}
		else {
			for (int i = 0; i<unread.size(); i++){
				unread.get(i).read();
			}

		}
		
	}
	
	/**
	 * Read a decided message in the mailBox, display "there is no sucht message in the mailing box" if it's the case
	 * @param message
	 * @see Message#read()
	 * @see Physician#mailBox
	 */
	public void readMessage(Message message){
		ArrayList<Message> unread = this.mailBox.get(0);
		int messageIndex = unread.indexOf(message);
		if (messageIndex != -1){
			unread.get(unread.indexOf(message)).read();
		}
		else {
			System.out.println("there is no sucht message in the mailing box");
		}
	}
	
	/**
	 * Unread a certain message that the physician has already read, display "this message do not exist or isn't read" if it's the case
	 * @param message
	 * @see Message#unRead()
	 * @see Physician#mailBox
	 */
	public void unReadMessage(Message message){
		ArrayList<Message> read = this.mailBox.get(1);
		int messageIndex = read.indexOf(message);
		if (messageIndex != -1){
			read.get(messageIndex).unRead();
		}
		else {
			System.out.println("this message do not exist or isn't read");
		}
	}
	
	/**
	 * Remove a message in the mailBox, display "this message doesn't exist" if the message isn't in the mail Box
	 * @param message
	 * @see Message#removeMessage()
	 * @see Physician#mailBox
	 */
	public void removeMessage(Message message){
		ArrayList<Message> unread = this.mailBox.get(0);
		ArrayList<Message> read = this.mailBox.get(1);
		int messageIndex1 = read.indexOf(message);
		int messageIndex2 = unread.indexOf(message);
		
		if (messageIndex1 != -1){
			read.get(messageIndex1).removeMessage();
		}
		else{
			if (messageIndex2 != -1) {
				unread.get(messageIndex2).removeMessage();
			}
			else{
				System.out.println("this message doesn't exist");
			}
		}
	}
	
	
	
	/**
	 * 
	 * @return {@link Physician#patientOverseeing}
	 */
	public ArrayList<Patient> getPatientOverseeing() {
		return patientOverseeing;
	}
	/**
	 * 
	 * @return {@link Physician#patientAlreadyTreated}
	 */
	public ArrayList<Patient> getPatientAlreadyTreated() {
		return patientAlreadyTreated;
	}	
	/**
	 * 
	 * @return {@link Physician#startTime}
	 */
	public TimeStamp getStartTime() {
		return startTime;
	}
	/**
	 * 
	 * @return {@link Physician#endTime}
	 */
	public TimeStamp getEndTime() {
		return endTime;
	}
	/**
	 * 
	 * @return {@link Physician#duration}
	 */
	public double getDuration() {
		return duration;
	}
	/**
	 * Set the {@link Physician#duration} of the current event of the physician
	 * @param duration
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}
	/**
	 * 
	 * @return {@link Physician#mailBox}
	 */
	public ArrayList<ArrayList<Message>> getMailBox() {
		return mailBox;
	}
	
	
	
	/**
	 * Sets the state of the Physician and moves him/her to the good list of {@link ED#getDbNurse()}, it :
	 * <li>removes it from its last db state</li>
	 * <li>put it in the good db</li>
	 * 
	 * @param String state
	 * @see ED#getDbPhysician()
	 */
	@Override
	public void setState(String state) {
		
		ArrayList<ArrayList<Physician>> dbPhysician = this.ed.getDbPhysician();
		
		
		for (int i = 0; i<dbPhysician.size(); i++){
			dbPhysician.get(i).remove(this);
		}
		
		
		//add the nurse to the state physician db
		if (state.equals("idle")){
			this.ed.getDbPhysician().get(0).add(this);
			this.state = state;
		}
		else if (state.equals("visiting")){
			this.ed.getDbPhysician().get(1).add(this);
			this.state = state;
		}
		else if (state.equalsIgnoreCase("ofDuty")){
			this.ed.getDbPhysician().get(2).add(this);
			this.state = state;
		}
		else{
			System.out.println("cet état n'existe pas");
		}
	}
	
	@Override
	public String toString(){
		
		return "(Physician) [id=" + this.getId() + ", name=" + this.getName() + ", surname=" + this.getSurname() + ", ED=" + this.ed.getName() + ", state=" + this.getState() + ", patientsOverseen=" + this.getPatientOverseeing().size() + ", patientAlreadyTreated=" + this.getPatientAlreadyTreated().size() + "]";
	}
	
	
	
	
	/**
	 * Display the following message  :
	 * <p>"Physician n°" +this.getId() +"created"</p>
	 */
	@Override
	public void create() {
		// TODO Auto-generated method stub
		System.out.println("Physician n°" +this.getId() +"created");
	}

}

