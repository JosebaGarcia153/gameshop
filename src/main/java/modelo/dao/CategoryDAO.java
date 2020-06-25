package modelo.dao;

import java.util.ArrayList;

import modelo.Crudable;
import modelo.pojo.Category;

public interface CategoryDAO extends Crudable<Category> {
	
	/*
	* Obtiene las categorias con sus productos
	* @return devuelve ArrayList<Category> alfbeticamente
	*/
	public ArrayList<Category> getAllWithProducts();
}
