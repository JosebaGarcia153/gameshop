package com.games.webapp.controller;

public class Alert {

	
	private String type;
	private String text;

	
	public Alert() {
		super();
		this.text = "";
		this.type = "";
	}

	public Alert(String type, String text) {
		this();
		this.type = type;
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Alert [type=" + type + ", text=" + text + "]";
	}
}