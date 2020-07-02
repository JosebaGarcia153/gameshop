package com.games.webapp.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.ConnectionManager;
import com.games.webapp.modelo.dao.CategoryDAO;
import com.games.webapp.modelo.pojo.Category;
import com.games.webapp.modelo.pojo.Game;

public class CategoryDAOImpl implements CategoryDAO{
	
	private static final Logger LOG = Logger.getLogger(CategoryDAOImpl.class);
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
	private final String SQL_GET_ALL_WITH_GAMES = "SELECT c.id 'category_id', c.name 'category_name', g.id 'game_id', g.name 'game_name', price FROM games g, categories c WHERE g.category_id = c.id ORDER BY c.name ASC LIMIT 500; ";
	
	private final String SQL_READ_BY_ID = "SELECT id, name FROM categories WHERE id = ? LIMIT 500; ";

	private final String SQL_CREATE = "INSERT INTO categories (name) VALUES (?); ";
	private final String SQL_UPDATE = "UPDATE categories SET name = ? WHERE id = ?; ";
	private final String SQL_DELETE = "DELETE FROM categories WHERE id = ?; ";
	
	@Override
	public ArrayList<Category> getAll() {
		
		ArrayList<Category> register = new ArrayList<Category>();

		try(
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_GET_ALL);
			ResultSet rs = pst.executeQuery();
			){
			
			LOG.debug(pst);
			while( rs.next() ) {
				
				register.add(mapper(rs));					
			}
				
		} catch (Exception e) {
			
			LOG.error(e);
		}
		
		return register;
	}
	
	
	@Override
	public ArrayList<Category> getAllWithGames() {
		
		//La clave Integer es el ID de la categoria
		HashMap<Integer, Category> register = new HashMap<Integer, Category>();

		try(
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_WITH_GAMES);
			ResultSet rs = pst.executeQuery();
			){
			
			LOG.debug(pst);
			while( rs.next() ) {
					
					int categoryId = rs.getInt("category_id"); //hashmap key
					
					Category c = register.get(categoryId);
					
					//Si la categoria no existe, crea una nueva y guarda los datos
					if (c == null) {
						
						c = new Category();
						c.setId(categoryId);
						c.setName(rs.getString("category_name"));
					}
					
					//Crea nuevo juego y guarda los datos
					Game g = new Game();
					g.setId(rs.getInt("game_id"));
					g.setName(rs.getString("game_name"));
					g.setPrice(rs.getDouble("price"));
					
					//Agrega juego a la categoria
					//Value del hashmap
					c.getGames().add(g);
					
					//Guarda la categoria en el hashmap
					register.put(categoryId, c);					
				}
				
		} catch (Exception e) {
			
			LOG.error(e);
		}
		//Devuelve un arraylist de categorias, cada una con su lista de juegos
		return new ArrayList<Category>(register.values());
	}
	
	@Override
	public Category getById(int id) throws Exception {
		
		Category c = new Category();
		
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_READ_BY_ID);
			){

			pst.setInt(1, id);
			
			try ( ResultSet rs = pst.executeQuery() ){
				
				LOG.debug(pst);
				if (rs.next()) {

					c = mapper(rs);

				} else {

					throw new Exception ("Couldn't find ID: " + id);
				}
			}	
		}
		return c;
	}

	
	@Override
	public Category create(Category category) throws Exception {
		
		if (category.getName() == null) {

			throw new Exception("You must insert a name");
		}
		
		try ( 
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
			){
			
			pst.setString(1, category.getName());
			
			int affectedRows = pst.executeUpdate();
			
			LOG.debug(pst);
			if (affectedRows == 1) {
				
				//conseguir el ID
				try ( ResultSet rsKeys = pst.getGeneratedKeys(); ) {
					
					if (rsKeys.next()) {

						category.setId(rsKeys.getInt(1));
					}
				}
			} else {

				throw new Exception ("Couldn't create a new entry for " + category.getName());
			}
		} catch (SQLException e) {
			
			throw new SQLException("The name already exists");
		}
		
		return category;
	}
	
	
	@Override
	public Category update(Category category) throws Exception {
		
		if (category.getName() == null) {
			
			throw new Exception("You must insert a name.");
		}
		
		try ( 
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_UPDATE);
			){
			
			pst.setString(1, category.getName());
			pst.setInt(2, category.getId());
			
			int affectedRows = pst.executeUpdate();
			
			LOG.debug(pst);
			if (affectedRows != 1) {
				
				throw new Exception ("Couldn't create a new entry for ID: " + category.getId());
			}
			
		} catch (SQLException e) {
			
			LOG.error(e);
			throw new SQLException("The name already exists");

		} 
		return category;
	}

	
	@Override
	public Category delete(int id) throws Exception {
		
		Category c = new Category();
		
		try (	
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_DELETE);
			){
			
			pst.setInt(1, id);
			
			c = getById(id);
			
			int affectedRows = pst.executeUpdate();
			
			LOG.debug(pst);
			if (affectedRows != 1) {
				
				throw new Exception("Couldn't delete the game with ID: " + id);
			}
		}
		return c;
	}
	
	
	private Category mapper(ResultSet rs) throws SQLException {
		
		Category c = new Category();
		
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		
		return c;
	}
}
