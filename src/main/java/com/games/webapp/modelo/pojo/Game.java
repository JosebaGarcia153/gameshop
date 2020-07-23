package com.games.webapp.modelo.pojo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Game {
	
	private int id;
	
	@NotNull(message = "Name cannot be null")
	@Size(min = 3, max = 100, message = "The name must have between 3 and 100 characters")
	private String name;
	
	@NotNull(message = "Price cannot be null")
	@Min(value = 1, message = "The price must be 1 &euro; or higher")
	private Float price;
	
	@NotBlank(message = "image URL cannot be left empty")
	private String image;
	
	private User user;
	
	private Category category;
	
	private String approvalDate;


	public Game() {
		super();
		this.id = 0;
		this.name = "";
		this.price = 0f;
		this.image = "https://picsum.photos/100/100";
		this.user = new User();
		this.category = new Category();
		this.approvalDate = "";
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
	
	
	public Float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category cathegory) {
		this.category = cathegory;
	}
	
	
	public String getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	
	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", price=" + price + ", cathegory=" + category + ", image=" + image + "]";
	}
}
