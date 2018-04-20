package com.it.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it.bean.Group;
import com.it.bean.Response;
import com.it.service.GroupServiceImpl;

@Controller
@RequestMapping("/group")
public class GroupController {
	
	@Autowired
	private GroupServiceImpl groupService;
	
	@RequestMapping(value = "/findGroupAll", method = RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Response<Group> findGroupAll(String id){
		List<Group> list = groupService.findAll(id);
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
	
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST,produces="application/json;charset=utf-8")
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
	
	@RequestMapping(value = "/saveGroup", method = RequestMethod.POST,produces="application/json;charset=utf-8")
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
	
	@RequestMapping(value = "/delGroupbyId", method = RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Response<Group> delNoteById(String id){
		Group group = groupService.findById(id);
		Response<Group> createNote = new Response<Group>();
		if (group != null) {
			int de = groupService.deleteGroup(id);
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
