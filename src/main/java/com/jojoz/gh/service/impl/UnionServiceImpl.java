package com.jojoz.gh.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jojoz.gh.dao.UnionDao;
import com.jojoz.gh.dao.UserDao;
import com.jojoz.gh.dto.UnionVO;
import com.jojoz.gh.entity.Union;
import com.jojoz.gh.entity.User;
import com.jojoz.gh.service.UnionService;


@Service
public class UnionServiceImpl implements UnionService{

	@Autowired
	private UnionDao unionDao;
	
	@Autowired
	private UserDao userDao;
	@Override
	public int savaUnion(Union union) {
		// TODO Auto-generated method stub
		if(null!=union){
			String id = UUID.randomUUID().toString();
			union.setId(id);
			union.setAddTime(new Date());
			return unionDao.save(union);
		}
		return 0;
	}
	@Override
	public List<UnionVO> query(String words, Integer check, Integer pageSize,
			Integer pageNum,User user) {
		// TODO Auto-generated method stub
		String produceDivision = user.getProduceDivision();
		if(null == produceDivision && "".equals(produceDivision)){
			return null;
		}
		//如果是西安市总工会的   则可以操作全部
		if("西安市总工会".equals(produceDivision)){
			produceDivision = null;
		}
		return unionDao.query(words, check, pageSize, (pageNum-1)*pageSize,produceDivision);
	}
	@Override
	public Union getUnionByUser(User user) {
		// TODO Auto-generated method stub
		
//		JSONObject json = JSONObject.fromObject(userJson);
		
//		String username = json.getString("username");
//		User user = userDao.getUserByUsername(username);
		Union union = unionDao.getUnionByUserId(user.getId());
		
		

		return union;
	}
	@Override
	public Union getUnionByUserId(String userId) {
		// TODO Auto-generated method stub
		return unionDao.getUnionByUserId(userId);
	}
	@Override
	public Union getUnionById(String id) {
		// TODO Auto-generated method stub
		return unionDao.getUnionById(id);
	}
	@Override
	public int updateUnion(Union union) {
		// TODO Auto-generated method stub
		union.setAddTime(new Date());
		return unionDao.update(union);
	}

}
