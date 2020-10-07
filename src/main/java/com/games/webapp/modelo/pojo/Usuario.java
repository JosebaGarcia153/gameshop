package com.games.webapp.modelo.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clase para generar instancias de usuarios
 * name guarda el nombre del usuario
 * password guarda la contraseña
 * image guarda la imagen de perfil
 * rol es un objeto usado para indicar si el usuario es un administrador o no
 * birthday guarda la fecha de nacimiento del usuario
 * @see Rol
 *
 */
public class Usuario {
	
	private int id;
	
	@NotNull(message = "Name cannot be empty")
	@Size(min = 5, max = 15, message = "The name must have between 5 and 15 characters")
	private String name;
	
	@NotNull(message = "Password cannot be empty")
	@Size(min = 6, max = 15, message = "The password must have between 6 and 15 characters")
	private String password;
	private String image;
	private Rol rol;
	private String birthday;
	
	
	public Usuario() {
		super();
		this.id = 0;
		this.name = "";
		this.password = "";
		this.image = "";
		this.rol = new Rol();
		this.birthday = "";
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


	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", password=" + password + ", image=" + image + ", rol=" + rol
				+ ", birthday=" + birthday + "]";
	}
}
