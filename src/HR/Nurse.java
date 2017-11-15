package HR;

import java.util.*;

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

}
