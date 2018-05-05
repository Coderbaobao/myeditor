package com.it.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.bean.Response;
import com.it.bean.User;
import com.it.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class LoginController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value = "/login",method = RequestMethod.POST,produces="application/json;charset=utf-8" )
	@ResponseBody
	public Response<User> login(@RequestBody User user){
		Response<User> create = new Response<User>();
		if(user != null) {
			User userinfo = userService.login(user);
			if(userinfo != null ) {
				create.setSuccess(1);
				create.setMessage("登陆成功！");
				create.setData(userinfo);
			} else {
				create.setSuccess(0);
				create.setMessage("登陆失败！");
			}
		}else {
			create.setSuccess(0);
			create.setMessage("登陆失败！");
		}
		return create;
	}
	
	/*@RequestMapping(value = "/register",method = RequestMethod.POST,produces="application/json;charset=utf-8" )
	@ResponseBody
	public Response<User> register(@RequestBody User user){
		Response<User> create = new Response<User>();
		if(user != null) {
			Date day = new Date();
			user.setCreateTime(day);
			user.setUserId(UUID.randomUUID().toString());
			int add = userService.addUser(user);
			if(add > 0) {
				create.setSuccess(1);
				create.setMessage("注册成功！");
				create.setData(user);
			} else {
				create.setSuccess(0);
				create.setMessage("注册失败！");
			}
		}else {
			create.setSuccess(0);
			create.setMessage("注册失败！");
		}
		return create;
	}*/
	
	
}
