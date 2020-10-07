package com.games.webapp.modelo.dao;

import com.games.webapp.modelo.pojo.Usuario;

public interface UserDAO {
	
	/**
	 * Comprueba si un usuario existe en la BBD
	 * @param name Nombre del usuario
	 * @param password Contraseña del usuario
	 * @return usuario encontrado
	 * @see com.games.webapp.modelo.dao.impl.UserDAOImpl
	 */
	Usuario exists (String name, String password);
	
	/**
	 * Crea un usuario en la BBDD
	 * @param usuario objeto creado en un controlador
	 * @return usuario generado
	 * @throws Exception Si la el usuario ya existe o los datos son incorrectos
	 * @see com.games.webapp.modelo.dao.impl.UserDAOImpl
	 */
	Usuario create(Usuario usuario) throws Exception;
	
	/**
	 * Busca el nombre exacto de un usuario en la BBDD
	 * @param name Nombre a buscar
	 * @return true si encuentra al usuario, false si no lo encuentra
	 * @see com.games.webapp.modelo.dao.impl.UserDAOImpl
	 */
	boolean searchByName(String name);
}
