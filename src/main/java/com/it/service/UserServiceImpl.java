package com.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.bean.User;
import com.it.dao.UserDao;

@Service()
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public int addUser(User user) {
		return userDao.add(user);
	}

	@Override
	public int deleteUser(String id) {
		return userDao.delete(id);
	}

	@Override
	public int updateUser(User user) {
		return userDao.update(user);
	}

	@Override
	public int findById(String name) {
		return userDao.findById(name);
	}

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

}
