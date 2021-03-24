package com.games.webapp.modelo.pojo;

import java.util.ArrayList;


import org.hibernate.validator.constraints.NotEmpty;


/**
 * Clase para generar instancias de categorias
 * id guarda la ID de la categoria
 * name guarda el nombre
 * games guarda un arraylist de juegos pertenecientes a la categoria
 * @see Game
 */
public class Category {
	
	private int id;
	
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	
	private ArrayList<Game> games;
	
	
	public Category() {
		super();
		this.id = 0;
		this.name = "";
		this.games = new ArrayList<Game>();
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
	
	
	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}


	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
}
