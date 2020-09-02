package com.games.webapp.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.games.webapp.modelo.ConnectionManager;
import com.games.webapp.modelo.dao.GameDAO;
import com.games.webapp.modelo.dao.SecurityException;
import com.games.webapp.modelo.pojo.Category;
import com.games.webapp.modelo.pojo.Game;
import com.games.webapp.modelo.pojo.GameCount;

public class GameDAOImpl implements GameDAO{
	
	private static final Logger LOG = Logger.getLogger(GameDAOImpl.class);
	public static GameDAOImpl INSTANCE = null;
	
	private GameDAOImpl() {
		super();
	}

	public static synchronized GameDAOImpl getInstance() {
		
		if ( INSTANCE == null ){
			INSTANCE = new GameDAOImpl();
		}
		
		return INSTANCE;		
	}

	private final String SQL_GET_ALL = "SELECT g.id 'game_id', g.name 'game_name', price, c.id 'category_id', c.name 'category_name'"
										+ " FROM games g, categories c" 
										+ " WHERE g.category_id = c.id"
										+ " ORDER BY g.id DESC LIMIT 500; ";
	
	private final String SQL_GET_LAST = "SELECT g.id 'game_id', g.name 'game_name', price, c.id 'category_id', c.name 'category_name'"
										+ " FROM games g, categories c" 
										+ " WHERE g.category_id = c.id"
										+ " ORDER BY g.id DESC LIMIT ?; ";
	
	private final String SQL_GET_BY_CATEGORY = "SELECT g.id 'game_id', g.name 'game_name', price, c.id 'category_id', c.name 'category_name'" 
										+ " FROM games g, categories c" 
										+ " WHERE g.category_id  = c.id"
										+ " AND c.id = ? " // filtramos por el id de la categoria
										+ " ORDER BY g.id DESC LIMIT ? ; ";
	
	private final String SQL_GET_BY_ID = "SELECT g.id 'game_id', g.name 'game_name', price, c.id 'category_id', c.name 'category_name'"
										+ " FROM games g, categories c"
										+ " WHERE g.category_id = c.id AND g.id = ? LIMIT 500; ";
	
	private final String SQL_COUNT_ALL_GAMES = "SELECT * FROM count_games; ";
	
	private final String SQL_COUNT_GAMES = "SELECT * FROM count_games WHERE user_id = ?; ";
	
	private final String SQL_GET_ALL_BY_USER = "SELECT g.id 'game_id', g.name 'game_name', price, image, c.id 'category_id', c.name 'category_name'"
										+ " FROM games g, categories c" 
										+ " WHERE g.category_id  = c.id AND g.user_id = ?"  
										+ " ORDER BY g.id DESC LIMIT 500; ";
	
	private final String SQL_GET_BY_USER_APPROVED_GAME = "SELECT g.id 'game_id', g.name 'game_name', price, image, c.id 'category_id', c.name 'category_name'"
										+ " FROM games g, categories c" 
										+ " WHERE g.category_id  = c.id AND approval_date IS NOT NULL AND g.user_id = ?"  
										+ " ORDER BY g.id DESC LIMIT 500; ";

	private final String SQL_GET_BY_USER_NON_APPROVED_GAME = "SELECT g.id 'game_id', g.name 'game_name', price, image, c.id 'category_id', c.name 'category_name'"
										+ " FROM games g, categories c" 
										+ " WHERE g.category_id  = c.id AND approval_date IS NULL AND g.user_id = ?"  
										+ " ORDER BY g.id DESC LIMIT 500; ";
	
	
	private final String SQL_CREATE = "INSERT INTO games (name, price, image, user_id, category_id) VALUES (?, ?, ?, ?, ?); ";
	
	private final String SQL_UPDATE = "UPDATE games SET name = ?, price = ?, image = ?, user_id = ?, category_id = ?, approval_date = ? WHERE id = ?; ";
	
	private final String SQL_DELETE = "DELETE FROM games WHERE id = ?; ";
	
	private final String SQL_VALIDATE = "UPDATE games SET approval_Date = NOW() WHERE id = ?; ";
	
	private final String SQL_GET_BY_ID_BY_USER = "SELECT g.id 'game_id', g.name 'game_name', price, c.id 'category_id', g.user_id, c.name 'category_name'"
			+ " FROM games g, categories c"
			+ " WHERE g.category_id = c.id AND g.id = ? AND g.user_id = ? LIMIT 500; ";
	
	public ArrayList<Game> getAll() {
	
		ArrayList<Game> register = new ArrayList<Game>();

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
	public ArrayList<Game> getLast(int numReg) {
		
		ArrayList<Game> register = new ArrayList<Game>();

		try(
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_GET_LAST);
			){
			
			pst.setInt( 1, numReg);
			
			try ( ResultSet rs = pst.executeQuery() ){
				
				LOG.debug(pst);
				while( rs.next() ) {
					
					register.add(mapper(rs));					
				}
			}	
		} catch (Exception e) {
			
			LOG.error(e);
		}
		
		return register;
	}
	
	
	@Override
	public ArrayList<Game> getAllByCategory(int categoryId, int numReg) {
		
		ArrayList<Game> register = new ArrayList<Game>();	
		
		try (
			Connection conexion = ConnectionManager.getConnection();
			PreparedStatement pst = conexion.prepareStatement(SQL_GET_BY_CATEGORY);
			){
			
			pst.setInt( 1, categoryId);
			pst.setInt( 2, numReg);
			
			try ( ResultSet rs = pst.executeQuery() ){
				
				LOG.debug(pst);
				while ( rs.next() ) {					
					register.add( mapper(rs) );					
				}
			}
			
		} catch (Exception e) {			
			LOG.error(e);			
		}
		return register;
	}
	
	
	@Override
	public Game getById(int id) throws Exception {
		
		Game game = new Game();

		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_GET_BY_ID);
			){

			pst.setInt(1, id);
			
			try ( ResultSet rs = pst.executeQuery() ){
				
				LOG.debug(pst);
				if (rs.next()) {

					game = mapper(rs);

				} else {

					throw new Exception ("Couldn't find ID: " + id);
				}
			}	
		}
		return game;
	}
	
	
	@Override
	public ArrayList<Game> getAllByUser(int userId) {
		
		ArrayList<Game> register = new ArrayList<Game>();
		
		try (
			Connection conexion = ConnectionManager.getConnection();
			PreparedStatement pst = conexion.prepareStatement(SQL_GET_ALL_BY_USER);
			){
			
			pst.setNull(1, java.sql.Types.NULL);
			pst.setInt(1, userId);
			
			try( ResultSet rs = pst.executeQuery() ){
				
				LOG.debug(pst);
				while (rs.next()) {	
					register.add(mapper(rs));	
				}
			}	

		} catch (Exception e) {
			LOG.error(e);
		}

		return register;
	}
	
	
	@Override
	public ArrayList<Game> getByUser(int userId, boolean isApproved) {
		
		ArrayList<Game> register = new ArrayList<Game>();

		String sql = (isApproved) ? SQL_GET_BY_USER_APPROVED_GAME : SQL_GET_BY_USER_NON_APPROVED_GAME;
		
		try (
			Connection conexion = ConnectionManager.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql);
			){
			
			pst.setNull(1, java.sql.Types.NULL);
			pst.setInt(1, userId);
			
			try( ResultSet rs = pst.executeQuery() ){
				
				LOG.debug(pst);
				while (rs.next()) {	
					register.add(mapper(rs));	
				}
			}	

		} catch (Exception e) {
			LOG.error(e);
		}

		return register;
	}
	
	
	@Override
	public GameCount getAllGameCount() {
		
		GameCount count = new GameCount();
		
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_COUNT_ALL_GAMES);
			){
			
			
			try( ResultSet rs = pst.executeQuery() ){
				if (rs.next()) {
					count.setTotal(rs.getInt("total"));
					count.setApproved(rs.getInt("approved"));
					count.setPending(rs.getInt("pending"));
				}
			}
			
		} catch (Exception e) {
			LOG.error(e);
		} 
		
		return count;
	}
	
	
	@Override
	public GameCount getGameCount(int userId) {
		
		GameCount count = new GameCount();
		
		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_COUNT_GAMES);
			){
			
			pst.setInt(1, userId);
			LOG.debug(pst);
			
			try( ResultSet rs = pst.executeQuery() ){
				if (rs.next()) {
					count.setUser_id(userId);
					count.setTotal(rs.getInt("total"));
					count.setApproved(rs.getInt("approved"));
					count.setPending(rs.getInt("pending"));
				}
			}
			
		} catch (Exception e) {
			LOG.error(e);
		} 
		
		return count;
	}
	
	
	@Override
	public Game create(Game game) throws Exception {
		
		if (game.getName() == null) {

			throw new Exception("You must insert a name");
		}
		
		try ( 
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
			){
			
			pst.setString(1, game.getName());
			pst.setDouble(2, game.getPrice());
			pst.setString(3, game.getImage());
			pst.setInt(4, game.getUser().getId());
			pst.setInt(5, game.getCategory().getId());
			
			
		
			int affectedRows = pst.executeUpdate();
			
			LOG.debug(pst);
			if (affectedRows == 1) {
				
				//conseguir el ID
				try ( ResultSet rsKeys = pst.getGeneratedKeys(); ) {
					
					if (rsKeys.next()) {

						game.setId(rsKeys.getInt(1));
					}
				}
			} else {

				throw new Exception ("Couldn't create a new entry for " + game.getName());
			}
		} catch (SQLException e) {
			
			throw new SQLException("The name already exists");
		}
		
		return game;
	}
	
	
	@Override
	public Game update(Game game) throws Exception {
		
		if (game.getName() == null) {
			
			throw new Exception("You must insert a name.");
		}
		
		try ( 
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_UPDATE);
			){
			
			game = getById(game.getId(), game.getUser().getId());
			
			pst.setString(1, game.getName());
			pst.setDouble(2, game.getPrice());
			pst.setString(3, game.getImage());
			pst.setInt(4, game.getUser().getId());
			pst.setInt(5, game.getCategory().getId());
			
			
			
			game.setApprovalDate(null);
			
			pst.setString(6, game.getApprovalDate());
			
			pst.setInt(7, game.getId());
			
			int affectedRows = pst.executeUpdate();
			
			LOG.debug(pst);
			if (affectedRows != 1) {
				
				throw new Exception ("Couldn't create a new entry for ID: " + game.getId());
			}
			
		} catch (SQLException e) {
			
			LOG.error(e);
			throw new SQLException("The name already exists");

		} 
		return game;
	}
	
	
	@Override
	public Game delete(int id) throws Exception {
		
		Game game = new Game();
		
		try (	
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_DELETE);
			){
			
			pst.setInt(1, id);
			
			game = getById(id);

			int affectedRows = pst.executeUpdate();
			
			LOG.debug(pst);
			if (affectedRows != 1) {
				
				throw new Exception("Couldn't delete the game with ID: " + id);
			}
		}
		return game;
	}
	
	
	@Override
	public void validate(int id) throws Exception {
		
		try ( 
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_VALIDATE);
			){
			
			pst.setInt(1, id);
			
			int affectedRows = pst.executeUpdate();
			
			LOG.debug(pst);
			if (affectedRows != 1) {
				
				throw new Exception ("Couldn't edit the entry for ID: " + id);
			}
			
		} catch (SQLException e) {
			
			LOG.error(e);
			throw new SQLException("There was an error");
		}
	}
	
	
	private Game mapper( ResultSet rs ) throws SQLException {
		
		Game g = new Game();
		Category c = new Category();
		
		g.setId(rs.getInt("game_id"));
		g.setName(rs.getString("game_name"));
		g.setPrice( rs.getFloat("price"));
		
		c.setId(rs.getInt("category_id"));
		c.setName(rs.getString("category_name"));
		g.setCategory(c);
		
		return g;
	}

	@Override
	public Game delete(int id, int userId) throws Exception, SecurityException {
		
		Game game = new Game();
		
		//Este metodo lanza una SecurityException por lo que no hace falta usarla abajo
		game = getById(id, userId);
		
		try (	
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_DELETE);
			){
			
			pst.setInt(1, id);
			pst.setInt(2, userId);

			pst.executeUpdate();
			LOG.debug(pst);
		}
		return game;
	}

	@Override
	public Game getById(int id, int userId) throws Exception, SecurityException {
		
		Game game = new Game();

		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_GET_BY_ID_BY_USER);
			){

			pst.setInt(1, id);
			pst.setInt(2, userId);
			
			try ( ResultSet rs = pst.executeQuery() ){
				
				LOG.debug(pst);
				if (rs.next()) {

					game = mapper(rs);

				} else {

					throw new SecurityException();
				}
			} 	
		} 
		return game;
	}	
}
