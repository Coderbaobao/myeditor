package com.it.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.bean.Group;
import com.it.dao.GroupDao;

@Service()
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupDao groupDao;
	
	@Override
	public List<Group> findAll() {
		return groupDao.findAll();
	}

	@Override
	public int addGroup(Group group) {
		return groupDao.add(group);
	}

	@Override
	public int deleteGroup(String id) {
		return groupDao.delete(id);
	}

	@Override
	public int updateGroup(Group group) {
		return groupDao.update(group);
	}

	@Override
	public Group findById(String id) {
		return groupDao.findById(id);
	}	

}
