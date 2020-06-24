package modelo.dao;

import java.util.ArrayList;

import modelo.Crudable;
import modelo.pojo.Game;

public interface GameDAO extends Crudable<Game> {
	
	ArrayList<Game> getLast (int numReg);
	ArrayList<Game> getAllByCategory (int categoryId, int numReg);
}
