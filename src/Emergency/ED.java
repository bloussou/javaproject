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
	private ArrayList<ArrayList<Nurse>> dbNurse; // [[idle],[transporting],[ofDuty]]
	private ArrayList<ArrayList<Physician>> dbPhysician; // [[idle],[visiting],[ofDuty]]
	private ArrayList<ArrayList<Transporter>> dbTransporter; // [[idle],[transportation],[ofDuty]]
	private ArrayList<ArrayList<Patient>> dbPatient; // [[arrived],[registered],[transporting],[waitingForConsultation],[inConsultation],[waitingForMRI],[waitingForBloodTest],[waitingForRadio],[transportation],[waitingForMRIT],[waitingForBloodTestT],[waitingForRadioT],[bloodTested],[mriTested],[radioTested],[released]]
	private ArrayList<ArrayList<Stretcher>> dbStretcher; // [[free],[occupied]]
	private ArrayList<ArrayList<WaitingRoom>> dbWaitingRoom; // [[available],[full]]
	private ArrayList<ArrayList<BoxRoom>> dbBoxRoom; // [[free],[occupied]]
	private ArrayList<ArrayList<ShockRoom>> dbShockRoom; // [[free],[occupied]]
	private ArrayList<ArrayList<BloodRoom>> dbBloodRoom; // [[free],[occupied]]
	private ArrayList<ArrayList<MRIRoom>> dbMRIRoom; // [[free],[occupied]]
	private ArrayList<ArrayList<RadioRoom>> dbRadioRoom; // [[free],[occupied]]

	
	
	
	
	
	public ED(String name, String country){
		
		ED.compteurEDId += 1;
		this.setId(ED.compteurEDId);
		
		this.setName(name);
		this.setCountry(country);
		
		//add the list "idle", "transporting", "ofDuty"
		this.dbNurse = new ArrayList<ArrayList<Nurse>>();
		for (int j = 0;j < 3; j++){
			this.dbNurse.add(new ArrayList<Nurse>());
		}
		
		//add the list "idle", "transporting", "ofDuty"
		this.dbPhysician = new ArrayList<ArrayList<Physician>>();
		for (int j = 0;j < 3; j++){
			this.dbPhysician.add(new ArrayList<Physician>());
		}
		
		//add the list "idle", "transporting", "ofDuty"
		this.dbTransporter = new ArrayList<ArrayList<Transporter>>();
		for (int j = 0;j < 3; j++){
			this.dbTransporter.add(new ArrayList<Transporter>());
		}
		
		//add the list as the state list of states for patients!
		this.dbPatient = new ArrayList<ArrayList<Patient>>();
		for (int j = 0;j < 16; j++){
			this.dbPatient.add(new ArrayList<Patient>());
		}
		
		//add the list "free", "occupied"
		this.dbStretcher = new ArrayList<ArrayList<Stretcher>>();
		for (int j = 0 ; j < 2 ; j++){
			this.dbStretcher.add(new ArrayList<Stretcher>());
		}
		
		//add the list "free", "occupied"
		this.dbWaitingRoom = new ArrayList<ArrayList<WaitingRoom>>();
		for (int j = 0 ; j < 2 ; j++){
			this.dbWaitingRoom.add(new ArrayList<WaitingRoom>());
		}
		
		//add the list "free", "occupied"
		this.dbBoxRoom = new ArrayList<ArrayList<BoxRoom>>();
		for (int j = 0 ; j < 2 ; j++){
			this.dbBoxRoom.add(new ArrayList<BoxRoom>());
		}
		
		//add the list "free", "occupied"
		this.dbShockRoom = new ArrayList<ArrayList<ShockRoom>>();
		for (int j = 0 ; j < 2 ; j++){
			this.dbShockRoom.add(new ArrayList<ShockRoom>());
		}
		
		//add the list "free", "occupied"
		this.dbBloodRoom = new ArrayList<ArrayList<BloodRoom>>();
		for (int j = 0 ; j < 2 ; j++){
			this.dbBloodRoom.add(new ArrayList<BloodRoom>());
		}
		
		//add the list "free", "occupied"
		this.dbMRIRoom = new ArrayList<ArrayList<MRIRoom>>();
		for (int j = 0 ; j < 2 ; j++){
			this.dbMRIRoom.add(new ArrayList<MRIRoom>());
		}
		//add the list "free", "occupied"
		this.dbRadioRoom = new ArrayList<ArrayList<RadioRoom>>();
		for (int j = 0 ; j < 2 ; j++){
			this.dbRadioRoom.add(new ArrayList<RadioRoom>());
		}

		
		this.edRegister = new ArrayList<Patient>();
		
		
		
	}
	
	
	public ArrayList<ArrayList<Nurse>> getDbNurse() {
		return dbNurse;
	}
	
	/*
	 * update Nurse after a state changement of the nurse
	 */

	public void updateNurse(Nurse nurse) {
		if(nurse.getState().equals("idle") && this.dbNurse.get(1).contains(nurse) ){
			this.dbNurse.get(1).remove(nurse);
			this.dbNurse.get(0).add(nurse);
		}
		else if (nurse.getState().equals("idle") && this.dbNurse.get(2).contains(nurse)){
			this.dbNurse.get(2).remove(nurse);
			this.dbNurse.get(0).add(nurse);
		}
		else if (nurse.getState().equals("transporting") && this.dbNurse.get(0).contains(nurse)){
			this.dbNurse.get(0).remove(nurse);
			this.dbNurse.get(1).add(nurse);
		}
		else if (nurse.getState().equals("transporting") && this.dbNurse.get(2).contains(nurse)){
			this.dbNurse.get(2).remove(nurse);
			this.dbNurse.get(1).add(nurse);
		}
		else if (nurse.getState().equals("ofDuty") && this.dbNurse.get(0).contains(nurse)){
			this.dbNurse.get(0).remove(nurse);
			this.dbNurse.get(2).add(nurse);
		}
		else if (nurse.getState().equals("ofDuty") && this.dbNurse.get(1).contains(nurse)){
			this.dbNurse.get(1).remove(nurse);
			this.dbNurse.get(2).add(nurse);
		}
	}
	














	public ArrayList<ArrayList<Physician>> getDbPhysician() {
		return dbPhysician;
	}






	public ArrayList<ArrayList<Transporter>> getDbTransporter() {
		return dbTransporter;
	}






	public ArrayList<ArrayList<Patient>> getDbPatient() {
		return dbPatient;
	}






	public ArrayList<ArrayList<Stretcher>> getDbStretcher() {
		return dbStretcher;
	}






	public ArrayList<ArrayList<WaitingRoom>> getDbWaitingRoom() {
		return dbWaitingRoom;
	}






	public ArrayList<ArrayList<BoxRoom>> getDbBoxRoom() {
		return dbBoxRoom;
	}






	public ArrayList<ArrayList<BloodRoom>> getDbBloodRoom() {
		return dbBloodRoom;
	}






	public ArrayList<ArrayList<MRIRoom>> getDbMRIRoom() {
		return dbMRIRoom;
	}






	public ArrayList<ArrayList<RadioRoom>> getDbRadioRoom() {
		return dbRadioRoom;
	}






	public ArrayList<ArrayList<ShockRoom>> getDbShockRoom() {
		return dbShockRoom;
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
	
	
	
	

