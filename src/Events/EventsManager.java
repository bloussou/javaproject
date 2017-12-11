package Events;

import java.util.ArrayList;
import HR.*;
import Rooms.BloodRoom;
import Rooms.MRIRoom;
import Rooms.RadioRoom;
import Emergency.ED;

public class EventsManager {

	private ArrayList<Event> inProgress;
	private ArrayList<ED> eds;
	public Time time;
	
	/**
	 * The constructor initializes the event list InProgress, and the EDs' list
	 */
	public EventsManager(ArrayList<ED> eds) {
		this.inProgress = new ArrayList<Event>();
		this.eds = eds;
		this.time = Time.getInstanceTime();
	}
	

	/**
	 * Processes one step of simulation
	 */
	public void nextStep(){
		this.checkNewEvents(eds);
		this.timeGoesToNextEventEnd();
		this.dequeueEvents();
	}

	
	/**
	 * For each ED in the eds' list, check if new events have to be created
	 * @param eds
	 */
	public void checkNewEvents(ArrayList<ED> eds){
		for (ED ed : eds) {
			this.checkNewRegistration(ed);
			this.checkNewTransport_Nurse(ed);
			this.checkNewConsultation(ed);
			this.checkTransport_transporter(ed);
			this.checkNewBloodExamination(ed);
			this.checkNewMRIExamination(ed);
			this.checkNewRadioExamination(ed);
		}
	}
	
		/**
	 * check in ED if new Registrations have to be done
	 * @param ed
	 */
	public void checkNewRegistration(ED ed){

			TimeStamp ts = new TimeStamp();
			//While there is an 'idle' Nurse and an 'arrived' patient --> match them together by creating an event Registration
			while(!ed.getDbNurse().get(0).isEmpty() && !ed.getDbPatient().get(0).isEmpty() && (ed.getDbPatient().get(0).get(0).getArrivalTime().getTimeStamp()<=ts.getTimeStamp())){
				 
				Registration newRegistration = new Registration(ed, ed.getDbPatient().get(0).get(0), ed.getDbNurse().get(0).get(0));
				
				this.insertNewEvent(newRegistration);
			}
	}
	
	/**
e	 * check in ED if new Transportation by a Nurse to a WaitingRoom have to be done
	 * @param ed
	 */
	public void checkNewTransport_Nurse(ED ed){

			//While there is an 'idle' Nurse and a 'registred' patient and an 'available' WaitingRoom --> match them together by creating an event Transport_Nurse
			while(!ed.getDbNurse().get(0).isEmpty() && !ed.getDbPatient().get(1).isEmpty() && !ed.getDbWaitingRoom().get(0).isEmpty()){
				
				Transport_Nurse newTransport_Nurse = new Transport_Nurse(ed, ed.getDbPatient().get(1).get(0),ed.getDbNurse().get(0).get(0), ed.getDbWaitingRoom().get(0).get(0));
				
				this.insertNewEvent(newTransport_Nurse);
			}
	}
	
	/**
	 * check in ED if new Consultation by a Physician in a BoxRoom for L5, L4 and L3 Patients and ShockRoom for L2, L1 Patients have to be done
	 * @param ed
	 */
	public void checkNewConsultation(ED ed){
		
		//extract the lists of "waitingforConsultation" patients : the first filled with shockRoom Severity level, and the other the rest
		ArrayList<Patient> shockRoomPatientList = new ArrayList<Patient>();
		ArrayList<Patient> boxRoomPatientList = new ArrayList<Patient>();		
		
		for (Patient patient : ed.getDbPatient().get(3)) {
			if (patient.getSeverityLevel() == "L2" || patient.getSeverityLevel() == "L1"){
				shockRoomPatientList.add(patient);
			}
			else {
				boxRoomPatientList.add(patient);
			}
		} 		
		
		//New Consultation for high level of severity Patients
		while(!ed.getDbPhysician().get(0).isEmpty() && !shockRoomPatientList.isEmpty() && (!ed.getDbShockRoom().get(0).isEmpty() || !ed.getDbBoxRoom().get(0).isEmpty())){
			
			Consultation newConsultation;
			
			if(!ed.getDbShockRoom().get(0).isEmpty()){
				newConsultation = new Consultation(ed, shockRoomPatientList.get(0), ed.getDbPhysician().get(0).get(0), ed.getDbShockRoom().get(0).get(0));
				shockRoomPatientList.remove(0);
			}
			else {
				newConsultation = new Consultation(ed, shockRoomPatientList.get(0), ed.getDbPhysician().get(0).get(0), ed.getDbBoxRoom().get(0).get(0));
			}
			
			this.insertNewEvent(newConsultation);
		}
		
		//New Consultation for low level of severity Patients
		while(!ed.getDbPhysician().get(0).isEmpty() && !boxRoomPatientList.isEmpty() && !ed.getDbBoxRoom().get(0).isEmpty()){
			
			Consultation newConsultation = new Consultation(ed, boxRoomPatientList.get(0), ed.getDbPhysician().get(0).get(0), ed.getDbBoxRoom().get(0).get(0));
			boxRoomPatientList.remove(0);
			this.insertNewEvent(newConsultation);
		}
		
	}
		
	/**
	 * check in ED if new BloodExamination have to be done
	 * @param ed
	 */
	public void checkNewBloodExamination(ED ed) {
		while(!ed.getDbPatient().get(10).isEmpty()){
			BloodExamination bloodExamination = new BloodExamination(ed.getDbPatient().get(10).get(0), ed, (BloodRoom) ed.getDbPatient().get(10).get(0).getLocation());
			
			this.insertNewEvent(bloodExamination);
		}
	}
	
	/**
	 * check in ED if new MRIExamination have to be done
	 * @param ed
	 */
	public void checkNewMRIExamination(ED ed) {
		while(!ed.getDbPatient().get(9).isEmpty()){
			
			MRIExamination mriExamination = new MRIExamination(ed.getDbPatient().get(9).get(0), ed, (MRIRoom) ed.getDbPatient().get(9).get(0).getLocation());
			
			this.insertNewEvent(mriExamination);
		}
	}
	
	/**
	 * check in ED if new RadioExamination have to be done
	 * @param ed
	 */
	public void checkNewRadioExamination(ED ed) {
		while(!ed.getDbPatient().get(11).isEmpty()){
			RadioExamination radioExamination = new RadioExamination(ed.getDbPatient().get(11).get(0), ed, (RadioRoom) ed.getDbPatient().get(11).get(0).getLocation());
			
			this.insertNewEvent(radioExamination);
		}
	}
	
	/**
	 * check in ED if new Transport by a transporter have to be done
	 * <li>Check if patients who have been tested are waiting to be transported to another consultation with the physician they have formely met in consultation</li>
	 * <li>Check if patients are waiting to be transported to an MRITest Room</li>
	 * <li>Check if patients are waiting to be transported to an BloodTest Room</li>
	 * <li>Check if patients are waiting to be transported to an RadioTest Room</li>
	 * @param ed
	 */
	public void checkTransport_transporter(ED ed){
		ArrayList<Patient> patientTested = new ArrayList<Patient>();
		for (Patient patient : ed.getDbPatient().get(12)){
			patientTested.add(patient);
		}
		for (Patient patient : ed.getDbPatient().get(13)){
			patientTested.add(patient);
		}
		for (Patient patient : ed.getDbPatient().get(14)){
			patientTested.add(patient);
		}
		
		if (patientTested.isEmpty() || ed.getDbTransporter().get(0).isEmpty()){	
			
		}
		else {
			for (Patient patient : patientTested){
				if (patient.getPhysician().getState() == "idle"){
					if(patient.getSeverityLevel().equalsIgnoreCase("L1") || patient.getSeverityLevel().equalsIgnoreCase("L2")) {
						if(!ed.getDbBoxRoom().get(0).isEmpty()){
							this.insertNewEvent(new Transport_Transporter(ed, patient, ed.getDbTransporter().get(0).get(0), ed.getDbBoxRoom().get(0).get(0)));
						}
						else if(!ed.getDbShockRoom().get(0).isEmpty()){
							this.insertNewEvent(new Transport_Transporter(ed, patient, ed.getDbTransporter().get(0).get(0), ed.getDbShockRoom().get(0).get(0)));
						}
						else {
							//no room available
						}
						
					}
					else {
						if(!ed.getDbBoxRoom().get(0).isEmpty()){
							this.insertNewEvent(new Transport_Transporter(ed, patient, ed.getDbTransporter().get(0).get(0), ed.getDbBoxRoom().get(0).get(0)));
						}
						else {
							//pas de box room
						}
					}
				}
			}
		}
		
		
		//Transport to mri room
		while(!ed.getDbTransporter().get(0).isEmpty() && !ed.getDbPatient().get(5).isEmpty() && !ed.getDbMRIRoom().get(0).isEmpty()){
			Transport_Transporter newTransport_Transporter = new Transport_Transporter(ed, ed.getDbPatient().get(5).get(0),ed.getDbTransporter().get(0).get(0), ed.getDbMRIRoom().get(0).get(0));
			
			this.insertNewEvent(newTransport_Transporter);
		}
		
		//Transport to blood room
		while(!ed.getDbTransporter().get(0).isEmpty() && !ed.getDbPatient().get(6).isEmpty() && !ed.getDbBloodRoom().get(0).isEmpty()){
			
			Transport_Transporter newTransport_Transporter = new Transport_Transporter(ed, ed.getDbPatient().get(6).get(0),ed.getDbTransporter().get(0).get(0), ed.getDbBloodRoom().get(0).get(0));
			
			this.insertNewEvent(newTransport_Transporter);
		}
		
		//Transport to radio room
		while(!ed.getDbTransporter().get(0).isEmpty() && !ed.getDbPatient().get(7).isEmpty() && !ed.getDbRadioRoom().get(0).isEmpty()){
			
			Transport_Transporter newTransport_Transporter = new Transport_Transporter(ed, ed.getDbPatient().get(7).get(0),ed.getDbTransporter().get(0).get(0), ed.getDbRadioRoom().get(0).get(0));
			
			this.insertNewEvent(newTransport_Transporter);
		}
		
		
		
	}
	
	
	
		/**
	 * Insert a new event in the list InProgress, of events in progress. Insert it so that the list is sorted by endTime.
	 * @param event
	 * @see Event#getEndTime()
	 */
	public void insertNewEvent(Event event){
		if(this.inProgress.isEmpty()){
			this.inProgress.add(event);
		}
		else {
			int i=0;
			while (i<this.getInProgress().size() && event.getEndTime().getTimeStamp() >= inProgress.get(i).getEndTime().getTimeStamp() ){
				i++;
			}
			inProgress.add(i, event);
		}
	}
	
	/**
	 * Time flows automatically to the next change in the system :
	 * <li>Either flows to the next Event end</li>
	 * <li>Either flows to the next arrival time</li>
	 * @see Event#getEndTime()
	 * @see Event#endEvent()
	 * @see Patient#getArrivalTime()
	 */
	public void timeGoesToNextEventEnd(){
		
		int nextArrivalTime = 100000000;
		for (ED ed : eds) {
			if(!ed.getDbPatient().get(0).isEmpty()){
				if(ed.getDbPatient().get(0).get(0).getArrivalTime().getTimeStamp() < nextArrivalTime){
					nextArrivalTime = ed.getDbPatient().get(0).get(0).getArrivalTime().getTimeStamp();
				}
			}
		}
		
		int nextEventEndTime = 100000000;
		if (!this.getInProgress().isEmpty()){
			nextEventEndTime = this.getInProgress().get(0).getEndTime().getTimeStamp();
		}

		this.time.timeGoes(Math.min(nextArrivalTime, nextEventEndTime)-time.getTime());
	}
	
	/**
	 * Dequeue all the events in progress that are supposed to end currently
	 */
	public void dequeueEvents(){
		
		
		while (!this.getInProgress().isEmpty() && this.getInProgress().get(0).getEndTime().getTimeStamp() <= time.getTime() ){
			this.getInProgress().get(0).endEvent();
			this.getInProgress().remove(0);
				
		
		}
	}
	
	
	
	public ArrayList<Event> getInProgress() {
		return inProgress;
	}
	public ArrayList<ED> getEds() {
		return eds;
	}
	public void setEds(ArrayList<ED> eds) {
		this.eds = eds;
	}
	public void setInProgress(ArrayList<Event> inProgress) {
		this.inProgress = inProgress;
	}


	

}
