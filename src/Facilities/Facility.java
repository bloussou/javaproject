package Facilities;

import Emergency.ED;

public abstract class Facility {

	protected ED ed;
	private int id;
	private String name;
	protected String state;
	private String location;
	
	
	
	public ED getEd() {
		return ed;
	}
	public void setEd(ED ed) {
		this.ed = ed;
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

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	
	@Override
	public String toString() {
		return "Facility [ed=" + ed + ", id=" + id + ", name=" + name + ", state=" + state + ", location="
				+ location + "]";
	}
	
	
	abstract public void produce();
}
