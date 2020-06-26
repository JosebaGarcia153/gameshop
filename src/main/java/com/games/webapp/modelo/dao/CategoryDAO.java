package com.games.webapp.modelo.dao;

import java.util.ArrayList;

import com.games.webapp.modelo.Crudable;
import com.games.webapp.modelo.pojo.Category;


public interface CategoryDAO extends Crudable<Category> {
	
	/*
	* Obtiene las categorias con sus productos
	* @return devuelve ArrayList<Category> alfbeticamente
	*/
	public ArrayList<Category> getAllWithGames();
}
