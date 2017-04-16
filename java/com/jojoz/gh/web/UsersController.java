package com.jojoz.gh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jojoz.gh.service.UserService;

@Component
@RequestMapping("/users")
public class UsersController {

	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String login(@PathVariable("username") String username,
			@PathVariable("password") String password) {

		return null;
	}
	
	
	@RequestMapping(value = "/user/addUser")
	public String addUser(@PathVariable("username") String username,
			@PathVariable("password") String password,
			@PathVariable("state") int state){
		
		userService.registUser(username, password);

		
		return null;
	}
}
