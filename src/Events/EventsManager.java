package Events;

import java.util.ArrayList;
import HR.*;
import Emergency.ED;

public class EventsManager {

	public ArrayList<Event> inProgress;
	public ArrayList<ED> eds;
	
	/**
	 * The constructor initializes the event list InProgress, and the EDs' list
	 */
	public EventsManager() {
		this.inProgress = new ArrayList<Event>();
		this.eds = new ArrayList<ED>();
	}
	
	/**
	 * For each ED in the eds' list, check if new events have to be created
	 * @param eds
	 */
	public void checkNewEvents(ArrayList<ED> eds){
		
		for (ED ed : eds) {
			
			this.checkNewRegistration(ed);
			this.checkNewTransport_Nurse(ed);
	
		}
		
		
	}

	
	
	
	private void checkNewRegistration(ED ed){
		//check for each ED if new Registrations have to be done
			
			//While there is an 'idle' Nurse and an 'arrived' patient --> match them together by creating an event Registration
			while(!ed.getDbNurse().get(0).isEmpty() && !ed.getDbPatient().get(0).isEmpty() ){
				
				Registration newRegistration = new Registration(ed, ed.getDbPatient().get(0).get(0), ed.getDbNurse().get(0).get(0));
				
				// Insert it in InProgress ordered by Event.endTime
				int insertionIndex = 0;
				while (newRegistration.getEndTime().getTimeStamp() > this.inProgress.get(insertionIndex).getEndTime().getTimeStamp() && insertionIndex < this.inProgress.size()){
					insertionIndex +=1;
				}
				this.inProgress.add(insertionIndex, newRegistration);
			}
	}
	
	private void checkNewTransport_Nurse(ED ed){
		//check for each ED if new Transportation by a Nurse to a WaitingRoom have to be done

			//While there is an 'idle' Nurse and a 'registred' patient and an 'available' WaitingRoom --> match them together by creating an event Transport_Nurse
			while(!ed.getDbNurse().get(0).isEmpty() && !ed.getDbPatient().get(1).isEmpty() && !ed.getDbWaitingRoom().get(1).isEmpty()){
				
				Transport_Nurse newTransport_Nurse = new Transport_Nurse(ed, ed.getDbPatient().get(1).get(0),ed.getDbNurse().get(0).get(0), ed.getDbWaitingRoom().get(0).get(0));
				
				// Insert it in InProgress ordered by Event.endTime
				int insertionIndex = 0;
				while (newTransport_Nurse.getEndTime().getTimeStamp() > this.inProgress.get(insertionIndex).getEndTime().getTimeStamp() && insertionIndex < this.inProgress.size()){
					insertionIndex +=1;
				}
				this.inProgress.add(insertionIndex, newTransport_Nurse);
			}
	}
	
	private void checkNewConsultation(ED ed){
		//check for each ED if new Consultation by a Physician in a BoxRoom for L5, L4 and L3 Patients and ShockRoom for L2, L1 Patients have to be done

		//extract the lists of "waitingforConsultation" patients : the first filled with shockRoom Severity level, and the other ther rest
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
		
			//While there is an 'idle' Physician and a 'WaitingForConsultation' patient and an 'available' corresponding room --> match them together by creating an event Transport_Nurse
			while(!ed.getDbNurse().get(0).isEmpty() && !ed.getDbPatient().get(1).isEmpty() && !ed.getDbWaitingRoom().get(1).isEmpty()){
				
				Transport_Nurse newTransport_Nurse = new Transport_Nurse(ed, ed.getDbPatient().get(1).get(0),ed.getDbNurse().get(0).get(0), ed.getDbWaitingRoom().get(0).get(0));
				
				// Insert it in InProgress ordered by Event.endTime
				int insertionIndex = 0;
				while (newTransport_Nurse.getEndTime().getTimeStamp() > this.inProgress.get(insertionIndex).getEndTime().getTimeStamp() && insertionIndex < this.inProgress.size()){
					insertionIndex +=1;
				}
				this.inProgress.add(insertionIndex, newTransport_Nurse);
			}
	}
	
	private void checkNewBloodExamination(ED ed) {
		while(!ed.getDbBloodRoom().get(0).isEmpty() && !ed.getDbPatient().get(10).isEmpty()){
			this.insertNewEvent(new BloodExamination(ed.getDbPatient().get(10).get(0), ed, ed.getDbBloodRoom().get(0).get(0)));
		}
	}
	
	private void checkNewMRIExamination(ED ed) {
		while(!ed.getDbMRIRoom().get(0).isEmpty() && !ed.getDbPatient().get(9).isEmpty()){
			this.insertNewEvent(new MRIExamination(ed.getDbPatient().get(9).get(0), ed, ed.getDbMRIRoom().get(0).get(0)));
		}
	}
	
	private void checkNewRadioExamination(ED ed) {
		while(!ed.getDbRadioRoom().get(0).isEmpty() && !ed.getDbPatient().get(11).isEmpty()){
			this.insertNewEvent(new RadioExamination(ed.getDbPatient().get(11).get(0), ed, ed.getDbRadioRoom().get(0).get(0)));
		}
	}
	
	
	private void insertNewEvent(Event event){
		if(this.inProgress.isEmpty()){
			this.inProgress.add(event);
		}
		else {
			int i=0;
			while (event.getEndTime().getTimeStamp() >= inProgress.get(i).getEndTime().getTimeStamp()){
				i++;
			}
			inProgress.add(i, event);
		}
	}
	
	public void timeGoesToNextEventEnd(){
		
	}
	
	public static void main(String[] args) {
		
		
		
		
		
	}
	

}
