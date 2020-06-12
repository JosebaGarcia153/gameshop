package modelo.dao;

import model.pojo.User;

public interface UserDAO {
	
	User exists (String name, String password);
}
