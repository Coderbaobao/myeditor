package com.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.bean.Note;
import com.it.dao.NoteDao;

@Service()
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	private NoteDao noteDao;

	@Override
	public int addNote(Note note) {
		return noteDao.add(note);
	}

	@Override
	public int deleteNote(String id) {
		return noteDao.delete(id);
	}

	@Override
	public int updateNote(Note note) {
		return noteDao.update(note);
	}

	@Override
	public Note findById(String id) {
		return noteDao.findById(id);
	}

}
