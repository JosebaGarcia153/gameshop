package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.pojo.Game;
import modelo.conexion.ConnectionManager;

public class gamesDAO {
	
	public static gamesDAO INSTANCE = null;
	
	private gamesDAO() {
		super();
	}

	public static synchronized gamesDAO getInstance() {
		
		if ( INSTANCE == null ){
			INSTANCE = new gamesDAO();
		}
		
		return INSTANCE;		
	}

	private final String SQL_GET_ALL = "SELECT id, name, price FROM games ORDER BY id DESC; ";
	
	public ArrayList<Game> getAll() {
	
		ArrayList<Game> registros = new ArrayList<Game>();

		try(
				Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();
				
				){
				
				while( rs.next() ) {
					// recuperar columnas del resultset
					int id = rs.getInt("id");
					String name = rs.getString("name");
					Double price = rs.getDouble("price");
					
					// crear pojo con datos del rs
					Game game = new Game();
					game.setId(id);
					game.setName(name);
					game.setPrice(price);
					
					// guardar en ArrayList
					registros.add(game);						
				}
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return registros;
	}
}
