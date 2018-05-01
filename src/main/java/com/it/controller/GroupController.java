package com.it.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.bean.Group;
import com.it.bean.Response;
import com.it.service.GroupServiceImpl;
import com.it.service.NoteServiceImpl;
@RestController
@RequestMapping("/group")
public class GroupController {
	
	@Autowired
	private GroupServiceImpl groupService;
	@Autowired
	private NoteServiceImpl noteService;
	
	@RequestMapping(value = "/findGroupAll", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Response<Group> findGroupAll(String noteId){
		List<Group> list = groupService.findAll(noteId);
		Response<Group> create = new Response<Group>();
		if (list != null && list.size() > 0) {
			create.setSuccess(1);
			create.setMessage("查询成功！");
			create.setList(list);
		} else {
			create.setSuccess(0);
			create.setMessage("查询失败！");
		}
		return create;
	}
	
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Response<Group> AddGroup(@RequestBody Group group){
		int addGroup = groupService.addGroup(group);
		Response<Group> create = new Response<Group>();
		if (addGroup  > 0) {
			create.setSuccess(1);
			create.setMessage("创建成功！");
		} else {
			create.setSuccess(0);
			create.setMessage("创建失败！");
		}
		return create;
	}
	
	@RequestMapping(value = "/saveGroup", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Response<Group> saveGroup(@RequestBody Group newGroup){
		Group group = groupService.findById(newGroup.getGroupId());
		Response<Group> create = new Response<Group>();
		if(group != null) {
			int result = groupService.updateGroup(newGroup);
			if (result > 0) {
				create.setSuccess(1);
				create.setMessage("更新成功！");
			} else {
				create.setSuccess(0);
				create.setMessage("更新失败！");
			}
		}else {
			create.setSuccess(0);
			create.setMessage("更新失败！");
		}
		return create;
	}
	
	@RequestMapping(value = "/delGroupbyId", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Response<Group> delNoteById(String groupId){
		Group group = groupService.findById(groupId);
		Response<Group> createNote = new Response<Group>();
		if (group != null) {
			int de = groupService.deleteGroup(groupId);
			int de2 = noteService.deleteByGroupId(groupId);
			if(de > 0 || de2>0) {
				createNote.setSuccess(1);
				createNote.setMessage("删除成功！");
			}else {
				createNote.setSuccess(0);
				createNote.setMessage("删除失败！");
			}
			
		} else {
			createNote.setSuccess(0);
			createNote.setMessage("删除失败！");
		}
		return createNote;
	}
	
}
