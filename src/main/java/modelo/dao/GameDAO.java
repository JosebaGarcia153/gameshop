package modelo.dao;

import java.util.ArrayList;

import model.pojo.Game;

public interface GameDAO extends Crudable<Game> {
	
	ArrayList<Game> getLast (int numReg);
	ArrayList<Game> getAllByCathegory (int cathegoryId, int numReg);
}
