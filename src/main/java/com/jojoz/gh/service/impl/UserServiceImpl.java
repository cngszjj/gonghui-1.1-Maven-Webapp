package com.jojoz.gh.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.jojoz.gh.dao.UserDao;
import com.jojoz.gh.dto.UserVO;
import com.jojoz.gh.entity.User;
import com.jojoz.gh.service.UserService;

/**
 * 2017年03月09日00:17:59
 * @author jojoz
 *
 */
@Service
public class UserServiceImpl implements UserService{
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private UserDao userDao;
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User u = userDao.getUserByLogin(username, getMD5(password) );
		if(null!=u){
			String token = getToken(username);
			int result = userDao.updateToken(u.getId(), token);
			if(result==1){
				u.setPassword("");
				u.setToken(token);
				return u;
			}
		}
		return null;
	}

	public int registUser(String username, String password, int state,String produceDivision) {
		// TODO Auto-generated method stub
		String id  = UUID.randomUUID().toString();
		Date d = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(null!=username && !username.equals("")){
			int result = userDao.save(id,username,getMD5(password), state,produceDivision,d);
			return result;
		}
		return 0;
	}
	
	
	private String getMD5(String id){
		if(null == id || "".equals(id)){
			return null;
		}
		String base = id  ;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	
	private String getToken(String username){
		String str  = UUID.randomUUID().toString();
		return getMD5(str+username);
		
	}

	@Override
	public boolean existUsername(String username) {
		// TODO Auto-generated method stub
		
		User user = userDao.getUserByUsername(username);
		if(null!= user ){
			return true;
		}
		return false;
	}

	@Override
	public User userValidate(String userJson) {
		// TODO Auto-generated method stub
		if(null == userJson || "".equals(userJson)){
			return null;
		}
		JSONObject json = JSONObject.fromObject(userJson);
		if(null == json){
			return null;
		}
		if(!json.containsKey("username") || !json.containsKey("token")){
			return null;
		}
		String username = json.getString("username");
		String token = json.getString("token");
		User user = userDao.getUserByUsername(username);
		if(null == user){
			return null;
		}
		if(user.getToken().equals(token)){
			return user;
		}
		return null;
	}

	@Override
	public List<UserVO> getUserList(String words, int pageSize, int pageNum,String sort,String order) {
		// TODO Auto-generated method stub
		return userDao.getList(pageSize,(pageNum-1)*pageSize, 0, words,sort,order);
	}

	@Override
	public int getCount(String words) {
		// TODO Auto-generated method stub
		return userDao.getCount(words);
	}

	@Override
	public UserVO getUserById(String id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	@Override
	public int delUser(String id) {
		// TODO Auto-generated method stub
		
		return userDao.delUser(id);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		user.setAddTime(new Date());
		return userDao.updateUser(user.getId(),user.getUsername(),getMD5(user.getPassword()),user.getState(),user.getProduceDivision(),user.getAddTime());
	}

	

}
