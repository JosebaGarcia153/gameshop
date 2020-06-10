package modelo.dao;

import java.util.ArrayList;
import model.pojo.Game;

public interface GameDAO {
	
	ArrayList<Game> getAll();
	Game create(Game game)  throws Exception;
}
