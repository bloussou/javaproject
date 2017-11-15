package Factory;

public class FactoryCreator {

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
