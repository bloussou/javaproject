package Factory;

import Emergency.ED;
import Events.*;
import Facilities.Facility;
import Facilities.Stretcher;
import HR.Human;
import HR.Nurse;
import HR.Patient;
import HR.Physician;
import HR.Transporter;
import Rooms.BloodRoom;
import Rooms.BoxRoom;
import Rooms.Corridor;
import Rooms.MRIRoom;
import Rooms.RadioRoom;
import Rooms.Room;
import Rooms.ShockRoom;
import Rooms.WaitingRoom;

public abstract class AbstractFactory {
	
	/**
	 * Takes a string :
	 * <li>"Nurse" to return a Nurse</li>
	 * <li>"Physician" to return a Physician</li>
	 * <li>"Transporter" to return a Transporter</li>
	 * <li>other to return null</li>
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
	abstract public Human getStaff(ED ed,String staffType); 
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
	abstract public Human getStaff(ED ed, String staffType, String name, String surname, String state);
	/**
	 * Return a patient with this parameters
	 * @param ed
	 * @param severityLevel
	 * @param arrivalTime
	 * @return {@link Patient}
	 * @see Patient#Patient(ED, String, TimeStamp)
	 */
	abstract public Human getPatient(ED ed, String severityLevel, TimeStamp arrivalTime);
	/**
	 * Return a patient with this parameters
	 * @param ed
	 * @param severityLevel
	 * @param arrivalTime
	 * @return {@link Patient}
	 * @see Patient#Patient(ED, String, String, String, String, String, TimeStamp)
	 */
	abstract public Human getPatient(ED ed, String name, String surname, String state, String healthInsurance, String severityLevel, TimeStamp arrivalTime);
	/**
	 * Return the good room chosen with the string :
	 * <li>"BoxRoom" return BoxRoom</li>
	 * <li>"ShockRoom" return ShockRoom</li>
	 * <li>"WaitingRoom" return WaitingRoom</li>
	 * <li>"BloodRoom" return BloodRoom</li>
	 * <li>"MRIRoom" return MRIRoom</li>
	 * <li>"RadioRoom" return RadioRoom</li>
	 * <li>"Corridor" return Corridor</li>
	 * <li>"other" return null</li>
	 * @param roomType
	 * @param ed
	 * @return {@link BoxRoom} or {@link ShockRoom} or {@link WaitingRoom} or {@link BloodRoom} or {@link MRIRoom} or {@link RadioRoom} or {@link Corridor}
	 * @see BoxRoom#BoxRoom(ED)
	 * @see ShockRoom#ShockRoom(ED)
	 * @see WaitingRoom#WaitingRoom(ED)
	 * @see BloodRoom#BloodRoom(ED)
	 * @see MRIRoom#MRIRoom(ED)
	 * @see RadioRoom#RadioRoom(ED)
	 * 
	 */
	abstract public Room getRoom(ED ed, String roomType);
	/**
	 * Return the good room chosen with the string :
	 * <li>"BoxRoom" return BoxRoom</li>
	 * <li>"ShockRoom" return ShockRoom</li>
	 * <li>"WaitingRoom" return WaitingRoom</li>
	 * <li>"BloodRoom" return BloodRoom</li>
	 * <li>"MRIRoom" return MRIRoom</li>
	 * <li>"RadioRoom" return RadioRoom</li>
	 * <li>"Corridor" return Corridor</li>
	 * <li>"other" return null</li>
	 * @param roomType
	 * @param ed
	 * @param name
	 * @return {@link BoxRoom} or {@link ShockRoom} or {@link WaitingRoom} or {@link BloodRoom} or {@link MRIRoom} or {@link RadioRoom} or {@link Corridor}
	 * @see BoxRoom#BoxRoom(ED, String))
	 * @see ShockRoom#ShockRoom(ED, String))
	 * @see WaitingRoom#WaitingRoom(ED, String))
	 * @see BloodRoom#BloodRoom(ED, String))
	 * @see MRIRoom#MRIRoom(ED, String))
	 * @see RadioRoom#RadioRoom(ED, String))
	 */
	abstract public Room getRoom(ED ed, String roomType, String name);
	
	/**
	 * return the good testRoom with a chosen distribution of test duration
	 * @param ed
	 * @param roomType
	 * @param distribution
	 * @param distParam
	 * @return
	 */
	abstract public Room getTestRoom(ED ed, String roomType, String distribution, float[] distParam);
	/**
	 * return the good testRoom with a chosen distribution of test duration
	 * @param ed
	 * @param roomType
	 * @param distribution
	 * @param distParam
	 * @return
	 */
	abstract public Room getTestRoom(ED ed, String name, String roomType, String distribution, float[] distParam);
	
	
	/**
	 * Return the good facility :
	 * <li>"Stretcher" return Stretcher</li>
	 * <li>"other" return null</li>
	 * @param facilityType
	 * @param ed
	 * @return {@link Stretcher} or null
	 * @see Stretcher#Stretcher(ED)
	 */
	abstract public Facility getFacility (ED ed,String facilityType);
	/**
	 * Return the good facility :
	 * <li>"Stretcher" return Stretcher</li>
	 * <li>"other" return null</li>
	 * @param facilityType
	 * @param ed
	 * @return {@link Stretcher} or null
	 * @see Stretcher#Stretcher(ED, String))
	 */
	abstract public Facility getFacility(ED ed, String facilityType, String name);
	

}
