package model.pojo;

public class Cathegory {
	
	private int id;
	private String name;

	
	public Cathegory() {
		super();
		this.id = 0;
		this.name = "";
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
	
	
	@Override
	public String toString() {
		return "Cathegory [id=" + id + ", name=" + name + "]";
	}
}
