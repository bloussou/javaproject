package HR;
import java.util.*;

import Emergency.ED;


public class Transporter extends Human{
	
	private static int compteurTransporterId;
	private ArrayList<Patient> patientTransported;
	
	public Transporter(ED ed, String name, String surname, String state){
		super();
		
		Transporter.compteurTransporterId += 1;
		this.setId(Transporter.compteurTransporterId);
		
		this.setEd(ed);
		this.setName(name);
		this.setSurname(surname);
		this.setState(state);
		
		this.patientTransported = new ArrayList<Patient>();
	}
	public Transporter(ED ed){
		super();
		
		Transporter.compteurTransporterId += 1;
		this.setId(Transporter.compteurTransporterId);
		
		this.setEd(ed);
		this.setName("Nurse" + Integer.toString(this.getId()));
		this.setSurname("Nurse" + Integer.toString(this.getId()));
		this.setState("Free");
		
		this.patientTransported = new ArrayList<Patient>();
	}
	
	
	
	@Override
	public void create() {
		
	}
	
	public void transport(Patient patient){
		this.setState("transportation");
		
	}
	
	
}
