package Factory;

import Events.Time;
import Facilities.Facility;
import HR.Human;
import Rooms.*;

public class RoomFactory extends AbstractFactory{

	@Override
	public Human getStaff(String staffType, String edName) {
		return null;
	}
	@Override
	public Human getStaff(String staffType, String edName, String name, String surname, String state) {
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
	public Room getRoom(String roomType, String edName) {
		if(roomType.equalsIgnoreCase("WAINTINGROOM")){
			return new WaitingRoom(edName);
		}
		else if(roomType.equalsIgnoreCase("BOXROOM")){
			return new BoxRoom(edName);
		}
		else if(roomType.equalsIgnoreCase("SHOCKROOM")){
			return new ShockRoom(edName);
		}
		else if(roomType.equalsIgnoreCase("RADIOROOM")){
			return new RadioRoom(edName);
		}
		else if(roomType.equalsIgnoreCase("MRIROOM")){
			return new MRIRoom(edName);
		}
		else if(roomType.equalsIgnoreCase("BLOODROOM")){
			return new BloodRoom(edName);
		}
		else if(roomType.equalsIgnoreCase("CORRIDOR")){
			return new Corridor(edName);
		}
		return null;
	}
	@Override
	public Room getRoom(String roomType, String edName, String name) {
		if(roomType.equalsIgnoreCase("WAINTINGROOM")){
			return new WaitingRoom(edName, name);
		}
		else if(roomType.equalsIgnoreCase("BOXROOM")){
			return new BoxRoom(edName, name);
		}
		else if(roomType.equalsIgnoreCase("SHOCKROOM")){
			return new ShockRoom(edName, name);
		}
		else if(roomType.equalsIgnoreCase("RADIOROOM")){
			return new RadioRoom(edName, name);
		}
		else if(roomType.equalsIgnoreCase("MRIROOM")){
			return new MRIRoom(edName, name);
		}
		else if(roomType.equalsIgnoreCase("BLOODROOM")){
			return new BloodRoom(edName, name);
		}
		else if(roomType.equalsIgnoreCase("CORRIDOR")){
			return new Corridor(edName, name);
		}
		return null;
	}
	
	
	@Override
	public Facility getFacility(String facilityType, String edName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Facility getFacility(String facilityType, String edName, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
