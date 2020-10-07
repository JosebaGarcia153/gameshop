package com.games.webapp.modelo.pojo;

/**
 * Clase para generar instancias de usuarios logueados
 * browser guarda el nombre del navegador desde que se ha logueado el usuario
 * ip guarda la IP del usuario
 * @see com.games.webapp.listener.LoggedUserListener
 *
 */
public class LoggedUser extends Usuario {
	
	//Otros datos obtenidos de la REQUEST
	private String browser;
	private String ip;
	
	
	public LoggedUser() {
		super();
		this.browser = "";
		this.ip = "";
	}
	
	
	public String getBrowser() {
		return browser;
	}
	
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	@Override
	public String toString() {
		return "LoggedUser [browser=" + browser + ", ip=" + ip + "]";
	}
}
