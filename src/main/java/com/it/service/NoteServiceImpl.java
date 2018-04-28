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
	public int deleteNote(String noteId) {
		return noteDao.delete(noteId);
	}

	@Override
	public int updateNote(Note note) {
		return noteDao.update(note);
	}

	@Override
	public Note findById(String noteId) {
		return noteDao.findById(noteId);
	}

	@Override
	public int MoveNote(Note note) {
		return noteDao.updateGroupId(note);
	}

	@Override
	public int deleteByGroupId(String groupId) {
		return noteDao.deleteByGroupId(groupId);
	}

}
