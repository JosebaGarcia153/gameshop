package com.games.webapp.modelo.dao.impl;

import java.sql.CallableStatement;
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
	
//	private final String SQL_GET_ALL = "SELECT id, name FROM categories ORDER BY name ASC LIMIT 500; ";
//	private final String SQL_GET_ALL_WITH_GAMES = "SELECT c.id 'category_id', c.name 'category_name', g.id 'game_id', g.name 'game_name', price FROM games g, categories c WHERE g.category_id = c.id ORDER BY c.name ASC LIMIT 500; ";
//	
//	private final String SQL_READ_BY_ID = "SELECT id, name FROM categories WHERE id = ? LIMIT 500; ";
//
//	private final String SQL_CREATE = "INSERT INTO categories (name) VALUES (?); ";
//	private final String SQL_UPDATE = "UPDATE categories SET name = ? WHERE id = ?; ";
//	private final String SQL_DELETE = "DELETE FROM categories WHERE id = ?; ";
	
	private final String PA_GET_ALL = " { CALL pa_category_list() }  ";
	private final String SQL_GET_ALL_WITH_GAMES = " SELECT c.id 'category_id', c.name 'category_name', g.id 'game_id', g.name 'game_name', image, price FROM games g, categories c WHERE g.category_id = c.id AND g.approval_date IS NOT NULL ORDER BY c.name ASC LIMIT 500; ";
	
	private final String PA_GET_BY_ID = " { CALL pa_category_by_id(?) } ";
	
	private final String PA_INSERT = " { CALL pa_category_insert(?,?) } ";
	private final String PA_UPDATE = " { CALL pa_category_update(?,?) } ";	
	private final String PA_DELETE = " { CALL pa_category_delete(?) } ";
	
	@Override
	public ArrayList<Category> getAll() {
		
		ArrayList<Category> register = new ArrayList<Category>();

		try(
			Connection connection = ConnectionManager.getConnection();
			//PreparedStatement pst = connection.prepareStatement(SQL_GET_ALL);
			CallableStatement cs = connection.prepareCall(PA_GET_ALL);
			ResultSet rs = cs.executeQuery();
			){
			
			LOG.debug(cs);
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
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_GET_ALL_WITH_GAMES);
			ResultSet rs = pst.executeQuery();
			){
			
			LOG.debug(pst);
			while( rs.next() ) {
					
				int categoryId = rs.getInt("category_id"); //hashmap key
				
				Category category = register.get(categoryId);
				
				//Si la categoria no existe, crea una nueva y guarda los datos
				if (category == null) {
					
					category = new Category();
					category.setId(categoryId);
					category.setName(rs.getString("category_name"));
				}
				
				//Crea nuevo juego y guarda los datos
				Game game = new Game();
				game.setId(rs.getInt("game_id"));
				game.setName(rs.getString("game_name"));
				game.setPrice(rs.getFloat("price"));
				
				//Agrega juego a la categoria
				//Value del hashmap
				category.getGames().add(game);
				
				//Guarda la categoria en el hashmap
				register.put(categoryId, category);					
			}
				
		} catch (Exception e) {
			
			LOG.error(e);
		}
		//Devuelve un arraylist de categorias, cada una con su lista de juegos
		return new ArrayList<Category>(register.values());
	}
	
	@Override
	public Category getById(int id) throws Exception {
		
		Category register = new Category();
		
		try (
			Connection connection = ConnectionManager.getConnection();
			//PreparedStatement pst = connection.prepareStatement(SQL_READ_BY_ID);
			CallableStatement cs = connection.prepareCall(PA_GET_BY_ID);
			){

			cs.setInt(1, id);
			
			LOG.debug(cs);
			try ( ResultSet rs = cs.executeQuery() ){
				
				LOG.debug(cs);
				if (rs.next()) {
					register = mapper(rs);
				} else {
					throw new Exception ("Couldn't find ID: " + id);
				}
			}	
		}
		return register;
	}

	
	@Override
	public Category create(Category category) throws Exception {
		
		if (category.getName() == null) {

			throw new Exception("You must insert a name");
		}
		
		try ( 
			Connection connection = ConnectionManager.getConnection();
			//PreparedStatement pst = connection.prepareStatement(SQL_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
			CallableStatement cs = connection.prepareCall(PA_INSERT);	
			){
			
			// IN pNombre VARCHAR(100)
			cs.setString(1, category.getName());
			// OUT pIdGenerado INT
			cs.registerOutParameter(2, java.sql.Types.INTEGER);

			LOG.debug(cs);
			
			cs.execute();
			
			// recojo el parametro de salida, despues de ejecutar el PA
			int id = cs.getInt(2); 
			
			category.setId(id);

			
		} catch (SQLException e) {
			LOG.error(e);
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
			//PreparedStatement pst = connection.prepareStatement(SQL_UPDATE);
			CallableStatement cs = connection.prepareCall(PA_UPDATE);	
			){
			
			cs.setString(1,category.getName());				
			cs.setInt(2, category.getId());
			
			LOG.debug(cs);
			int affectedRows = cs.executeUpdate();
			
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
		
		Category category = null;
		
		try (	
			Connection connection = ConnectionManager.getConnection();
			//PreparedStatement pst = connection.prepareStatement(SQL_DELETE);
				CallableStatement cs = connection.prepareCall(PA_DELETE);
			){
			
			category = getById(id);
			
			cs.setInt(1, id);
			
			LOG.debug(cs);
			try {
			cs.executeUpdate();
			
			} catch (SQLException e) {	
				LOG.error(e);
				throw new Exception("Couldn't delete the game with ID: " + id);
			}
		}
		return category;
	}
	
	
	private Category mapper(ResultSet rs) throws SQLException {
		
		Category c = new Category();
		
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		
		return c;
	}
}
