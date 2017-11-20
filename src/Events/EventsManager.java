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
		for (ED ed : eds) {
			
			while(!ed.getDbNurse().get(0).isEmpty() && !ed.getDbPatient().get(0).isEmpty() ){
				
			}
			
		}
		
		
	}
	
	public void timeGoesToNextEventEnd(){
		
	}
	
	public static void main(String[] args) {
		
		EventsManager EM = new EventsManager();
		ED hosp1 = new ED("Hosp1", "France");
		ED hosp2 = new ED("Hosp2", "France");
		
		EM.eds.add(hosp2);
		EM.eds.add(hosp1);
		
		EM.checkNewRegistration();
				
	}
	
}
