package com.it.service;

import com.it.bean.User;

public interface UserService {
	int addUser(User user);
	
	int deleteUser(String id);
	
	int updateUser(User user);
	
	User login(User user);
	
	int findById(String name);
}
