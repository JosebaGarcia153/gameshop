package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.pojo.Category;
import model.pojo.Game;
import modelo.conexion.ConnectionManager;

public class GameDAOImpl implements GameDAO{
	
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
	
	private final String SQL_READ_BY_ID = "SELECT g.id 'game_id', g.name 'game_name', price, c.id 'category_id', c.name 'category_name'"
										+ " FROM games g, categories c"
										+ " WHERE g.category_id = c.id AND g.id = ? LIMIT 500; ";
	
	private final String SQL_CREATE = "INSERT INTO games (name, price, category_id) VALUES (?, ?, ?); ";
	private final String SQL_UPDATE = "UPDATE games SET name = ?, price = ?, category_id = ? WHERE id = ?; ";
	private final String SQL_DELETE = "DELETE FROM games WHERE id = ?; ";
	
	public ArrayList<Game> getAll() {
	
		ArrayList<Game> register = new ArrayList<Game>();

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
	
	@Override
	public ArrayList<Game> getLast(int numReg) {
		
		ArrayList<Game> register = new ArrayList<Game>();

		try(
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_LAST);
				){
			
			pst.setInt( 1, numReg);
			
			try ( ResultSet rs = pst.executeQuery() ){
				
				while( rs.next() ) {
					
					register.add(mapper(rs));					
				}
			}	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return register;
	}
	
	@Override
	public ArrayList<Game> getAllByCategory(int categoryId, int numReg) {
		
		ArrayList<Game> register = new ArrayList<Game>();	
		
		try (
				Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_BY_CATEGORY);
			) {			
					pst.setInt( 1, categoryId);
					pst.setInt( 2, numReg);

					try ( ResultSet rs = pst.executeQuery() ){
						while ( rs.next() ) {					
							register.add( mapper(rs) );					
						}
					}
			
		} catch (Exception e) {			
			e.printStackTrace();			
		}
		return register;
	}
	
	@Override
	public Game getById(int id) throws Exception {
		
		Game game = new Game();

		try (
				Connection connection = ConnectionManager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQL_READ_BY_ID);
				){

			pst.setInt(1, id);

			try ( ResultSet rs = pst.executeQuery() ){

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
			pst.setInt(3, game.getCategory().getId());

			int affectedRows = pst.executeUpdate();

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
			
			pst.setString(1, game.getName());
			pst.setDouble(2, game.getPrice());
			pst.setInt(3, game.getCategory().getId());

			pst.setInt(4, game.getId());

			int affectedRows = pst.executeUpdate();

			if (affectedRows != 1) {
				
				throw new Exception ("Couldn't create a new entry for ID: " + game.getId());
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
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
			
			if (affectedRows != 1) {
				
				throw new Exception("Couldn't delete the game with ID: " + id);
			}
		}
		return game;
	}
	
	private Game mapper( ResultSet rs ) throws SQLException {
		
		Game g = new Game();
		Category c = new Category();
		
		g.setId(rs.getInt("game_id"));
		g.setName(rs.getString("game_name"));
		g.setPrice( rs.getDouble("price"));
		
		c.setId(rs.getInt("category_id"));
		c.setName(rs.getString("category_name"));
		g.setCategory(c);
		
		return g;
	}	
}
