package Factory;

public class FactoryCreator {

	public static AbstractFactory getFactory(String choice){ 
		if(choice.equalsIgnoreCase("HUMAN")){
			return new PeopleFactory(); 
		}
		return null; 
		
	} 
		
}
