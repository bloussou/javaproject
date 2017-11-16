package Emergency;

import java.util.ArrayList;
import HR.Patient;

public class ED {
	private static int compteurEDId;
	private String name;
	private String country;
	private int id;
	private ArrayList<Patient> edRegister;
	
	public ED(String name, String country){
		
		ED.compteurEDId += 1;
		this.setId(ED.compteurEDId);
		
		this.setName(name);
		this.setCountry(country);
		
	}

	public static int getCompteurEDId() {
		return compteurEDId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public ArrayList<Patient> getEdRegister() {
		return edRegister;
	}

	public void setEdRegister(ArrayList<Patient> edRegister) {
		this.edRegister = edRegister;
	}
	
	
	
	
	
	
	
	
}
