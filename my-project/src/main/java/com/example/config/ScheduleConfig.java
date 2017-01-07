package com.example.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.job.JobExec;
import com.example.job.JobManager;
import com.example.listener.MyListenerProcessor;

@Configuration
@ConditionalOnClass(Scheduler.class)
@EnableConfigurationProperties(JobProperties.class)
public class ScheduleConfig {

	private JobProperties jobProperties;
	
	public ScheduleConfig(JobProperties jobProperties){
		this.jobProperties=jobProperties;
	}
	
	@Bean
	public MyListenerProcessor beanProcess(){
		return new MyListenerProcessor();
	}
	
	@Bean
	public Scheduler getSchedule(){
		SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = null;
		try {
			sched = sf.getScheduler();
			sched.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	    return sched;
	}
	
	@Bean
	public JobManager createJobManager(){
		JobManager manager = new JobManager(getSchedule());
		manager.addJobs(jobProperties.getJobs());
		return manager;
	}
	
	@Bean
	public JobExec execJob(){
		JobExec exec = new JobExec(createJobManager());
		return exec;
	}
}
