package com.it.controller;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.bean.Note;
import com.it.bean.Response;
import com.it.service.NoteServiceImpl;

@RestController
@RequestMapping("/note")
public class NoteController {
	
	@Autowired
	private NoteServiceImpl noteService;
	
	@RequestMapping(value = "/addNote", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Response<Note> addNote(@RequestBody Note note){
		Response<Note> createNote = new Response<Note>();
		if (note != null) {
			Date day = new Date();
			note.setCreateTime(day);
			int result = noteService.addNote(note);
			if (result > 0) {
				createNote.setSuccess(1);
				createNote.setMessage("创建成功！");
			} else {
				createNote.setSuccess(0);
				createNote.setMessage("创建失败！");
			}
		} else {
			createNote.setSuccess(0);
			createNote.setMessage("创建失败！");
		}
		return createNote;
	}
	@RequestMapping(value = "/saveNote", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Response<Note> saveNote(@RequestBody Note newNote){
		Note note = noteService.findById(newNote.getNoteId());
		Response<Note> createNote = new Response<Note>();
		Date day = new Date();
		newNote.setModifyTime(day);
		if(note != null) {
			int result = noteService.updateNote(newNote);
			if (result > 0) {
				createNote.setSuccess(1);
				createNote.setMessage("更新成功！");
			} else {
				createNote.setSuccess(0);
				createNote.setMessage("更新失败！");
			}
		}else {
			createNote.setSuccess(0);
			createNote.setMessage("更新失败！");
		}
		return createNote;
	}
	@RequestMapping(value = "/findById", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Response<Note> findById(String noteId){
		Note note = noteService.findById(noteId);
		Response<Note> createNote = new Response<Note>();
		if (note != null) {
			createNote.setSuccess(1);
			createNote.setMessage("查询成功！");
			createNote.setData(note);
		} else {
			createNote.setSuccess(0);
			createNote.setMessage("查询失败！");
		}
		return createNote;
	}
	
	@RequestMapping(value = "/moveNote", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Response<Note> moveNote(@RequestBody Note newNote){
		Note note = noteService.findById(newNote.getNoteId());
		Response<Note> createNote = new Response<Note>();
		if(note != null) {
			int result = noteService.MoveNote(newNote);
			if(result > 0) {
				createNote.setSuccess(1);
				createNote.setMessage("移动成功！");
				createNote.setData(note);
			}else {
				createNote.setSuccess(0);
				createNote.setMessage("移动失败！");
			} 
		}else {
			createNote.setSuccess(0);
			createNote.setMessage("移动失败！");
		}
		
		return createNote;
	}
	
	@RequestMapping(value = "/delNotebyId", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Response<Note> delNoteById(String noteId){
		Note note = noteService.findById(noteId);
		Response<Note> createNote = new Response<Note>();
		if (note != null) {
			int de = noteService.deleteNote(noteId);
			if(de > 0) {
				createNote.setSuccess(1);
				createNote.setMessage("删除成功！");
			}else {
				createNote.setSuccess(0);
				createNote.setMessage("查询失败！");
			}
			
		} else {
			createNote.setSuccess(0);
			createNote.setMessage("查询失败！");
		}
		return createNote;
	}
	
}
