package com.jojoz.gh.service;

import java.util.List;

import com.jojoz.gh.dto.UnionVO;
import com.jojoz.gh.entity.Union;
import com.jojoz.gh.entity.User;

public interface UnionService {

	
	/**
	 * 保存union
	 * @param union
	 * @return
	 */
	public int savaUnion(Union union);
	
	/**
	 * 查询
	 * @param words
	 * @param filed
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<UnionVO> query(String words,Integer check,Integer pageSize,Integer pageNum,User user);
	
	/**
	 * 获取用户下的union
	 * @param userJson
	 * @return
	 */
	public Union getUnionByUser(User user);
	/**
	 * 通过用户id获取该用户的union
	 * @param userId
	 * @return
	 */
	public Union getUnionByUserId(String userId);

	public Union getUnionById(String id);

	public int updateUnion(Union union);
}
