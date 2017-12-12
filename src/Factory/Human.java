package Factory;

public abstract class Human {

	private String edName;
	private int id;
	private String name;
	private String surname;
	private String state;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getEdName() {
		return edName;
	}
	public void setEdName(String edName) {
		this.edName = edName;
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
		return "Human [EDname=" + edName + ", id=" + id + ", name=" + name + ", surname=" + surname + ", state=" + state + "]";
	}


	abstract public void create();
	
}
