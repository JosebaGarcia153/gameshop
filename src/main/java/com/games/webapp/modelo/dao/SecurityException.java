package com.games.webapp.modelo.dao;

public class SecurityException extends Exception {

	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "You don't have the priviledges to alter this entry";

	public SecurityException() {
		super(MESSAGE);
	}
}