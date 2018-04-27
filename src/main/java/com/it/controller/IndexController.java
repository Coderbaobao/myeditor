package com.it.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
		
	@RequestMapping("/login")
	public String login() {
		
		return "thymeleaf/login";
	}
	
	@RequestMapping("/register")
	public String register() {
		
		return "thymeleaf/register";
	}
	
	@RequestMapping("/mdEditor")
	public String index() {
	
		return "thymeleaf/mdEditor";
	}
	
	@RequestMapping("/imageUpload")
	public String ImageUpload() {
	
		return "thymeleaf/imageUpload";
	}
	
}
