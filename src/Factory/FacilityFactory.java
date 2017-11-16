package Factory;

import Emergency.ED;
import Events.*;
import Facilities.*;
import HR.Human;
import Rooms.Room;

public class FacilityFactory extends AbstractFactory {

	@Override
	public Human getStaff(String staffType, ED ed) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Human getStaff(String staffType, ED ed, String name, String surname, String state) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Human getPatient(ED ed, String severityLevel, TimeStamp arrivalTime) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Human getPatient(ED ed, String name, String surname, String state, String healthInsurance,
			String severityLevel, TimeStamp arrivalTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room getRoom(String roomType, ED ed) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Room getRoom(String roomType, ED ed, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Facility getFacility(String facilityType, ED ed) {
		if(facilityType.equalsIgnoreCase("STRETCHER")){
			return new Stretcher(ed);
		}
		return null;
	}
	@Override
	public Facility getFacility(String facilityType, ED ed, String name) {
		if(facilityType.equalsIgnoreCase("STRETCHER")){
			return new Stretcher(ed, name);
		}
		return null;
	}
	
}
