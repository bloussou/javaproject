package Emergency;

import java.util.ArrayList;

import Facilities.Stretcher;
import HR.Nurse;
import HR.Patient;
import HR.Physician;
import HR.Transporter;
import Rooms.BloodRoom;
import Rooms.BoxRoom;
import Rooms.MRIRoom;
import Rooms.RadioRoom;
import Rooms.ShockRoom;
import Rooms.WaitingRoom;

public class ED {
	private static int compteurEDId;
	private String name;
	private String country;
	private int id;
	private ArrayList<Patient> edRegister;
	private ArrayList<ArrayList<Nurse>> dbNurse;
	private ArrayList<ArrayList<Physician>> dbPhysician;
	private ArrayList<ArrayList<Transporter>> dbTransporter;
	private ArrayList<ArrayList<Patient>> dbPatient;
	private ArrayList<ArrayList<Stretcher>> dbStretcher;
	private ArrayList<ArrayList<WaitingRoom>> dbWaitingRoom;
	private ArrayList<ArrayList<BoxRoom>> dbBoxRoom;
	private ArrayList<ArrayList<BloodRoom>> dbBloodRoom;
	private ArrayList<ArrayList<MRIRoom>> dbMRIRoom;
	private ArrayList<ArrayList<RadioRoom>> dbRadioRoom;
	private ArrayList<ArrayList<ShockRoom>> dbShockRoom;
	
	
	
	
	
	public ED(String name, String country){
		
		ED.compteurEDId += 1;
		this.setId(ED.compteurEDId);
		
		this.setName(name);
		this.setCountry(country);
		
		//add the list "idle", "transporting", "ofDuty"
		for (int j = 0;j < 3; j++){
			this.dbNurse.add(new ArrayList<Nurse>());
		}
		
		//add the list "idle", "transporting", "ofDuty"
		for (int j = 0;j < 3; j++){
			this.dbPhysician.add(new ArrayList<Physician>());
		}
		
		//add the list "idle", "transporting", "ofDuty"
		for (int j = 0;j < 3; j++){
			this.dbTransporter.add(new ArrayList<Transporter>());
		}
		
		//add the list as the state list of states !
		for (int j = 0;j < 16; j++){
			this.dbPatient.add(new ArrayList<Patient>());
		}
		
		//add the list "free", "occupied"
		for (int j = 0 ; j < 2 ; j++){
			this.dbStretcher.add(new ArrayList<Stretcher>());
		}
		
		//add the list "free", "occupied"
		for (int j = 0 ; j < 2 ; j++){
			this.dbWaitingRoom.add(new ArrayList<WaitingRoom>());
		}
		
		//add the list "free", "occupied"
		for (int j = 0 ; j < 2 ; j++){
			this.dbBloodRoom.add(new ArrayList<BloodRoom>());
		}
		
		//add the list "free", "occupied"
				for (int j = 0 ; j < 2 ; j++){
					this.dbStretcher.add(new ArrayList<Stretcher>());
		}
		//add the list "free", "occupied"
		for (int j = 0 ; j < 2 ; j++){
			this.dbStretcher.add(new ArrayList<Stretcher>());
		}
		
		
		
		
	}

	
	
	
	
	
	public ArrayList<ArrayList<Nurse>> getDbNurse() {
		return dbNurse;
	}
	
	/*
	 * update Nurse after a state changement of the nurse
	 */

	public void updateNurse(Nurse nurse) {
		if(nurse.getState().equals("idle")){
			this.dbNurse(1).
		}
	}
	
	public void removeNurse()






	public ArrayList<Physician> getDbPhysician() {
		return dbPhysician;
	}

	public void setDbPhysician(ArrayList<Physician> dbPhysician) {
		this.dbPhysician = dbPhysician;
	}






	public ArrayList<Transporter> getDbTransporter() {
		return dbTransporter;
	}

	public void setDbTransporter(ArrayList<Transporter> dbTransporter) {
		this.dbTransporter = dbTransporter;
	}






	public ArrayList<Patient> getDbPatient() {
		return dbPatient;
	}

	public void setDbPatient(ArrayList<Patient> dbPatient) {
		this.dbPatient = dbPatient;
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

	public void addPatientToEdRegister(Patient patient) {
		this.edRegister.add(patient);
	}
		
	public void removePatientFromEdRegister(Patient patient) {
		this.edRegister.remove(patient);
	}
	
	
}
	
	
	
	

