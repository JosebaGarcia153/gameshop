package com.games.webapp.modelo.dao;

import java.util.ArrayList;

import com.games.webapp.modelo.Crudable;
import com.games.webapp.modelo.pojo.Game;

public interface GameDAO extends Crudable<Game> {
	
	ArrayList<Game> getLast (int numReg);
	ArrayList<Game> getAllByCategory (int categoryId, int numReg);
	void validate(int id);
	ArrayList<Game> getAllByUser(int userId, boolean isApproved);
}
