package com.it.dao;
import java.util.List;

import com.it.bean.Group;

public interface GroupDao {
	int add(Group group);
	
	int delete(String groupId);
	
	int update(Group group);
	
	Group findById(String groupId);
	
	List<Group>findAll(String noteId);
}
