package HR;
import java.util.*;

import Factory.FactoryCreator;
import Factory.Human;

public class Nurse extends Factory.Human{
	
	private ArrayList<Patient> patientRegistered;
	private ArrayList<Patient> patientTransported;
	
	public Nurse(String edName, String name, String surname, String state){
		
		Human.setCompteurHumanId(Human.getCompteurHumanId()+1);
		this.setId(Human.getCompteurHumanId());
		
		this.setEdName(edName);
		this.setName(name);
		this.setSurname(surname);
		this.setState(state);
	}
	
	public Nurse(String edName){
		Human.setCompteurHumanId(Human.getCompteurHumanId()+1);
		this.setId(Human.getCompteurHumanId());
		
		this.setEdName(edName);
		this.setName("Nurse" + Integer.toString(this.getId()));
		this.setSurname("Nurse" + Integer.toString(this.getId()));
		this.setState("Free");
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
		
		//create Nurse
		
	}

}
