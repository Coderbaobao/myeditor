	package com.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.it.dao")
public class MarkdownApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MarkdownApplication.class, args);
	}		
}
