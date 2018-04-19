package com.it.bean;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 通用响应类
 * @author UYABOOK
 *
 */
public class Response<T> {
	
	private Integer success;
	private String message;
	@JsonInclude(Include.NON_NULL)
	private T data;
	@JsonInclude(Include.NON_NULL)
	private List<Map<String, Object>> listMap;
	@JsonInclude(Include.NON_NULL)
	private List<T> list;
	
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<Map<String, Object>> getListMap() {
		return listMap;
	}
	public void setListMap(List<Map<String, Object>> listMap) {
		this.listMap = listMap;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

	
}
