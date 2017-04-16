package com.jojoz.gh.entity;

import java.util.Date;

public class User {
	private String id;
	private String username;
	private String password;
	private Integer state;// 3管理员 2 操作员 1 普通用户
	private String token;
	private String produceDivision;
	private Date addTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
