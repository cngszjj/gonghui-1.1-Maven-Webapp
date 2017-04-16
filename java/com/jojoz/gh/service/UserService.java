package com.jojoz.gh.service;
/**
 * 2017年03月09日00:18:32
 * @author jojoz
 *
 */
public interface UserService {

	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public String login(String username,String password);
	
	/**
	 * 用户注册
	 * @param username
	 * @param password
	 * @return
	 */
	public String  registUser(String username,String password);       
}
