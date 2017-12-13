package Factory;

import Emergency.ED;
import Events.*;
import Facilities.*;
import HR.Human;
import Rooms.Room;

public class FacilityFactory extends AbstractFactory {

	@Override
	public Human getStaff(ED ed, String staffType) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Human getStaff(ED ed, String staffType, String name, String surname, String state) {
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
	public Room getRoom(ED ed, String roomType) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Room getRoom(ED ed, String roomType, String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Room getTestRoom(ED ed, String roomType, String distribution, float[] distParam) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Room getTestRoom(ED ed, String name, String roomType, String distribution, float[] distParam) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Return the good facility :
	 * <li>"Stretcher" return Stretcher</li>
	 * <li>"other" return null</li>
	 * @param facilityType
	 * @param ed
	 * @return {@link Stretcher} or null
	 * @see Stretcher#Stretcher(ED)
	 */
	@Override
	public Facility getFacility(ED ed, String facilityType) {
		if(facilityType.equalsIgnoreCase("STRETCHER")){
			return new Stretcher(ed);
		}
		else { 
			System.out.println("Unable to create new facility unit --> facilityType unknown");
		}
		return null;
	}
	/**
	 * Return the good facility :
	 * <li>"Stretcher" return Stretcher</li>
	 * <li>"other" return null</li>
	 * @param facilityType
	 * @param ed
	 * @return {@link Stretcher} or null
	 * @see Stretcher#Stretcher(ED, String))
	 */
	@Override
	public Facility getFacility(ED ed, String facilityType, String name) {
		if(facilityType.equalsIgnoreCase("STRETCHER")){
			return new Stretcher(ed, name);
		}
		else { 
			System.out.println("Unable to create new facility unit --> facilityType unknown");
		}
		return null;
	}
	
}
