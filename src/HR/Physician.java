package HR;
import java.util.*;


public class Physician extends Human implements Observer{
	
	private static int compteurPhysicianId;
	private ArrayList<Patient> patientOverseeing;	
	private ArrayList<Patient> patientAlreadyTreated;
	
	
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
	
	
	public void handleNewPatient(Patient patient){

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
