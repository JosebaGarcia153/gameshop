package com.games.webapp.modelo.pojo;


/**
 * Clase para generar instancias de rols
 * id guarda el ID del rol, si el ID es 1 es un usuario normal, si es 2 es un administrador
 * name guarda el nombre del rol
 * @see Rol
 *
 */
public class Rol {
	
	public static final int ADMIN = 2;
	public static final int USER = 1;
	
	private int id;
	private String name;
	
	public Rol() {
		super();
		this.id = 1;
		this.name = "";
	}
	
	public Rol( int id ) {
		this();
		this.id = id;		
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
		return "Rol [id=" + id + ", name=" + name + "]";
	}
}