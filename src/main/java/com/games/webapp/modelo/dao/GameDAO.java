package com.games.webapp.modelo.dao;

import java.util.ArrayList;

import com.games.webapp.modelo.Crudable;
import com.games.webapp.modelo.pojo.Game;
import com.games.webapp.modelo.pojo.GameCount;

public interface GameDAO extends Crudable<Game> {
	
	/**
	 * Busca una cantidad especifica de los ultimos juegos ingresados en la BBDD
	 * @param numReg Numero de entradas a encontrar
	 * @return ArrayList{@code <}Game{@code >} Entradas encontradas
	 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
	 */
	ArrayList<Game> getLast (int numReg);
	
	/**
	 * Busca todos los juegos ordenados de una categoria y muestra el numero de ultimas entradas indicadas por el usuario
	 * @param categoryId ID de la categoria a mostrar
	 * @param numReg Numero de entradas a encontrar
	 * @return ArrayList{@code <}Game{@code >} Entradas encontradas
	 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
	 */
	ArrayList<Game> getAllByCategory (int categoryId, int numReg);
	
	/**
	 * Busca todos los juegos agregados a la BBDD por un usuario especifico
	 * @param userId Usuario que ha ingresado los juegos
	 * @return ArrayList{@code <}Game{@code >} Entradas encontradas
	 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
	 */
	ArrayList<Game> getAllByUser(int userId);
	
	/**
	 * Busca todos los juegos agregados a la BBDD por un usuario especifico y han sido aprovados por un admin
	 * @param userId Usuario que ha ingresado los juegos
	 * @param isApproved Valor "true" marca que si esta aprobado
	 * @return ArrayList{@code <}Game{@code >} Entradas encontradas
	 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
	 */
	ArrayList<Game> getByUser(int userId, boolean isApproved);
	
	/**
	 * Un admin valida un juego en la BBDD
	 * @param id ID del juego no existe
	 * @throws Exception Si la ID del juego no esta en la BBDD
	 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
	 */
	void validate(int id) throws Exception;
	
	/**
	 * Cuenta cuantos juegos hay aprobados, sin aprobar y en total
	 * @see GameCount com.games.webapp.modelo.pojo.GameCount
	 * @return GameCount Objeto con datos contados
	 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
	 */
	GameCount getAllGameCount();
	
	/**
	 * Cuenta cuantos juegos hay aprobados, sin aprobar y en total de un usuario
	 * @param userId Usuario al que pertenecen los juegos
	 * @see GameCount com.games.webapp.modelo.pojo.GameCount
	 * @return GameCount Objeto con datos contados
	 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
	 */
	GameCount getGameCount(int userId);
	
	/**
	 * Edita un juego de la BBDD 
	 * @param game datos que modificar al juego
	 * @return Game Datos del juego modificado
	 * @throws Exception Si la ID del juego no esta en la BBDD
	 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
	 */
	Game update(Game game) throws Exception;
	
	/**
	 * Elimina un juego de la BBDD despues de comprobar que pertenezca al usuario intentando borrarlo o aun admin
	 * @param id ID del juego a borrar
	 * @param userId Usuario al que pertenece el juego
	 * @return Game Datos del juego eliminado
	 * @throws Exception Si la ID del juego no esta en la BBDD
	 * @throws SecurityException Si no puede eliminar el juego porque no pertenece al usuario
	 * @see com.games.webapp.modelo.dao.SecurityException
	 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
	 */
	Game delete(int id, int userId) throws Exception, SecurityException;
	
	/**
	 * Busca todos los juegos agregados a la BBDD por un usuario especifico y han sido aprovados por un admin
	 * @param id ID del juego a encontrar
	 * @param userId Usuario al que pertenece el juego
	 * @return Game Datos del juego encontrado
	 * @throws Exception Si la ID del juego no esta en la BBDD
	 * @throws SecurityException Si el juego no pertenece al usuario
	 * @see com.games.webapp.modelo.dao.impl.GameDAOImpl
	 */
	Game getById(int id, int userId) throws Exception, SecurityException;	
}
