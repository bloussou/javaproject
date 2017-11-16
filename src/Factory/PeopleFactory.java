package Factory;

import Emergency.ED;
import Events.*;
import Facilities.Facility;
import HR.*;
import Rooms.Room;

public class PeopleFactory extends AbstractFactory{
	
	@Override
	public Human getStaff(String staffType, ED ed) {
		if(staffType.equalsIgnoreCase("NURSE")){
			return new Nurse(ed);
		}
		else if(staffType.equalsIgnoreCase("PHYSICIAN")){
			return new Physician(ed);
		}
		else if(staffType.equalsIgnoreCase("TRANSPORTER")){
			return new Transporter(ed);
		}
		return null;
	}
	@Override
	public Human getStaff(String staffType, ED ed, String name, String surname, String state) {
		if(staffType.equalsIgnoreCase("NURSE")){
			return new Nurse(ed, name, surname, state);
		}
		else if(staffType.equalsIgnoreCase("PHYSICIAN")){
			return new Physician(ed, name, surname, state);
		}
		else if(staffType.equalsIgnoreCase("TRANSPORTER")){
			return new Transporter(ed, name, surname, state);
		}
		return null;
	}

	@Override
	public Human getPatient(ED ed, String severityLevel, TimeStamp arrivalTime) {
		return new Patient(ed, severityLevel, arrivalTime);
	}
	@Override
	public Human getPatient(ED ed, String name, String surname, String state, String healthInsurance, String severityLevel, TimeStamp arrivalTime) {
		return new Patient(ed, name, surname, state, healthInsurance, severityLevel, arrivalTime);
	}
	
	
	@Override
	public Room getRoom(String roomType, ED ed) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Room getRoom(String roomType, ED ed, String name){
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
