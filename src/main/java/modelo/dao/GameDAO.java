package modelo.dao;

import java.util.ArrayList;
import model.pojo.Game;

public interface GameDAO {
	
	ArrayList<Game> getAll();
	Game getById(int id) throws Exception;
	
	Game create(Game game) throws Exception;
	Game update(Game game) throws Exception;
	Game delete(int id) throws Exception;
}
