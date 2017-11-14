package Facilities;

public class Stretcher extends Facility{

	private static int compteurStretcherId;
	
	public Stretcher(String edName, String name){
		super();
		
		Stretcher.compteurStretcherId += 1;
		this.setId(Stretcher.compteurStretcherId);
		
		this.setEdName(edName);
		this.setName(name);
	}
	public Stretcher(String edName){
		super();
		
		Stretcher.compteurStretcherId += 1;
		this.setId(Stretcher.compteurStretcherId);
		
		this.setEdName(edName);
		this.setName("Stretcher" + Integer.toString(Stretcher.compteurStretcherId));

	}
	
	
	public static int getCompteurStretcherId() {
		return compteurStretcherId;
	}


	public static void setCompteurStretcherId(int compteurStretcherId) {
		Stretcher.compteurStretcherId = compteurStretcherId;
	}
	


	@Override
	public void produce() {
		// TODO Auto-generated method stub
		
	}

	
}
