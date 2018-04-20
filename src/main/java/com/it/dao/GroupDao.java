package com.it.dao;
import java.util.List;

import com.it.bean.Group;

public interface GroupDao {
	int add(Group group);
	
	int delete(String id);
	
	int update(Group group);
	
	Group findById(String id);
	
	List<Group>findAll(String id);
}
