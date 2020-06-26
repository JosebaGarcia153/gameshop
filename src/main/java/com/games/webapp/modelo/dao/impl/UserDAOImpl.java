package com.games.webapp.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.ConnectionManager;
import com.games.webapp.modelo.dao.UserDAO;
import com.games.webapp.modelo.pojo.User;

public class UserDAOImpl implements UserDAO {
	
	private static final Logger LOG = Logger.getLogger(UserDAOImpl.class);
	private static UserDAOImpl instance = null;
	
	private UserDAOImpl() {
		super();	
	}
	
	public static synchronized UserDAOImpl getInstance() {
		
		if (instance == null) {
			
			instance = new UserDAOImpl();
		}	
		return instance;
	}

	private final String SQL_EXISTS = "SELECT id, name, password, image FROM users WHERE name = ? AND password = ? LIMIT 500; ";
	

	@Override
	public User exists (String name, String password) {
		
		User user = null;
		
		try(	
				Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_EXISTS);	
				) {
			
			pst.setString(1, name);
			pst.setString(2, password);
			
			LOG.debug(pst);
			try (ResultSet rs = pst.executeQuery()) {
				
				if (rs.next()) {
					
					user = new User();
					
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setPassword( rs.getString("password"));
					user.setImage( rs.getString("image"));
				}
			} //2nd try
		} catch (Exception e) {
			
			LOG.error(e);
		}
		
		return user;
	}
}