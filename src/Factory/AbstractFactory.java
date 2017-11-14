package Factory;

import Events.Time;
import Facilities.Facility;
import HR.Human;
import Rooms.Room;

public abstract class AbstractFactory {

	abstract public Human getStaff(String staffType, String edName); 
	abstract public Human getStaff(String staffType, String edName, String name, String surname, String state);
	abstract public Human getPatient(String edName, String severityLevel, Time arrivalTime);
	abstract public Human getPatient(String edName, String name, String surname, String state, String healthInsurance, String severityLevel, Time arrivalTime);
	abstract public Room getRoom(String roomType, String edName);
	abstract public Room getRoom(String roomType, String edName, String name);
	abstract public Facility getFacility (String facilityType, String edName);
	abstract public Facility getFacility(String facilityType, String edName, String name);
	

}
