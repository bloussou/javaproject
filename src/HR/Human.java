package HR;

import Emergency.ED;

public abstract class Human {
	
	/**
	 * The ED of the human
	 * @see Human#getEd()
	 * @see Human#setEd(ED)
	 * @see ED#ED(String, String)
	 */
	protected ED ed;
	/**
	 * The unique id of a human
	 * @see Human#getId()
	 * @see Human#setId(int)
	 */
	private int id;
	/**
	 * The name of the Human
	 * @see Human#getName()
	 * @see Human#setName(String)
	 */
	private String name;
	/**
	 * The surname of the Human
	 * @see Human#getSurname()
	 * @see Human#setSurname(String)
	 */
	private String surname;
	/**
	 * The state of the Human
	 * 
	 * @see Human#setState(String)
	 * @see Human#getState()
	 */
	protected String state;
	
	
	/**
	 * 
	 * @return {@link Human#id}
	 */
	public int getId() {
		return id;
	}
	/**
	 * Set the id of the Human
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return {@link ED}
	 */
	public ED getEd() {
		return ed;
	}
	/**
	 * Set the ed of the human
	 * @param ed
	 */
	public void setEd(ED ed) {
		this.ed = ed;
	}
	/**
	 * 
	 * @return {@link Human#name}
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the name of the Human
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return {@link Human#surname}
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * Set the surname of the Human
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * 
	 * @return {@link Human#state}
	 */
	public String getState() {
		return state;
	}
	/**
	 * Set the state of the human
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	
	/**
	 * Return a description of the human like :
	 * return "Human [EDname=" + ed.getName() + ", id=" + id + ", name=" + name + ", surname=" + surname + ", state=" + state + "]";
	 * @return String
	 * @see Human#ed
	 * @see Human#id
	 * @see Human#name
	 * @see Human#surname
	 * @see Human#state
	 */
	@Override
	public String toString() {
		return "Human [EDname=" + ed.getName() + ", id=" + id + ", name=" + name + ", surname=" + surname + ", state=" + state + "]";
	}


	abstract public void create();
	
}
