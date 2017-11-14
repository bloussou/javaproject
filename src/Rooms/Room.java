package Rooms;

import HR.Patient;

public abstract class Room {

	private String edName;
	private int id;
	private String name;
	private String dist;
	private float cost;
	private float[] distParam = {0,0};
	private int capacity;
	
	

	public String getEdName() {
		return edName;
	}
	public void setEdName(String edName) {
		this.edName = edName;
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
	
	
	abstract public void addOccupant(Patient patient);
	abstract public void removeOccupant(Patient patient);
	abstract public void construct();
	abstract public void updatePatientCharge();
		
		
}
