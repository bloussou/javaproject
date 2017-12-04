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
			System.out.println("cr�ation Human Factory");
			return new PeopleFactory(); 
		}
		else if(choice.equalsIgnoreCase("ROOM")){
			System.out.println("cr�ation Room Factory");
			return new RoomFactory(); 
		}
		else if(choice.equalsIgnoreCase("FACILITY")){
			System.out.println("cr�ation Facility Factory");
			return new FacilityFactory(); 
		}
		return null; 
		
	} 
		
}
