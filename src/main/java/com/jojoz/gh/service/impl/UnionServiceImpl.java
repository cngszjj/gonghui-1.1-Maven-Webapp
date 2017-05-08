package com.jojoz.gh.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jojoz.gh.dao.UnionDao;
import com.jojoz.gh.dao.UserDao;
import com.jojoz.gh.dto.UnionVO;
import com.jojoz.gh.dto.UnionVO1;
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
	public List<UnionVO1> query(String words, Integer check, Integer pageSize,
			Integer pageNum,User user ,String pd_words,String dstart,String dend) {
		// TODO Auto-generated method stub
		String produceDivision = user.getProduceDivision();
		if(null == produceDivision && "".equals(produceDivision)){
			return null;
		}
		//如果是西安市总工会的   则可以操作全部
		if("西安市总工会".equals(produceDivision)){
			produceDivision = pd_words;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date sd = null;
		if(null != dstart && !"".equals(dstart)){
			try {
				sd = sdf.parse(dstart);
				dstart = sdf1.format(sd);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Date ed = null;
		if(null != dend && !"".equals(dend)){
		try {
			ed = sdf.parse(dend);
			dend = sdf1.format(ed);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return unionDao.query(words, check, pageSize, (pageNum-1)*pageSize,produceDivision,dstart,dend);
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
	@Override
	public List<UnionVO1> getUnionForExport() {
		// TODO Auto-generated method stub
		
		return unionDao.getUnionForExport();
	}
	@Override
	public List<UnionVO1> query(String words, Integer check, Integer pageSize,
			Integer pageNum, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
