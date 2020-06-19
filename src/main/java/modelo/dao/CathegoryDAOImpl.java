package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.pojo.Cathegory;
import modelo.conexion.ConnectionManager;

public class CathegoryDAOImpl implements CathegoryDAO{
	
	private static CathegoryDAOImpl INSTANCE = null;
	
	private CathegoryDAOImpl() {
		super();
	}
	
	public static synchronized CathegoryDAOImpl getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new CathegoryDAOImpl();
		}
		
		return INSTANCE;
	}
	
	private final String SQL_GET_ALL = "SELECT id, name FROM cathegories ORDER BY name ASC LIMIT 500; ";
	
	@Override
	public ArrayList<Cathegory> getAll() {
		
		ArrayList<Cathegory> register = new ArrayList<Cathegory>();

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
	public Cathegory getById(int id) throws Exception {
		throw new Exception("Not implemented");
	}

	@Override
	public Cathegory create(Cathegory pojo) throws Exception {
		throw new Exception("Not implemented");
	}

	@Override
	public Cathegory update(Cathegory pojo) throws Exception {
		throw new Exception("Not implemented");
	}

	@Override
	public Cathegory delete(int id) throws Exception {
		throw new Exception("Not implemented");
	}
	
	private Cathegory mapper(ResultSet rs) throws SQLException {
		
		Cathegory c = new Cathegory();
		
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		
		return c;
	}
}
