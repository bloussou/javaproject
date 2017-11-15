package Factory;

public class FactoryCreator {

	public static AbstractFactory getFactory(String choice){ 
		if(choice.equalsIgnoreCase("HUMAN")){
			System.out.println("création Human Factory");
			return new PeopleFactory(); 
		}
		else if(choice.equalsIgnoreCase("ROOM")){
			System.out.println("création Room Factory");
			return new RoomFactory(); 
		}
		else if(choice.equalsIgnoreCase("FACILITY")){
			System.out.println("création Facility Factory");
			return new FacilityFactory(); 
		}
		return null; 
		
	} 
		
}
