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
		//set the state of the physician
		this.setState("visiting");
		
		//add the patient to the list patientOverseeing
		this.patientOverseeing.add(patient);
		
		//set the state of the patient
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
		TimeStamp departureTime = new TimeStamp();
		patient.setState("released");
		patient.setHistory("(released, "+ departureTime.toString() + "), ");
		patient.setDepartureTime(departureTime);
		
		//remove the patient of the list patient
		this.patientOverseeing.remove(patient);
		
		
		//add the patient to the array patientTreated
		this.patientAlreadyTreated.add(patient);
		
	}
	
	
	public void prescribe(Patient patient){
		
		//building the prescription
		String prescription = "" ;
		double num = new Uniform().randSample(0, 100);
		TimeStamp time = new TimeStamp();
		
		if (num<=35){
			prescription = "released";
			this.emitVerdict(patient);
			
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
