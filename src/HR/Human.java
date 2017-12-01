package HR;

import Emergency.ED;

public abstract class Human {

	protected ED ed;
	private int id;
	private String name;
	private String surname;
	protected String state;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public ED getEd() {
		return ed;
	}
	public void setEd(ED ed) {
		this.ed = ed;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "Human [EDname=" + ed.getName() + ", id=" + id + ", name=" + name + ", surname=" + surname + ", state=" + state + "]";
	}


	abstract public void create();
	
}
