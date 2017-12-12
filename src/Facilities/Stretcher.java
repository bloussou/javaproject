package Facilities;

import java.util.ArrayList;

import Emergency.ED;

public class Stretcher extends Facility{
	/**
	 * A int to give a unique id to each stretcher
	 */
	private static int compteurStretcherId;
	
	
	/**
	 * Build a stretcher and set its state to "free"
	 * @param ed
	 * @param name
	 * @see Stretcher#setState(String)
	 */
	public Stretcher(ED ed, String name){
		super();
		
		Stretcher.compteurStretcherId += 1;
		this.setId(Stretcher.compteurStretcherId);
		
		this.setEd(ed);
		this.setName(name);
		
		this.setState("free");
	}
	/**
	 * Build a stretcher and set its state to "free"
	 * @param ed
	 * @see Stretcher#setState(String)
	 */
	public Stretcher(ED ed){
		super();
		
		Stretcher.compteurStretcherId += 1;
		this.setId(Stretcher.compteurStretcherId);
		
		this.setEd(ed);
		this.setName("Stretcher" + Integer.toString(Stretcher.compteurStretcherId));
		
		this.setState("free");

	}
	


	/**
	 * write the following string on the console : 
	 * "Stretcher n°"+ this.getId() +" produce"
	 */
	@Override
	public void produce() {
		// TODO Auto-generated method stub
		System.out.println("Stretcher n°"+ this.getId() +" produce");
	}
	
	
	/**
	 * Set the state of the stretcher and put it in the good {@link ED#getDbStretcher()}
	 * <li>state1 : free</li>
	 * <li>state2 : occupied</li>
	 * @see ED#getDbStretcher()
	 * @see Facility#state
	 */
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
	/**
	 * 
	 * @return {@link Stretcher#compteurStretcherId}
	 */
	public static int getCompteurStretcherId() {
		return compteurStretcherId;
	}


}
