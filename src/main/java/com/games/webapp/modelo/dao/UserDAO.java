package com.games.webapp.modelo.dao;

import com.games.webapp.modelo.pojo.Usuario;

public interface UserDAO {
	
	//Devuelve null si no encuentra el usuario
	Usuario exists (String name, String password);

	Usuario create(Usuario usuario) throws Exception;
	
	boolean searchByName(String name);
}
