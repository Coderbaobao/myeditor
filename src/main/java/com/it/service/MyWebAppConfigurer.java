package com.it.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport{

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		//和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        //上传的图片在D盘下的temp目录下，访问路径如：http://localhost:8080/pic/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中pic表示访问的前缀。"file:E:/temp/"是文件真实的存储路径
        registry.addResourceHandler("/pic/**").addResourceLocations("file:/home/ubuntu/images/");
//        registry.addResourceHandler("/pic/**").addResourceLocations("file:D:/temp/");
	}
	
}
