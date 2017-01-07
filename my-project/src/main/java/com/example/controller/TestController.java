package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.AppConfig;

@RestController
public class TestController {

	@Autowired
	private AppConfig appConfig;
//	@Autowired
//	private MongoTemplate mongoTemplate;
	
	@RequestMapping("/test")
	public AppConfig test(){
		AppConfig rs = new AppConfig();
		BeanUtils.copyProperties(appConfig, rs);
		return rs;
	}
	
	/*@RequestMapping("/test1")
	public Boolean test1(){
		
		return mongoTemplate.collectionExists("test_collection");
	}*/
}
