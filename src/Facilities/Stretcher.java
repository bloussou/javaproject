package Facilities;

import Emergency.ED;

public class Stretcher extends Facility{

	private static int compteurStretcherId;
	
	public Stretcher(ED ed, String name){
		super();
		
		Stretcher.compteurStretcherId += 1;
		this.setId(Stretcher.compteurStretcherId);
		
		this.setEd(ed);
		this.setName(name);
	}
	public Stretcher(ED ed){
		super();
		
		Stretcher.compteurStretcherId += 1;
		this.setId(Stretcher.compteurStretcherId);
		
		this.setEd(ed);
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
