package com.it.dao;

import com.it.bean.Note;

public interface NoteDao {

	int add(Note note);
	
	int delete(String id);
	
	int update(Note note);
	
	Note findById(String id);
	
}
