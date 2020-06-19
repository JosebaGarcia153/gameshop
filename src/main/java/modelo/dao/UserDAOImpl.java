package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.pojo.User;
import modelo.conexion.ConnectionManager;

public class UserDAOImpl implements UserDAO {
	
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
			
			e.printStackTrace();
		}
		
		return user;
	}
}