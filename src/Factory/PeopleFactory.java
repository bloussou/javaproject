package Factory;

import Events.Time;

public class PeopleFactory extends AbstractFactory{
	
	@Override
	public Human getStaff(String staffType, String edName) {
		if(staffType.equalsIgnoreCase("NURSE")){
			return new HR.Nurse(edName);
		}
		else if(staffType.equalsIgnoreCase("PHYSICIAN")){
			return new HR.Physician(edName);
		}
		else if(staffType.equalsIgnoreCase("TRANSPORTER")){
			return new HR.Transporter(edName);
		}
		return null;
	}
	@Override
	public Human getStaff(String staffType, String edName, String name, String surname, String state) {
		if(staffType.equalsIgnoreCase("NURSE")){
			return new HR.Nurse(edName, name, surname, state);
		}
		else if(staffType.equalsIgnoreCase("PHYSICIAN")){
			return new HR.Physician(edName, name, surname, state);
		}
		else if(staffType.equalsIgnoreCase("TRANSPORTER")){
			return new HR.Transporter(edName, name, surname, state);
		}
		return null;
	}

	@Override
	public Human getPatient(String edName, String severityLevel, Time arrivalTime) {
		return new HR.Patient(edName, severityLevel, arrivalTime);
	}
	@Override
	public Human getPatient(String edName, String name, String surname, String state, String healthInsurance, String severityLevel, Time arrivalTime) {
		return new HR.Patient(edName, name, surname, state, healthInsurance, severityLevel, arrivalTime);
	}
	
	
	@Override
	public Room getRoom(String roomType, String edName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Room getRoom(String roomType, String edName, String name){
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
