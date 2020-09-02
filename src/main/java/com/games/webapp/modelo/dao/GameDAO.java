package com.games.webapp.modelo.dao;

import java.util.ArrayList;

import com.games.webapp.modelo.Crudable;
import com.games.webapp.modelo.pojo.Game;
import com.games.webapp.modelo.pojo.GameCount;

public interface GameDAO extends Crudable<Game> {
	
	ArrayList<Game> getLast (int numReg);
	ArrayList<Game> getAllByCategory (int categoryId, int numReg);
	void validate(int id) throws Exception;
	ArrayList<Game> getAllByUser(int userId);
	ArrayList<Game> getByUser(int userId, boolean isApproved);
	GameCount getAllGameCount();
	GameCount getGameCount(int userId);
	
	/**
	 * Eliminar un registro, pero comprobamos que pertenezca a un usuario
	 * @param idProducto
	 * @param idUsuario
	 * @return Producto eliminado
	 * @throws Exception 
	 * @throws SeguridadException Si no puede eliminar el producto porque no pertenece al usuario
	 */
	Game delete(int id, int userId) throws Exception, SecurityException;
	
	/**
	 * Busca juegos pertenecientes al usuario
	 * @param idProducto
	 * @param idUsuario
	 * @return
	 * @throws SeguridadException
	 */
	Game getById(int id, int userId) throws Exception, SecurityException;	
}
