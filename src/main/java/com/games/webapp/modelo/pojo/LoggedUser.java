package com.games.webapp.modelo.pojo;

public class LoggedUser extends User {
	
	//Otros datos obtenidos de la REQUEST
	private String browser;
	private String ip;
	
	
	public LoggedUser() {
		super();
		this.browser = "";
		this.browser = "";
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
