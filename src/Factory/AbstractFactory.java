package Factory;

public abstract class AbstractFactory {

	abstract public Human getHuman(String edName, String humanType); 
	abstract public Room getRoom(String edName, String roomType);
	abstract public Facility getFacility (String edName, String facilityType);
	

}
