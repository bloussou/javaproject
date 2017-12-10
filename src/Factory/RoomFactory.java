package Factory;

import Emergency.ED;
import Events.*;
import Facilities.Facility;
import HR.Human;
import Rooms.*;

public class RoomFactory extends AbstractFactory{

	@Override
	public Human getStaff(ED ed, String staffType) {
		return null;
	}
	@Override
	public Human getStaff(ED ed, String staffType, String name, String surname, String state) {
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
	@Override
	public Room getRoom(ED ed, String roomType) {
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
		else{
			System.out.println("Problème dans Room Factory : le type de room a créer n'est pas bien valide");
			return null;
		}
		
	}
	
	
	@Override
	public Room getRoom(ED ed, String roomType, String name) {
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
