package com.it.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String userId;
	@JsonInclude(Include.NON_NULL)
	private String userName;
	@JsonInclude(Include.NON_NULL)
	private String userPaw;
	@JsonInclude(Include.NON_NULL)
	private String userPic;
	private int status;
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
	private Date createTime;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPaw() {
		return userPaw;
	}
	public void setUserPaw(String userPaw) {
		this.userPaw = userPaw;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	public int isStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
