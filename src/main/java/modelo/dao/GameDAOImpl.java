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
	private final String SQL_CREATE = "INSERT INTO games (name, price) VALUES (?, ?); ";
	
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
	public Game create(Game game) throws Exception {
		
		if (game.getName() == null) {

			throw new Exception("You must insert a product name");
		}
		
		try ( 
				Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
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
	
	private Game mapper( ResultSet rs ) throws SQLException {
		
		Game game = new Game();
		
		game.setId(rs.getInt("id"));
		game.setName(rs.getString("name"));
		game.setPrice( rs.getDouble("price"));
		
		
		return game;
		
	}
}
