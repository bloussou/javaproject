package Rooms;

import java.util.Arrays;

import Emergency.ED;
import HR.Patient;

public abstract class Room {

	protected ED ed;
	protected String state;
	private int id;
	private String name;
	private String dist;
	private float cost;
	private float[] distParam = {0,0};
	private int capacity;
	
	

	public ED getEd() {
		return ed;
	}
	public void setEd(ED ed) {
		this.ed = ed;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public float[] getDistParam() {
		return distParam;
	}
	public void setDistParam(float[] distParam) {
		this.distParam = distParam;
	}
	
	@Override
	public String toString() {
		return "Room [ed=" + ed + ", id=" + id + ", name=" + name + ", dist=" + dist + ", cost=" + cost
				+ ", distParam=" + Arrays.toString(distParam) + ", capacity=" + capacity + "]";
	}
	
	
	abstract public void addOccupant(Patient patient);
	abstract public void removeOccupant(Patient patient);
	abstract public void construct();
	abstract public void updatePatientCharge(Patient patient);
		
		
}
