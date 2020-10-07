package com.games.webapp.modelo.pojo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Clase para generar instancias de usuarios
 * id guarda la ID del juego
 * name guarda el nombre
 * price guarda el precio
 * image guarda la imagen de portada
 * user guarda el una instancia del usuario que lo ha subido
 * category guarda una instancia de la categoria a la que pertenece 
 * approvalDate guarda la fecha en la que un administrador lo a aprobado
 * @see Usuario
 * @see Category
 */
public class Game {
	
	private int id;
	
	@NotNull(message = "Name cannot be empty")
	@Size(min = 3, max = 100, message = "The name must have between 3 and 100 characters")
	private String name;
	
	@NotNull(message = "Price cannot be empty")
	@Min(value = 1, message = "The price must be 1 &euro; or higher")
	private Float price;
	
	@NotBlank(message = "image URL cannot be empty")
	private String image;
	
	private Usuario user;
	
	private Category category;
	
	private String approvalDate;


	public Game() {
		super();
		this.id = 0;
		this.name = "";
		this.price = 0f;
		this.image = "https://picsum.photos/100/100";
		this.user = new Usuario();
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
	
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
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
		return "Game [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", image=" + image + "]";
	}
}
