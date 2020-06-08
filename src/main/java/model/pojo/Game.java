package model.pojo;

public class Game {
	
	private int id;
	private String name;
	private Double price;
	
	public Game() {
		super();
		this.id = 0;
		this.name = "";
		this.price = 0.0;
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

	public void setName(String nombre) {
		this.name = nombre;
	}
	
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	@Override
	public String toString() {
		return "Games [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}
