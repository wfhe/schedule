package com.example.job;

import org.springframework.stereotype.Component;

import com.example.config.Job;

@Component
public class TestJob {

//	@Job(cron={"0/20 * * * * ?","15 0/2 * * * ?"})
	public void test(){
		System.out.println("test job------------");
	}
}
