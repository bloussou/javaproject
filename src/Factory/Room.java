package Factory;

import HR.Patient;

public abstract class Room {

	private String edName;
	private int id;
	private String name;
	private Distribution dist;
	private float cost;
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
	public Distribution getDist() {
		return dist;
	}
	public void setDist(Distribution dist) {
		this.dist = dist;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	abstract public void addOccupant(Patient patient);
	abstract public void removeOccupant(Patient patient);
	abstract public void construct();
	abstract public void updatePatientCharge();
		
		
}
