package com.games.webapp.modelo.pojo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Game {
	
	private int id;
	
	@NotNull(message = "Name cannot be null")
	@Size(min = 3, max = 100, message = "The name must have between 3 and 100 characters")
	private String name;
	
	@NotNull(message = "Price cannot be null")
	@Min(value = 1, message = "The price must be 1 &euro; or higher")
	private Double price;
	
	private Category category;
	
	
	public Game() {
		super();
		this.id = 0;
		this.name = "";
		this.price = 0.0;
		this.category = new Category();
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


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category cathegory) {
		this.category = cathegory;
	}


	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", price=" + price + ", cathegory=" + category + "]";
	}
}
