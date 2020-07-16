package com.games.webapp.modelo.pojo;

public class User {
	
	private int id;
	private String name;
	private String password;
	private String image;
	private Rol rol;
	
	
	public User() {
		super();
		this.id = 0;
		this.name = "";
		this.password = "";
		this.image = "";
		this.rol = new Rol();
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
	
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", image=" + image + "rol=" + rol + "]";
	}
}
