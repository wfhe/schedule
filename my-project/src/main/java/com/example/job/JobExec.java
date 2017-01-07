package com.example.job;

import org.springframework.boot.CommandLineRunner;


public class JobExec implements CommandLineRunner{

	
	private JobManager jobManager;
	
	public JobExec(JobManager jobManager){
		this.jobManager=jobManager;
	}
	
	@Override
	public void run(String... args) throws Exception {
		jobManager.run();
	}

}
