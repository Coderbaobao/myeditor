package com.it.dao;

import com.it.bean.User;

public interface UserDao {
	int add(User user);
	
	int delete(String id);
	
	int update(User user);
	
	User login(User user);
	
	int findById(String name);
}
