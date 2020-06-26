package com.games.webapp.modelo.dao;

import com.games.webapp.modelo.pojo.User;

public interface UserDAO {
	
	//Devuelve null si no encuentra el usuario
	User exists (String name, String password);
}
