package Factory;

public class FactoryCreator {

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
