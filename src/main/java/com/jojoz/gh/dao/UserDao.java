package com.jojoz.gh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jojoz.gh.dto.UserVO;
import com.jojoz.gh.entity.User;

public interface UserDao {

	/**
	 * 通过用户名密码取得单个用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUserByLogin(@Param("username") String username,
			@Param("password") String password);

	/**
	 * 添加用户
	 * 
	 * @param username
	 * @param password
	 * @param state
	 *            用户类型
	 * @return
	 */
	public int save(@Param("id") String id,@Param("username") String username,
			@Param("password") String password, @Param("state") int state,@Param("produceDivision") String produceDivision,@Param("addTime") Date addTime);

	/**
	 * 查询用户列表
	 * 
	 * @param count
	 * @param limit
	 * @param state
	 * @return
	 */
	public List<UserVO> getList(@Param("count") int count,
			@Param("limit") int limit, @Param("state") int state,@Param("words") String words,@Param("sort") String sort,@Param("order") String order,@Param("produce")String produce);

	/**
	 * 更新用户
	 * 
	 * @param username
	 * @param password
	 * @param state
	 * @return
	 */
	public int update(@Param("username") String username,
			@Param("password") String password, @Param("state") int state,
			@Param("id") String id);
	
	
	/**
	 * 更新token
	 * @param id
	 * @param token
	 * @return
	 */
	public int updateToken(@Param("id") String id,@Param("token") String token);
	
	/**
	 * 根据用户名获取token
	 * @param username
	 * @return
	 */
	public User getUserByUsername(@Param("username") String username);
	
	
	public int getCount(@Param("words") String words);
	
	public UserVO getUserById(@Param("id") String id);

	public int delUser(@Param("id")String id);

	public int updateUser(@Param("id") String id,@Param("username") String username,@Param("password") String password,
			@Param("state")Integer state, @Param("produceDivision")String produceDivision,@Param("addTime") Date addTime);
 	
}
