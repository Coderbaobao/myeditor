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
	public List<Group> findAll(String noteId) {
		return groupDao.findAll(noteId);
	}

	@Override
	public int addGroup(Group group) {
		return groupDao.add(group);
	}

	@Override
	public int deleteGroup(String groupId) {
		return groupDao.delete(groupId);
	}

	@Override
	public int updateGroup(Group group) {
		return groupDao.update(group);
	}

	@Override
	public Group findById(String groupId) {
		return groupDao.findById(groupId);
	}	

}
