package Factory;
import Events.Time;

public abstract class AbstractFactory {

	abstract public Human getStaff(String edName, String staffType); 
	abstract public Human getStaff(String edName, String staffType, String name, String surname, String state);
	abstract public Human getPatient(String edName, String severityLevel, Time arrivalTime);
	abstract public Human getPatient(String edName, String name, String surname, String state, String healthInsurance, String severityLevel, Time arrivalTime);
	abstract public Room getRoom(String edName, String roomType);
	abstract public Facility getFacility (String edName, String facilityType);
	

}
