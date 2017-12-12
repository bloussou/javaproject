package Rooms;

import java.util.Arrays;

import Emergency.ED;
import HR.Human;
import HR.Patient;

public abstract class Room {

	/**
	 * The ED of the Room
	 * @see Room#getEd()
	 * @see Room#setEd(ED)
	 * @see ED#ED(String, String)
	 */
	protected ED ed;
	/**
	 * The state of the Room
	 * 
	 * @see Room#setState(String)
	 * @see Room#getState()
	 */
	protected String state;
	/**
	 * The unique id of a room
	 * @see Room#getId()
	 * @see Room#setId(int)
	 */
	private int id;
	/**
	 * The name of the Room
	 * @see Room#getName()
	 * @see Room#setName(String)
	 */
	private String name;
	/**
	 * The distribution of the Room
	 * @see Room#getName()
	 * @see Room#setName(String)
	 * @see Proba#Exp#RandSample(double)
	 * @see Proba#Gamma#RandSample(double,double)
	 * @see Proba#LogNorm#RandSample(double,double)
	 * @see Proba#Uniform#RandSample(double,double)
	 */
	private String dist;
	/**
	 * The cost of the Room when a patient enters
	 * @see Room#getCost()
	 * @see Room#setCost(String)
	 */
	private float cost;
	/**
	 * The distribution parameters of the Room
	 * @see Room#getDistParam()
	 * @see Room#setDistParam(String)
	 * @see Proba#Exp#RandSample(double)
	 * @see Proba#Gamma#RandSample(double,double)
	 * @see Proba#LogNorm#RandSample(double,double)
	 * @see Proba#Uniform#RandSample(double,double)
	 */
	private float[] distParam = {0,0};
	/**
	 * The capacity of the Room
	 * @see Room#getCapacity()
	 * @see Room#setCapacity(String)
	 */
	private int capacity;
	
	
	
	/**
	 * 
	 * @return {@link Room#ed}
	 */
	public ED getEd() {
		return ed;
	}
	/**
	 * Set the ed of the Room
	 * @param ed
	 */
	public void setEd(ED ed) {
		this.ed = ed;
	}
	
	/**
	 * 
	 * @return {@link Room#state}
	 */
	public String getState() {
		return state;
	}
	/**
	 * Set the state of the Room
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * 
	 * @return {@link Room#id}
	 */
	public int getId() {
		return id;
	}
	/**
	 * Set the id of the Room
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return {@link Room#name}
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the name of the Room
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return {@link Room#dist}
	 */
	public String getDist() {
		return dist;
	}
	/**
	 * Set the dist of the Room
	 * @param dist
	 */
	public void setDist(String dist) {
		if( dist.equalsIgnoreCase("UNIFORM") || dist.equalsIgnoreCase("UNIFORM") || dist.equalsIgnoreCase("UNIFORM") || dist.equalsIgnoreCase("UNIFORM")){
		this.dist = dist;
		}
		else{
			System.out.println("Nom de distribution non reconnue");
		}
	}
	
	/**
	 * 
	 * @return {@link Room#cost}
	 */
	public float getCost() {
		return cost;
	}
	/**
	 * Set the cost of the Room
	 * @param cost
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	/**
	 * 
	 * @return {@link Room#capacity}
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * Set the capacity of the Room
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * 
	 * @return {@link Room#distParam}
	 */
	public float[] getDistParam() {
		return distParam;
	}
	/**
	 * Set the parameters of the Room distribution
	 * @param distParam
	 */
	public void setDistParam(float[] distParam) {
		if(distParam.length == 2){
			this.distParam = distParam;
		}
		else {
			System.out.println("Expected a lenght 2 for distParam array, (even when only one arg is necessary the second one should be 0");
		}
	}
	
	/**
	 * Return a description of the Room like :
	 * 		return "Room [ed=" + ed + ", id=" + id + ", name=" + name + ", dist=" + dist + ", cost=" + cost
				+ ", distParam=" + Arrays.toString(distParam) + ", capacity=" + capacity + "]";
	 * @return String
	 * 
	 */
	@Override
	public String toString() {
		return "Room [ed=" + ed + ", id=" + id + ", name=" + name + ", dist=" + dist + ", cost=" + cost
				+ ", distParam=" + Arrays.toString(distParam) + ", capacity=" + capacity + "]";
	}
	
	
	
	/**
	 * A method to add a new patient in the Room :
	 * <li>adds the patient to this.occupants</li>
	 * <li>updates the state of the Room</li>
	 * @param patient
	 */
	abstract public void addOccupant(Patient patient);
	
	/**
	 * A method to remove a new patient from the Room :
	 * <li>removes the patient from this.occupants</li>
	 * <li>updates the state of the Room</li>
	 * @param patient
	 */
	abstract public void removeOccupant(Patient patient);
	
	abstract public void construct();
	
	/**
	 * A method to update the total charge of a patient currently in this Room
	 * @param patient
	 */
	abstract public void updatePatientCharge(Patient patient);
		
		
}
