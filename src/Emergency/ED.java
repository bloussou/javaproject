package Emergency;

import java.util.ArrayList;

import Events.Time;
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
	
	/**
	 * le compteur d'ED, il permet que chaque ED ai un id unique
	 */
	private static int compteurEDId;
	/**
	 * le nom de l'hopital si il en a un
	 * 
	 * @see ED#setName(String)
	 * @see ED#getName()
	 */
	private String name;
	/**
	 * Country name of the ED
	 * 
	 * @see ED#setCountry(String)
	 * @see ED#getCountry()
	 */
	private String country;
	/**
	 * l'id unique de chaque hopital
	 * 
	 * L'id est unique et ne peut pas être changé, il est juste affichable
	 * 
	 * @see ED#getId()
	 */
	private int id;
	/**
	 * La liste de tout les patients enregistrés dans l'hopital. 
	 * 
	 * @see ED#getEdRegister()
	 */
	private ArrayList<Patient> edRegister;
	/**
	 * La liste des nurses de l'hôpital et de leur état.
	 * Elle est composées de trois sous listes sous cette forme :
	 * [[idle],[transporting],[ofDuty]]
	 * 
	 * @see ED#getDbNurse()
	 */
	private ArrayList<ArrayList<Nurse>> dbNurse; 
	/**
	 * La liste des médecins de l'hôpital et de leur état.
	 * Elle est composées de trois sous listes sous cette forme :
	 * [[idle],[visiting],[ofDuty]]
	 * 
	 * @see ED#getDbPhysician()
	 */
	private ArrayList<ArrayList<Physician>> dbPhysician; 
	/**
	 * La liste des transporteurs de l'hôpital et de leur état.
	 * Elle est composées de trois sous listes sous cette forme :
	 * [[idle],[transportation],[ofDuty]]
	 * 
	 * @see ED#getDbTransporter() 
	 */
	private ArrayList<ArrayList<Transporter>> dbTransporter; 
	/**
	 * La liste des patients de l'hôpital et de leur état.
	 * Elle est composées de 15 sous listes sous cette forme :
	 * [[arriving],[registered],[transporting],[waitingForConsultation],[inConsultation],[waitingForMRI],[waitingForBloodTest],[waitingForRadio],[transportation],[waitingForMRIT],[waitingForBloodTestT],[waitingForRadioT],[bloodTested],[mriTested],[radioTested],[released]]
	 * 
	 * @see ED#getDbPatient() 
	 */
	private ArrayList<ArrayList<Patient>> dbPatient; 
	/**
	 * La liste des stretchers de l'hôpital et de leur état.
	 * Elle est composées de deux sous listes sous cette forme :
	 * [[free],[occupied]]
	 * 
	 * @see ED#getDbStretcher() 
	 */
	private ArrayList<ArrayList<Stretcher>> dbStretcher; 
	/**
	 * La liste des waiting rooms de l'hôpital et de leur état.
	 * Elle est composées de deux sous listes sous cette forme :
	 * [[available],[full]]
	 * 
	 * @see ED#getDbWaitingRoom() 
	 */
	private ArrayList<ArrayList<WaitingRoom>> dbWaitingRoom; 
	/**
	 * La liste des box rooms de l'hôpital et de leur état.
	 * Elle est composées de deux sous listes sous cette forme :
	 * [[free],[occupied]]
	 * 
	 * @see ED#getDbBoxRoom()
	 */
	private ArrayList<ArrayList<BoxRoom>> dbBoxRoom; 
	/**
	 * La liste des shockrooms de l'hôpital et de leur état.
	 * Elle est composées de deux sous listes sous cette forme :
	 * [[free],[occupied]]
	 * 
	 * @see ED#getDbShockRoom() 
	 */
	private ArrayList<ArrayList<ShockRoom>> dbShockRoom; 
	/**
	 * La liste des bloodrooms de l'hôpital et de leur état.
	 * Elle est composées de deux sous listes sous cette forme :
	 * [[free],[occupied]]
	 * 
	 * @see ED#getDbBloodRoom() 
	 */
	private ArrayList<ArrayList<BloodRoom>> dbBloodRoom; 
	/**
	 * La liste des mriroom de l'hôpital et de leur état.
	 * Elle est composées de deux sous listes sous cette forme :
	 * [[free],[occupied]]
	 * 
	 * @see ED#getDbMRIRoom() 
	 */
	private ArrayList<ArrayList<MRIRoom>> dbMRIRoom; 
	/**
	 * La liste des radioroom de l'hôpital et de leur état.
	 * Elle est composées de deux sous listes sous cette forme :
	 * [[free],[occupied]]
	 * 
	 * @see ED#getDbRadioRoom() 
	 */
	private ArrayList<ArrayList<RadioRoom>> dbRadioRoom; 

	public Time time;
	
	
	/**
	 * Constructor of the ed taking two parameters. It sets all the db ofthe ED and give him a unique ID
	 * @param name
	 * 			the name of the ed
	 * @param country
	 * 			the country of the ED
	 * 
	 * @see ED#country
	 * @see ED#id
	 * @see ED#name
	 * @see ED#dbNurse
	 * @see ED#dbBloodRoom
	 * @see ED#dbBoxRoom
	 * @see ED#dbPhysician
	 * @see ED#dbTransporter
	 * @see ED#dbPatient
	 * @see ED#dbStretcher
	 * @see ED#dbWaitingRoom
	 * @see ED#dbShockRoom
	 * @see ED#dbBloodRoom
	 * @see ED#dbMRIRoom
	 * @see ED#dbRadioRoom
	 * @see ED#edRegister
	 * 
	 */
	public ED(String name, String country){
		
		ED.compteurEDId += 1;
		this.setId(ED.compteurEDId);
		
		this.time = Time.getInstanceTime();
		
		this.setName(name);
		this.setCountry(country);
		
		//add the list "idle", "transporting", "offDuty"
		this.dbNurse = new ArrayList<ArrayList<Nurse>>();
		for (int j = 0;j < 3; j++){
			this.dbNurse.add(new ArrayList<Nurse>());
		}
		
		//add the list "idle", "transporting", "offDuty"
		this.dbPhysician = new ArrayList<ArrayList<Physician>>();
		for (int j = 0;j < 3; j++){
			this.dbPhysician.add(new ArrayList<Physician>());
		}
		
		//add the list "idle", "transporting", "offDuty"
		this.dbTransporter = new ArrayList<ArrayList<Transporter>>();
		for (int j = 0;j < 3; j++){
			this.dbTransporter.add(new ArrayList<Transporter>());
		}
		
		//add the list as the state list of states for patients!
		this.dbPatient = new ArrayList<ArrayList<Patient>>();
		for (int j = 0;j < 17; j++){
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

		//instanciate the ED register
		this.edRegister = new ArrayList<Patient>();
		
		
		
	}
	
	/**
	 * 
	 * @return {@link ED#dbNurse}
	 */
	public ArrayList<ArrayList<Nurse>> getDbNurse() {
		return dbNurse;
	}
	
	/*


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
	*/

	/**
	 * @return 
	 * 		{@link ED#dbPhysician}
	 * 
	 */
	public ArrayList<ArrayList<Physician>> getDbPhysician() {
		return dbPhysician;
	}

	/**
	 * 
	 * @return
	 * 		{@link ED#dbTransporter}
	 * 
	 */
	public ArrayList<ArrayList<Transporter>> getDbTransporter() {
		return dbTransporter;
	}

	/**
	 * 
	 * @return
	 * 		{@link ED#dbPatient}
	 * 
	 */
	public ArrayList<ArrayList<Patient>> getDbPatient() {
		return dbPatient;
	}

	/**
	 * 
	 * @return
	 * 		{@link ED#dbStretcher}
	 */
	public ArrayList<ArrayList<Stretcher>> getDbStretcher() {
		return dbStretcher;
	}

	/**
	 * 
	 * @return
	 * 		{@link ED#dbWaitingRoom}
	 */
	public ArrayList<ArrayList<WaitingRoom>> getDbWaitingRoom() {
		return dbWaitingRoom;
	}

	/**
	 * 
	 * @return
	 * 		{@link ED#dbBoxRoom}
	 
	 */
	public ArrayList<ArrayList<BoxRoom>> getDbBoxRoom() {
		return dbBoxRoom;
	}

	/**
	 * 
	 * @return
	 * 		{@link ED#dbBloodRoom}
	 */
	public ArrayList<ArrayList<BloodRoom>> getDbBloodRoom() {
		return dbBloodRoom;
	}

	/**
	 * 
	 * @return
	 * 		{@link ED#dbMRIRoom}
	 */
	public ArrayList<ArrayList<MRIRoom>> getDbMRIRoom() {
		return dbMRIRoom;
	}
	
	/**
	 * 
	 * @return
	 * 		{@link ED#dbRadioRoom}
	 */
	public ArrayList<ArrayList<RadioRoom>> getDbRadioRoom() {
		return dbRadioRoom;
	}

	/**
	 * 
	 * @return
	 * 		{@link ED#dbShockRoom}
	 */
	public ArrayList<ArrayList<ShockRoom>> getDbShockRoom() {
		return dbShockRoom;
	}

	
	
	/**
	 * 
	 * @return
	 * 		{@link ED#compteurEDId}
	 */
	public static int getCompteurEDId() {
		return compteurEDId;
	}
	/**
	 * 
	 * @return
	 * 		{@link ED#name}
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the name of the ED
	 * @param name
	 * @see ED#name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return {@link ED#country}
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * Set the country of the ED
	 * @param country
	 * @see ED#country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * Set the id of the ED
	 * @param id
	 * @see ED#id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return {@link ED#id}
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @return {@link ED#edRegister}
	 */
	public ArrayList<Patient> getEdRegister() {
		return edRegister;
	}
	/**
	 * Add a patient to the list edRgister
	 * @param patient
	 * 
	 */
	public void addPatientToEdRegister(Patient patient) {
		this.edRegister.add(patient);
	}
		
	
	/**
	 * remove a patient from the list edRegister
	 * @param patient
	 */
	public void removePatientFromEdRegister(Patient patient) {
		this.edRegister.remove(patient);
	}
	
	
}
	
	
	
	

