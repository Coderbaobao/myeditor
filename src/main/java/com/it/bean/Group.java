package com.it.bean;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String groupId;
	private String groupName;
	
	private List<Note> notes; 
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
}
