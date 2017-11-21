package HR;
import java.util.*;

import Emergency.ED;
import Proba.Uniform;
import Events.TimeStamp;




public class Physician extends Human implements Observer{
	
	private static int compteurPhysicianId;
	private ArrayList<Patient> patientOverseeing;	
	private ArrayList<Patient> patientAlreadyTreated;
	private TimeStamp startTime;
	private TimeStamp endTime;
	private double duration;
	
	
	public Physician(ED ed, String name, String surname, String state){
		super();
		
		Physician.compteurPhysicianId += 1;
		this.setId(Physician.compteurPhysicianId);
		
		this.setEd(ed);
		this.setName(name);
		this.setSurname(surname);
		this.setState(state);
		
		//add the physician to the state physician db
		if (this.getState().equals("idle")){
			ed.getDbPhysician().get(0).add(this);
		}
		else if (this.getState().equals("visiting")){
			ed.getDbPhysician().get(1).add(this);
		}
		else {
			ed.getDbPhysician().get(2).add(this);
		}
		
		this.patientOverseeing = new ArrayList<Patient>();
		this.patientAlreadyTreated = new ArrayList<Patient>();
	}
	public Physician(ED ed){
		super();
		
		Physician.compteurPhysicianId += 1;
		this.setId(Physician.compteurPhysicianId);
		
		this.setEd(ed);
		this.setName("Phisician" + Integer.toString(this.getId()));
		this.setSurname("Physician" + Integer.toString(this.getId()));
		this.setState("idle");
		ed.getDbPhysician().get(0).add(this);
		
		this.patientOverseeing = new ArrayList<Patient>();
		this.patientAlreadyTreated = new ArrayList<Patient>();
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
	
	
	
	public void handleNewPatient(Patient patient){
		ED edp = this.getEd();
		
		//set the state of the physician
		if(this.getState().equalsIgnoreCase("idle")){
			edp.getDbPhysician().get(0).remove(this);
		}
		else{
			edp.getDbPhysician().get(2).remove(this);
		}
		edp.getDbPhysician().get(1).add(this);
		this.setState("visiting");
		
		//add the patient to the list patientOverseeing
		this.patientOverseeing.add(patient);
		
		//set the state of the patient
		if(patient.getState().equalsIgnoreCase("waitingForConsultation")){
			edp.getDbPatient().get(3).remove(patient);
		}
		else{
			System.out.println("il y a un pb dans l'algo");
		}
		edp.getDbPatient().get(4).add(patient);
		patient.setState("inConsultation");
		
		//set the start and the end of the consultation
		startTime = new TimeStamp() ;
		this.setDuration(new Uniform().randSample(10,20));
		int duree = (int)(this.getDuration());
		endTime = new TimeStamp(duree);
		
		//set the physician of the patient
		patient.setPhysician(this);
		
		//set the history of the patient
		patient.setHistory("(visited, "+ this.startTime.toString() + "), ");
		
	}
	
	public void emitVerdict(Patient patient){
		ED edp = patient.getEd();
		TimeStamp departureTime = new TimeStamp();
		
		patient.setHistory("(released, "+ departureTime.toString() + "), ");
		patient.setDepartureTime(departureTime);
		
		//remove the patient of the list patient
		this.patientOverseeing.remove(patient);
		
		
		//add the patient to the array patientTreated
		this.patientAlreadyTreated.add(patient);
		
		//remove the patient from the db
		String patientState = patient.getState();
		if (patientState.equalsIgnoreCase("released")){
			edp.getDbPatient().get(4).remove(patient);
		}
		else if (patientState.equalsIgnoreCase("bloodTested")){
			edp.getDbPatient().get(12).remove(patient);
		}
		else if (patientState.equalsIgnoreCase("mriTested")){
			edp.getDbPatient().get(13).remove(patient);
		}
		else if (patientState.equalsIgnoreCase("radioTested")){
			edp.getDbPatient().get(14).remove(patient);
		}
		else{
			System.out.println("il y a un pb dans l'algo");
		}
		edp.getDbPatient().get(15).add(patient);
		
		
		patient.setState("released");
		
	}
	
	
	public void prescribe(Patient patient){
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
			edp.getDbPatient().get(4).remove(patient);
			edp.getDbPatient().get(7).remove(patient);
		}
		else if(num<=95){
			prescription = "waitingForBloodTest";
			//set history with the prescription
			patient.setHistory("(" + prescription +", "+ time.toString() + "), ");
			edp.getDbPatient().get(4).remove(patient);
			edp.getDbPatient().get(6).remove(patient);
		}
		else {
			prescription = "waitingForMRI";
			//set history with the prescription
			patient.setHistory("(" + prescription +", "+ time.toString() + "), ");
			edp.getDbPatient().get(4).remove(patient);
			edp.getDbPatient().get(5).remove(patient);
		}
		
		//set the state of the patient
		patient.setState(prescription);
		
		
		//set the state of the physician
		this.setState("idle");
		edp.getDbPhysician().get(1).remove(this);
		edp.getDbPhysician().get(0).add(this);
	}
	
		
	@Override
	public void displayMsgBox() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

}

