package com.jojoz.gh.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jojoz.gh.dao.UserDao;
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
	public String login(String username, String password) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	public String registUser(String username, String password) {
		// TODO Auto-generated method stub
		int state = 1;
		if(null!=username && !username.equals("")){
			int result = userDao.sava(username, password, state);
			return result+"";
		}
		
		return "0";
	}

}
