package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.ConnectionManager;
import modelo.dao.CategoryDAO;
import modelo.pojo.Category;

public class CategoryDAOImpl implements CategoryDAO{
	
	private static CategoryDAOImpl INSTANCE = null;
	
	private CategoryDAOImpl() {
		super();
	}
	
	public static synchronized CategoryDAOImpl getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new CategoryDAOImpl();
		}
		
		return INSTANCE;
	}
	
	private final String SQL_GET_ALL = "SELECT id, name FROM categories ORDER BY name ASC LIMIT 500; ";
	
	@Override
	public ArrayList<Category> getAll() {
		
		ArrayList<Category> register = new ArrayList<Category>();

		try(
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();
				){
				
				while( rs.next() ) {
					
					register.add(mapper(rs));					
				}
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return register;
		
	}
	
	//TODO impletmentar metodos cuando sean necesarios
	
	@Override
	public Category getById(int id) throws Exception {
		throw new Exception("Not implemented");
	}

	@Override
	public Category create(Category pojo) throws Exception {
		throw new Exception("Not implemented");
	}

	@Override
	public Category update(Category pojo) throws Exception {
		throw new Exception("Not implemented");
	}

	@Override
	public Category delete(int id) throws Exception {
		throw new Exception("Not implemented");
	}
	
	private Category mapper(ResultSet rs) throws SQLException {
		
		Category c = new Category();
		
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		
		return c;
	}
}
