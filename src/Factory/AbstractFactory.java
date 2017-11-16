package Factory;

import Emergency.ED;
import Events.*;
import Facilities.Facility;
import HR.Human;
import Rooms.Room;

public abstract class AbstractFactory {

	abstract public Human getStaff(String staffType, ED ed); 
	abstract public Human getStaff(String staffType, ED ed, String name, String surname, String state);
	abstract public Human getPatient(ED ed, String severityLevel, TimeStamp arrivalTime);
	abstract public Human getPatient(ED ed, String name, String surname, String state, String healthInsurance, String severityLevel, TimeStamp arrivalTime);
	abstract public Room getRoom(String roomType, ED ed);
	abstract public Room getRoom(String roomType, ED ed, String name);
	abstract public Facility getFacility (String facilityType, ED ed);
	abstract public Facility getFacility(String facilityType, ED ed, String name);
	

}
