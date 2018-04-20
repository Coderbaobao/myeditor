package com.it.service;
import java.util.List;

import com.it.bean.Group;

public interface GroupService {
	int addGroup(Group group);

	int deleteGroup(String id);

	int updateGroup(Group group);
	
    List<Group> findAll(String id);
    
    Group findById(String id);
}
