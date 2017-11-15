package Factory;

import Events.*;
import Facilities.*;
import HR.Human;
import Rooms.Room;

public class FacilityFactory extends AbstractFactory {

	@Override
	public Human getStaff(String staffType, String edName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Human getStaff(String staffType, String edName, String name, String surname, String state) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Human getPatient(String edName, String severityLevel, TimeStamp arrivalTime) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Human getPatient(String edName, String name, String surname, String state, String healthInsurance,
			String severityLevel, TimeStamp arrivalTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room getRoom(String roomType, String edName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Room getRoom(String roomType, String edName, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Facility getFacility(String facilityType, String edName) {
		if(facilityType.equalsIgnoreCase("STRETCHER")){
			return new Stretcher(edName);
		}
		return null;
	}
	@Override
	public Facility getFacility(String facilityType, String edName, String name) {
		if(facilityType.equalsIgnoreCase("STRETCHER")){
			return new Stretcher(edName, name);
		}
		return null;
	}
	
}
