package Events;

import java.util.ArrayList;

import Emergency.ED;

public class EventsManager {

	public ArrayList<Event> inProgress;
	public ArrayList<ED> eds;
	
	public EventsManager() {
		this.inProgress = new ArrayList<Event>();
		this.eds = new ArrayList<ED>();
	}
	
	
	public void checkNewEvents(){
		
	}
	
	private void checkNewRegistration(){
		//check for each ED if new Registrations have to be done
		for (ED ed : eds) {
			
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
	}
	
	private void checkNewTransport_Nurse(){
		//check for each ED if new Registrations have to be done
		for (ED ed : eds) {
			
			//While there is an 'idle' Nurse and an 'arrived' patient --> match them together by creating an event Registration
			while(!ed.getDbNurse().get(0).isEmpty() && !ed.getDbPatient().get(1).isEmpty() && !ed.getDbWaitingRoom().get(1).isEmpty()){
				
				Transport_Nurse newTransport_Nurse = new Transport_Nurse();
				
				// Insert it in InProgress ordered by Event.endTime
				int insertionIndex = 0;
				while (newTransport_Nurse.getEndTime().getTimeStamp() > this.inProgress.get(insertionIndex).getEndTime().getTimeStamp() && insertionIndex < this.inProgress.size()){
					insertionIndex +=1;
				}
				this.inProgress.add(insertionIndex, newTransport_Nurse);
			}
		}
	}
	
	public void timeGoesToNextEventEnd(){
		
	}
	
	public static void main(String[] args) {
		
		
		
		
		
	}
	

}
