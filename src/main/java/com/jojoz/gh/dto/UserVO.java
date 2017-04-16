package com.jojoz.gh.dto;

import java.util.Date;

public class UserVO {

	private String id;
	private String username;
	private int state;
	private String token;
	private String produceDivision;
	private Date addTime;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProduceDivision() {
		return produceDivision;
	}
	public void setProduceDivision(String produceDivision) {
		this.produceDivision = produceDivision;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
	
	
	
	
}
