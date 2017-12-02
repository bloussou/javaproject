package Facilities;

import java.util.ArrayList;

import Emergency.ED;

public class Stretcher extends Facility{

	private static int compteurStretcherId;
	
	public Stretcher(ED ed, String name){
		super();
		
		Stretcher.compteurStretcherId += 1;
		this.setId(Stretcher.compteurStretcherId);
		
		this.setEd(ed);
		this.setName(name);
		
		this.setState("free");
	}
	public Stretcher(ED ed){
		super();
		
		Stretcher.compteurStretcherId += 1;
		this.setId(Stretcher.compteurStretcherId);
		
		this.setEd(ed);
		this.setName("Stretcher" + Integer.toString(Stretcher.compteurStretcherId));
		
		this.setState("free");

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
		System.out.println("Stretcher n°"+ this.getId() +" produce");
	}
	
	@Override
	public void setState(String state){
		ArrayList<ArrayList<Stretcher>> dbStretcher = this.ed.getDbStretcher();
		
		for (int i = 0; i<dbStretcher.size(); i++){
			dbStretcher.get(i).remove(this);
		}
		//add the stretcher to the state stretcher db
		if (state.equals("free")){
			this.ed.getDbStretcher().get(0).add(this);
			this.state = state;
		}
		else if (state.equals("occupied")){
			this.ed.getDbStretcher().get(1).add(this);
			this.state = state;
		}
		else{
			System.out.println("cet état n'existe pas");
		}
	}

	
}
