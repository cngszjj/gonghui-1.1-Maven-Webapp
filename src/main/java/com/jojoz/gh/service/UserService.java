package com.jojoz.gh.service;

import java.util.List;

import com.jojoz.gh.dto.UserVO;
import com.jojoz.gh.entity.User;

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
	public User login(String username,String password);
	
	/**
	 * 用户注册
	 * @param username
	 * @param password
	 * @return
	 */
	public int  registUser(String username,String password,int state,String produceDivision); 
	
	/**
	 * 判断用户名是否存在
	 * @param username
	 * @return
	 */
	public boolean existUsername(String username);
	
	/**
	 * 权限验证
	 * @param userJson
	 * @return
	 */
	public User userValidate(String userJson);
	
	/**
	 * 查询用户
	 * @param words
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<UserVO> getUserList(String words,int pageSize,int pageNum,String sort,String order,String produce);
	
	public int getCount(String words);
	
	public UserVO getUserById(String id);
	
	public int delUser(String id);
	
	public int updateUser(User user);
}
