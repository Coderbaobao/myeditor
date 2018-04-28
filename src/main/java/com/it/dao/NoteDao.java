package com.it.dao;

import com.it.bean.Note;

public interface NoteDao {

	int add(Note note);
	
	int delete(String noteId);
	
	int deleteByGroupId(String groupId);
	
	int update(Note note);
	
	int updateGroupId(Note note);
	
	Note findById(String noteId);
	
}
