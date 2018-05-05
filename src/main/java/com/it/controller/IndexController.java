package com.it.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
		
	@RequestMapping(value="/login")
	public String login() {
		
		return "thymeleaf/login";
	}
	
	/*
	@RequestMapping(value="/register")
	public String register() {
		
		return "thymeleaf/register";
	}*/
	
	@RequestMapping(value="/mdEditor")
	public String index() {
	
		return "thymeleaf/mdEditor";
	}
	
	@RequestMapping(value="/imageUpload")
	public String ImageUpload() {
	
		return "thymeleaf/imageUpload";
	}
	
}
