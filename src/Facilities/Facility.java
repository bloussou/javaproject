package Facilities;

import Emergency.ED;

public abstract class Facility {
	
	/**
	 * The {@link ED} of the facility
	 */
	protected ED ed;
	/**
	 * the unique id of the facility
	 */
	private int id;
	/**
	 * The name of the facility
	 */
	private String name;
	/**
	 * the current state of the facility
	 */
	protected String state;
	/**
	 * the location of the facility
	 */
	private String location;
	
	
	
	/**
	 * 
	 * @return {@link Facility#ed}
	 */
	public ED getEd() {
		return ed;
	}
	/**
	 * Set {@link Facility#ed}
	 * @param ed
	 */
	public void setEd(ED ed) {
		this.ed = ed;
	}
	/**
	 * 
	 * @return {@link Facility#id}
	 */
	public int getId() {
		return id;
	}
	/**
	 * Set {@link Facility#id}
	 * @param ed
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return {@link Facility#name}
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set {@link Facility#name}
	 * @param ed
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return {@link Facility#state}
	 */
	public String getState() {
		return state;
	}
	/**
	 * Set {@link Facility#state}
	 * @param ed
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 
	 * @return {@link Facility#location}
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * Set {@link Facility#location}
	 * @param ed
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	
	/**
	 * Return a string like :
	 * <p>"Facility [ed=" + ed + ", id=" + id + ", name=" + name + ", state=" + state + ", location="
				+ location + "]"</p>
	 *@see Facility#ed
	 *@see Facility#id
	 *@see Facility#name
	 *@see Facility#state
	 *@see Facility#location
	 */
	@Override
	public String toString() {
		return "Facility [ed=" + ed + ", id=" + id + ", name=" + name + ", state=" + state + ", location="
				+ location + "]";
	}
	
	
	abstract public void produce();
}
