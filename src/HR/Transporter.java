package HR;
import java.util.*;

import Factory.Human;


public class Transporter extends Factory.Human{
	
	private static int compteurTransporterId;
	private ArrayList<Patient> patientTransported;
	
	public Transporter(String edName, String name, String surname, String state){
		super();
		
		Transporter.compteurTransporterId += 1;
		this.setId(Transporter.compteurTransporterId);
		
		this.setEdName(edName);
		this.setName(name);
		this.setSurname(surname);
		this.setState(state);
		
		this.patientTransported = new ArrayList<Patient>();
	}
	public Transporter(String edName){
		super();
		
		Transporter.compteurTransporterId += 1;
		this.setId(Transporter.compteurTransporterId);
		
		this.setEdName(edName);
		this.setName("Nurse" + Integer.toString(this.getId()));
		this.setSurname("Nurse" + Integer.toString(this.getId()));
		this.setState("Free");
		
		this.patientTransported = new ArrayList<Patient>();
	}
	
	
	
	@Override
	public void create() {
		
	}
	
	public void transport(){
		
	}
	
	
}
