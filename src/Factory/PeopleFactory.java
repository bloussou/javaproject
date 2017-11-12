package Factory;

public class PeopleFactory extends AbstractFactory{
	
	@Override
	public Human getHuman(String edName, String humanType) {
		if(humanType.equalsIgnoreCase("NURSE")){
			return new HR.Nurse(edName);
		}
		return null;
	}

	@Override
	public Room getRoom(String edName, String roomType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Facility getFacility(String edName, String facilityType) {
		// TODO Auto-generated method stub
		return null;
	}

}
