package HR;
import java.util.*;
import Proba.Uniform;
import Events.Time;




public class Physician extends Human implements Observer{
	
	private static int compteurPhysicianId;
	private ArrayList<Patient> patientOverseeing;	
	private ArrayList<Patient> patientAlreadyTreated;
	private int startTime;
	private int endTime;
	private double duration;
	
	
	public Physician(String edName, String name, String surname, String state){
		super();
		
		Physician.compteurPhysicianId += 1;
		this.setId(Physician.compteurPhysicianId);
		
		this.setEdName(edName);
		this.setName(name);
		this.setSurname(surname);
		this.setState(state);
		
		this.patientOverseeing = new ArrayList<Patient>();
		this.patientAlreadyTreated = new ArrayList<Patient>();
	}
	public Physician(String edName){
		super();
		
		Physician.compteurPhysicianId += 1;
		this.setId(Physician.compteurPhysicianId);
		
		this.setEdName(edName);
		this.setName("Phisician" + Integer.toString(this.getId()));
		this.setSurname("Physician" + Integer.toString(this.getId()));
		this.setState("Free");
		
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
		Time t = Time.getInstanceTime();
		startTime = t.getTime();
		this.setDuration(new Uniform().randSample(10,20));
		int duree = (int)(this.getDuration());
	}
	
	public void emitVerdict(){
		
	}
	
	public void prescribe(){
		
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
