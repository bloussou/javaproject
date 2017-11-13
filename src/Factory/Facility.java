package Factory;

public abstract class Facility {

	private static int compteurFacilityId;
	private String edName;
	private int id;
	private String name;
	private String state;
	
	abstract void produce();
}
