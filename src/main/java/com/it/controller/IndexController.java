package com.it.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
		
	@RequestMapping("/login")
	public String login() {
		
		return "thymeleaf/login";
	}
	
	@RequestMapping("/Meditor")
	public String index() {
	
		return "thymeleaf/Meditor";
	}
	
}
