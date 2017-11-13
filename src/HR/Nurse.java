package HR;
import java.util.*;
import Factory.*;
import Events.Time;

public class Nurse extends Human{
	
	private static int compteurNurseId;
	private ArrayList<Patient> patientRegistered;
	private ArrayList<Patient> patientTransported;
	
	public Nurse(String edName, String name, String surname, String state){
		super();
		
		Nurse.compteurNurseId += 1;
		this.setId(Nurse.compteurNurseId);
		
		this.setEdName(edName);
		this.setName(name);
		this.setSurname(surname);
		this.setState(state);
		
		this.patientRegistered = new ArrayList<Patient>();
		this.patientTransported = new ArrayList<Patient>();
	}	
	public Nurse(String edName){
		super();

		Nurse.compteurNurseId += 1;
		this.setId(Nurse.compteurNurseId);

		this.setEdName(edName);
		this.setName("Nurse" + Integer.toString(this.getId()));
		this.setSurname("Nurse" + Integer.toString(this.getId()));
		this.setState("Free");
		
		this.patientRegistered = new ArrayList<Patient>();
		this.patientTransported = new ArrayList<Patient>();
	}
	
	
	@Override
	public void create(){
		System.out.println("Création d'une infirmière :\n" + this.toString());
	}
	
	public void register(Patient patient){
		
	}
	
	public void findRoom(){
		
	}
	
	public void transport(Patient patient){
		
	}
	
	public static void main(String[] args) {
		
		//get Abstract Factory
		Factory.AbstractFactory humanFactory = FactoryCreator.getFactory("HUMAN");
		
		//create different objects
		Nurse nurse1 = (Nurse) humanFactory.getStaff("Hosp1", "NURSE");
		System.out.println(nurse1.toString());
		
		Patient patient1 = (Patient) humanFactory.getPatient("ED1", "L5", new Time());
		System.out.println(patient1.toString());
		
		Nurse nurse2 = (Nurse) humanFactory.getStaff("Hosp2", "NURSE");
		System.out.println(nurse2.toString());
		
		Patient patient2 = (Patient) humanFactory.getPatient("ED2", "L5", new Time());
		System.out.println(patient2.toString());
		
		Physician phys1 = (Physician) humanFactory.getStaff("Hosp1", "PHYSICIAN");
		System.out.println(phys1.toString());
		
		Patient patient3 = (Patient) humanFactory.getPatient("ED2", "L5", new Time());
		System.out.println(patient3.toString());
		
		Transporter trans1 = (Transporter) humanFactory.getStaff("Hosp2", "TRANSPORTER");
		System.out.println(trans1.toString());
		
		Patient patient4 = (Patient) humanFactory.getPatient("ED2", "L5", new Time());
		System.out.println(patient4.toString());
		
		Nurse nurse3 = (Nurse) humanFactory.getStaff("Hosp1", "NURSE");
		System.out.println(nurse1.toString());
		
		Patient patient5= (Patient) humanFactory.getPatient("ED1", "L5", new Time());
		System.out.println(patient5.toString());
		
		Transporter trans2 = (Transporter) humanFactory.getStaff("Hosp2", "TRANSPORTER");
		System.out.println(trans2.toString());
		
		Patient patient6 = (Patient) humanFactory.getPatient("ED2", "L5", new Time());
		System.out.println(patient6.toString());
		
		
		
	}

}
