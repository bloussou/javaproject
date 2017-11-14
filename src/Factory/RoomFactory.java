package Factory;

import Events.Time;

public class RoomFactory extends AbstractFactory{

	@Override
	public Human getStaff(String edName, String staffType) {
		return null;
	}
	@Override
	public Human getStaff(String edName, String staffType, String name, String surname, String state) {
		return null;
	}
	@Override
	public Human getPatient(String edName, String severityLevel, Time arrivalTime) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Human getPatient(String edName, String name, String surname, String state, String healthInsurance,
			String severityLevel, Time arrivalTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room getRoom(String edName, String roomType) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Facility getFacility(String edName, String facilityType) {
		// TODO Auto-generated method stub
		return null;
	}

}
