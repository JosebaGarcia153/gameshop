package modelo.dao;

import model.pojo.User;

public interface UserDAO {
	
	//Devuelve null si no encuentra el usuario
	User exists (String name, String password);
}
