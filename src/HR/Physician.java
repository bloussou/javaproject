package HR;
import java.util.*;

import Emergency.ED;
import Proba.Uniform;
import Rooms.*;
import Events.TimeStamp;
import MessageBox.Message;




public class Physician extends Human  implements Observer{
	
	private static int compteurPhysicianId;
	private ArrayList<Patient> patientOverseeing;	
	private ArrayList<Patient> patientAlreadyTreated;
	private TimeStamp startTime;
	private TimeStamp endTime;
	private double duration;
	private ArrayList<ArrayList<Message>> mailBox;
	
	
	
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
		
		
		//initialize the messageBox=[unread,already read]
		this.mailBox.add(new ArrayList<Message>());
		this.mailBox.add(new ArrayList<Message>());
	}
	
	
	
	public Physician(ED ed){
		super();
		
		Physician.compteurPhysicianId += 1;
		this.setId(Physician.compteurPhysicianId);
		
		this.setEd(ed);
		this.setName("Phisician" + Integer.toString(this.getId()));
		this.setSurname("Physician" + Integer.toString(this.getId()));
		this.setState("idle");
		
		this.patientOverseeing = new ArrayList<Patient>();
		this.patientAlreadyTreated = new ArrayList<Patient>();
		this.mailBox = new ArrayList<ArrayList<Message>>();
		
		//initialize the messageBox=[unread,already read]
		this.mailBox.add(new ArrayList<Message>());
		this.mailBox.add(new ArrayList<Message>());
		
	}
	
	
	
	
	public ArrayList<Patient> getPatientOverseeing() {
		return patientOverseeing;
	}
	public ArrayList<Patient> getPatientAlreadyTreated() {
		return patientAlreadyTreated;
	}
	public TimeStamp getStartTime() {
		return startTime;
	}
	public TimeStamp getEndTime() {
		return endTime;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public ArrayList<ArrayList<Message>> getMailBox() {
		return mailBox;
	}
	
	
	
	public void handleNewPatient(Patient patient, Room consultationRoom){
		ED edp = this.getEd();
		
		//set the state of the physician
		this.setState("visiting");
		
		//add the patient to the list patientOverseeing
		this.patientOverseeing.add(patient);
		
		//set the state of the patient
		patient.setState("inConsultation");
		
		//set the state of the room
		if(consultationRoom.getState().equalsIgnoreCase("free")){
			if(consultationRoom instanceof BoxRoom){
				edp.getDbBoxRoom().get(0).remove(consultationRoom);
				edp.getDbBoxRoom().get(1).add((BoxRoom) consultationRoom);
			}
			else if (consultationRoom instanceof ShockRoom) {
				edp.getDbShockRoom().get(0).remove(consultationRoom);
				edp.getDbShockRoom().get(1).add((ShockRoom) consultationRoom);
			}
			else {
				System.out.println("Encore un soucis dans l'algo ???");
			}
			consultationRoom.setState("occupied");
		}
		else{
			System.out.println("il y a un pb dans l'algo ici aussi ?");
		}

		
		//set the physician of the patient
		patient.setPhysician(this);
		
		//set the history of the patient
		this.startTime = new TimeStamp();
		patient.setHistory("(visited, "+ this.getStartTime().toString() + "), ");
		
	}
	
	public void emitVerdict(Patient patient){
		TimeStamp departureTime = new TimeStamp();
		patient.setState("released");
		
		patient.setHistory("(released, "+ departureTime.toString() + "), ");
		patient.setDepartureTime(departureTime);
		
		//remove the patient of the list patient
		this.patientOverseeing.remove(patient);
		
		
		//add the patient to the array patientTreated
		this.patientAlreadyTreated.add(patient);
		
		
	}
	
	
	public void prescribe(Patient patient, Room consultationRoom){
		ED edp = patient.getEd();
		
		
		//building the prescription
		String prescription = "" ;
		double num = new Uniform().randSample(0, 100);
		TimeStamp time = new TimeStamp();
		
		if (num<=35){
			this.emitVerdict(patient);
			prescription = "released";
			
		}
		else if(num <= 55){
			prescription = "waitingForRadio";
			//set history with the prescription
			patient.setHistory("(" + prescription +", "+ time.toString() + "), ");
		}
		else if(num<=95){
			prescription = "waitingForBloodTest";
			//set history with the prescription
			patient.setHistory("(" + prescription +", "+ time.toString() + "), ");
		}
		else {
			prescription = "waitingForMRI";
			//set history with the prescription
			patient.setHistory("(" + prescription +", "+ time.toString() + "), ");
		}
		
		//set the state of the patient
		patient.setState(prescription);
		
		
		//set the state of the physician
		this.setState("idle");

	
	
		
		//set the state of the room
		
		consultationRoom.setState("free");
	
	
	}
	
	
	/*
	 * writeMessage allows this physician to write a message with the good arguments
	 */
	public void writeMessage(Patient patient, Physician physician, String obj, String content){
		new Message(patient, physician, obj, content);
	}
	
	
	/*
	 * readUnreadMessageBox shows the message unread and put it in the already read file	
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
	
	/*
	 * readMessage, it allows to read only one message
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
	
	/*
	 * unReadMessage allows the physician to unread a message he has receive
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
	
	/*
	 * Remove message is a method that allows to remove the message he chooses
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
	public void displayMsgBox() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void create() {
		// TODO Auto-generated method stub
		System.out.println("Physician n°" +this.getId() +"created");
	}

}

