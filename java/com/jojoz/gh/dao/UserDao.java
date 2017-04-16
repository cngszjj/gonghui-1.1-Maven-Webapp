package com.jojoz.gh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	public int sava(@Param("username") String username,
			@Param("password") String password, @Param("state") int state);

	/**
	 * 查询用户列表
	 * 
	 * @param count
	 * @param limit
	 * @param state
	 * @return
	 */
	public List<User> getList(@Param("count") int count,
			@Param("limit") int limit, @Param("state") int state);

	/**
	 * 更新用户
	 * 
	 * @param username
	 * @param password
	 * @param state
	 * @return
	 */
	public User update(@Param("username") String username,
			@Param("password") String password, @Param("state") int state,
			@Param("id") int id);
}
