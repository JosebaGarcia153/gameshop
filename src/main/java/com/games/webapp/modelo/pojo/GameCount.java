package com.games.webapp.modelo.pojo;

public class GameCount {
	
	private int user_id;
	private int total;
	private int approved;
	private int pending;
	
	
	public GameCount() {
		super();
	}

	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	public int getApproved() {
		return approved;
	}
	
	public void setApproved(int approved) {
		this.approved = approved;
	}
	
	
	public int getPending() {
		return pending;
	}
	
	public void setPending(int pending) {
		this.pending = pending;
	}


	@Override
	public String toString() {
		return "GameCount [user_id=" + user_id + ", total=" + total + ", approved=" + approved + ", pending=" + pending
				+ "]";
	}
}
