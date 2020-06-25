package modelo.pojo;

import java.util.ArrayList;

public class Category {
	
	private int id;
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
