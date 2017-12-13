package Factory;

public class FactoryCreator {
	
	/**
	 * Take a string to return the good factory :
	 * <li>"Human" : people Factory </li>
	 * <li>"Room" : room Factory </li>
	 * <li>"Facility : Facility Factory</li>
	 * <li>other : return null </li>
	 * @param choice
	 * @return {@link PeopleFactory#PeopleFactory()} {@link RoomFactory#RoomFactory()} {@link FacilityFactory#FacilityFactory()}
	 */
	public static AbstractFactory getFactory(String choice){ 
		if(choice.equalsIgnoreCase("HUMAN")){
			return new PeopleFactory(); 
		}
		else if(choice.equalsIgnoreCase("ROOM")){
			return new RoomFactory(); 
		}
		else if(choice.equalsIgnoreCase("FACILITY")){
			return new FacilityFactory(); 
		}
		return null; 
		
	} 
		
}
