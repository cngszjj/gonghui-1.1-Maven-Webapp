package com.jojoz.gh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jojoz.gh.dto.GHRsult;
import com.jojoz.gh.dto.UnionRsult;
import com.jojoz.gh.dto.UserVO;
import com.jojoz.gh.entity.User;
import com.jojoz.gh.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger= LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public GHRsult<UserVO> loginUser(HttpServletRequest req,
			HttpServletResponse resp, User user) {
		UserVO uvo = new UserVO();
		if (null == user) {
			logger.info("data is null");
			return new GHRsult<UserVO>(false, "data is null");
		}
		if (null == user.getUsername() || user.getUsername().equals("")) {
			logger.info("username is null");
			return new GHRsult<UserVO>(false, "username is null");
		}
		if (null == user.getPassword() || user.getPassword().equals("")) {
			logger.info("password is null");
			return new GHRsult<UserVO>(false, "password is null");
		}
		try {
			User u = userService.login(user.getUsername(), user.getPassword());
			if (null == u) {
				logger.info("username or password is error");
				return new GHRsult<UserVO>(false,
						"username or password is error");
			}
			uvo.setState(u.getState());
			uvo.setToken(u.getToken());
			uvo.setUsername(u.getUsername());
			uvo.setProduceDivision(u.getProduceDivision());
			// resp.sendRedirect("index.html");
			logger.info("result true ");

			return new GHRsult<UserVO>(true, uvo);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			return new GHRsult<UserVO>(false, e.getMessage());
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public GHRsult<Integer> register(HttpServletRequest req,
			HttpServletResponse resp, User user) {

		if (null == user) {
			logger.info("data is null");
			return new GHRsult<Integer>(false, "data is null");
		}
		if (null == user.getUsername() || user.getUsername().equals("")) {
			logger.info("username is null");
			return new GHRsult<Integer>(false, "username is null");
		}
		if (null == user.getPassword() || user.getPassword().equals("")) {
			logger.info("password is null");
			return new GHRsult<Integer>(false, "password is null");
		}
		try {
			boolean bool = userService.existUsername(user.getUsername());
			if (bool) {
				logger.info("username is exist");
				return new GHRsult<Integer>(false, "username is exist");
			}
			int result = userService.registUser(user.getUsername(),
					user.getPassword(), 1, "");
			if (result == 0) {
				logger.info("registor fail");
				return new GHRsult<Integer>(false, "registor fail");
			}

			return new GHRsult<Integer>(true, result);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return new GHRsult<Integer>(false, e.getMessage());
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public GHRsult<Integer> addUser(@CookieValue(value = "user", required = false) String userStr,User user) {
		User u = userService.userValidate(userStr);
		if (null == u) {
			logger.info("need login");
			return new GHRsult<Integer>(false, "need login");
		}
		// 没有权限
		if (u.getState() <2) {
			logger.info("no permission");
			return new GHRsult<Integer>(false, "no permission");
		}
		if (null == user) {
			logger.info("data is null");
			return new GHRsult<Integer>(false, "data is null");
		}
		if (null == user.getUsername() || user.getUsername().equals("")) {
			logger.info("username is null");
			return new GHRsult<Integer>(false, "username is null");
		}
		if (null == user.getPassword() || user.getPassword().equals("")) {
			logger.info("password is null");
			return new GHRsult<Integer>(false, "password is null");
		}
		try {
			boolean bool = userService.existUsername(user.getUsername());
			if (bool) {
				logger.info("username is exist");
				return new GHRsult<Integer>(false, "username is exist");
			}
			int result = userService.registUser(user.getUsername(),
					user.getPassword(), user.getState(),
					user.getProduceDivision());
			if (result == 0) {
				logger.info("registor fail");
				return new GHRsult<Integer>(false, "registor fail");
			}

			return new GHRsult<Integer>(true, result);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return new GHRsult<Integer>(false, e.getMessage());
		}
	}

	/**
	 * 获取union关键字段列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public UnionRsult<Object[]> query(
			@CookieValue(value = "user", required = false) String userStr,
			String words, Integer page, Integer rows, String sort, String order) {
		User user = userService.userValidate(userStr);
		if (null == user) {
			logger.info("need login");
			return null;
		}
		// 没有权限
		if (user.getState() <2) {
			logger.info("no permission");
			return null;
		}
		if (null == page || null == rows) {
			page = 1;
			rows = 20;
		}
		Object[] uvo = userService.getUserList(words, rows, page, sort, order)
				.toArray();
		int count = userService.getCount(words);
		return new UnionRsult<Object[]>(count, uvo);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public UserVO getUserById(
			@CookieValue(value = "user", required = false) String userStr,
			@PathVariable("id") String id) {
		User user = userService.userValidate(userStr);
		if (null == user) {
			logger.info("need login");
			return null;
		}
		// 没有权限
		if (user.getState() <2) {
			logger.info("no permission");
			return null;
		}
		return userService.getUserById(id);

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public GHRsult<Integer> updateUser(
			@CookieValue(value = "user", required = false) String userStr,
			User user1) {

		User u = userService.userValidate(userStr);
		if (null == u) {
			logger.info("no permission");
			return new GHRsult<Integer>(false, "need login");
		}
		// 没有权限
		if (u.getState() <2) {
			logger.info("no permission");
			return new GHRsult<Integer>(false, "no permission");
		}

		if (null == user1) {
			logger.info("data is null");
			return new GHRsult<Integer>(false, "data is null");
		}
		
		try {
			
			int result = userService.updateUser(user1);
			if (result == 0) {
				logger.info("update fail");
				return new GHRsult<Integer>(false, "registor fail");
			}
			return new GHRsult<Integer>(true, result);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			return new GHRsult<Integer>(false, e.getMessage());
		}
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public GHRsult<Integer> updateUser(
			@CookieValue(value = "user", required = false) String userStr,
			String id) {
		User u = userService.userValidate(userStr);
		if (null == u) {
			return new GHRsult<Integer>(false, "need login");
		}
		// 没有权限
		if (u.getState() <2) {
			return new GHRsult<Integer>(false, "no permission");
		}
		try {
			int result = userService.delUser(id);
			if (result == 0) {
				logger.error("delete fail id:"+id);
				return new GHRsult<Integer>(false, "delete fail");
			}
			return new GHRsult<Integer>(true, result);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return new GHRsult<Integer>(false, e.getMessage());
		}
	}

}
