package Factory;

import Emergency.ED;
import Events.*;
import Facilities.Facility;
import HR.*;
import Rooms.Room;

public class PeopleFactory extends AbstractFactory{
	/**
	 * Takes a string :
	 * <li>"Nurse" to return a Nurse</li>
	 * <li>"Physician" to return a Physician</li>
	 * <li>"Transporter" to return a Transporter</li>
	 * <li>other to return null</li>
	 * 
	 *<p>The default state is idle in this case</p>
	 * @param staffType
	 * @param ed
	 * @return  {@link Nurse} or {@link Transporter} or {@link Physician}
	 * @see Physician#Physician(ED)
	 * @see Nurse#Nurse(ED)
	 * @see Transporter#Transporter(ED)
	 * @see Physician#setState(String)
	 * @see Nurse#setState(String)
	 * @see Transporter#setState(String)
	 */

	@Override
	public Human getStaff(ED ed, String staffType) {
		if(staffType.equalsIgnoreCase("NURSE")){
			return new Nurse(ed);
		}
		else if(staffType.equalsIgnoreCase("PHYSICIAN")){
			return new Physician(ed);
		}
		else if(staffType.equalsIgnoreCase("TRANSPORTER")){
			return new Transporter(ed);
		}
		else {
			System.out.println("Problème dans People Factory : le type de people a créer n'est pas bien valide");
			return null;
		}
	}
	
	/**
	 * Takes a string :
	 * <li>"Nurse" to return a Nurse</li>
	 * <li>"Physician" to return a Physician</li>
	 * <li>"Transporter" to return a Transporter</li>
	 * <li>other to return null</li>
	 * @param staffType
	 * 		the type of the staff
	 * @param ed 
	 * 		the ed of the staff
	 * @param name
	 * 		the name of the staff
	 * @param surname
	 * 		the surname of the staff
	 * @param state
	 * 		the state of the staff
	 * @return  {@link Nurse} or {@link Transporter} or {@link Physician}
	 * @see Physician#Physician(ED)
	 * @see Nurse#Nurse(ED)
	 * @see Transporter#Transporter(ED)
	 * @see Physician#setState(String)
	 * @see Nurse#setState(String)
	 * @see Transporter#setState(String)
	 */
	@Override
	public Human getStaff(ED ed, String staffType, String name, String surname, String state) {
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
	/**
	 * Return a patient with this parameters
	 * @param ed
	 * @param severityLevel
	 * @param arrivalTime
	 * @return {@link Patient}
	 * @see Patient#Patient(ED, String, TimeStamp)
	 */
	@Override
	public Human getPatient(ED ed, String severityLevel, TimeStamp arrivalTime) {
		return new Patient(ed, severityLevel, arrivalTime);
	}
	/**
	 * Return a patient with this parameters
	 * @param ed
	 * @param severityLevel
	 * @param arrivalTime
	 * @return {@link Patient}
	 * @see Patient#Patient(ED, String, String, String, String, String, TimeStamp)
	 */
	@Override
	public Human getPatient(ED ed, String name, String surname, String state, String healthInsurance, String severityLevel, TimeStamp arrivalTime) {
		return new Patient(ed, name, surname, state, healthInsurance, severityLevel, arrivalTime);
	}
	
	
	@Override
	public Room getRoom(ED ed, String roomType) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Room getRoom(ED ed, String roomType, String name){
		return null;
	}
	
	@Override
	public Facility getFacility(ED ed, String facilityType) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Facility getFacility(ED ed, String facilityType, String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
