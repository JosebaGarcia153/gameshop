package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	private final String SQL_GET_ALL = "SELECT id, name, price FROM games ORDER BY id DESC; ";
	private final String SQL_READ_BY_ID = "SELECT id, name, price FROM games WHERE id = ?; ";
	
	private final String SQL_CREATE = "INSERT INTO games (name, price) VALUES (?, ?); ";
	private final String SQL_UPDATE = "UPDATE games SET name = ?, price = ? WHERE id = ?; ";
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
			
			throw new Exception("The name already exists");
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

			pst.setInt(3, game.getId());

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
		
		Game game = new Game();
		
		game.setId(rs.getInt("id"));
		game.setName(rs.getString("name"));
		game.setPrice( rs.getDouble("price"));
		
		
		return game;
		
	}

	
}
