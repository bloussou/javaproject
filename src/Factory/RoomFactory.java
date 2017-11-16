package Factory;

import Emergency.ED;
import Events.*;
import Facilities.Facility;
import HR.Human;
import Rooms.*;

public class RoomFactory extends AbstractFactory{

	@Override
	public Human getStaff(String staffType, ED ed) {
		return null;
	}
	@Override
	public Human getStaff(String staffType, ED ed, String name, String surname, String state) {
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
		if(roomType.equalsIgnoreCase("WAITINGROOM")){
			return new WaitingRoom(ed);
		}
		else if(roomType.equalsIgnoreCase("BOXROOM")){
			return new BoxRoom(ed);
		}
		else if(roomType.equalsIgnoreCase("SHOCKROOM")){
			return new ShockRoom(ed);
		}
		else if(roomType.equalsIgnoreCase("RADIOROOM")){
			return new RadioRoom(ed);
		}
		else if(roomType.equalsIgnoreCase("MRIROOM")){
			return new MRIRoom(ed);
		}
		else if(roomType.equalsIgnoreCase("BLOODROOM")){
			return new BloodRoom(ed);
		}
		else if(roomType.equalsIgnoreCase("CORRIDOR")){
			return new Corridor(ed);
		}
		return null;
	}
	@Override
	public Room getRoom(String roomType, ED ed, String name) {
		if(roomType.equalsIgnoreCase("WAITINGROOM")){
			return new WaitingRoom(ed, name);
		}
		else if(roomType.equalsIgnoreCase("BOXROOM")){
			return new BoxRoom(ed, name);
		}
		else if(roomType.equalsIgnoreCase("SHOCKROOM")){
			return new ShockRoom(ed, name);
		}
		else if(roomType.equalsIgnoreCase("RADIOROOM")){
			return new RadioRoom(ed, name);
		}
		else if(roomType.equalsIgnoreCase("MRIROOM")){
			return new MRIRoom(ed, name);
		}
		else if(roomType.equalsIgnoreCase("BLOODROOM")){
			return new BloodRoom(ed, name);
		}
		else if(roomType.equalsIgnoreCase("CORRIDOR")){
			return new Corridor(ed, name);
		}
		return null;
	}
	
	
	@Override
	public Facility getFacility(String facilityType, ED ed) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Facility getFacility(String facilityType, ED ed, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
