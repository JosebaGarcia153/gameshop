package com.games.webapp.modelo.dao;

/**
 * Excepción customizada para cuando un usuario intenta alterar cosas que no le pertenezen en la BBDD
 */
public class SecurityException extends Exception {

	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "You don't have the priviledges to alter this entry";

	public SecurityException() {
		super(MESSAGE);
	}
}