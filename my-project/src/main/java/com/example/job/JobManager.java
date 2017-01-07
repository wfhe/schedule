package com.example.job;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.example.config.ScheduleJob;
import com.example.util.ContextUtils;


public class JobManager implements ApplicationContextAware{

	Logger log = LoggerFactory.getLogger(JobManager.class);
	private Scheduler sched;
	private static List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
	
	public JobManager(Scheduler sched){
		this.sched=sched;
	}
	
	public void addJobs(List<ScheduleJob> jobs){
		jobList.addAll(jobs);
	}
	
	public static void addJob(ScheduleJob job){
		System.out.println("job:"+job);
		jobList.add(job);
	}
	
	public void run() throws Exception {
		int count = 0;
	    for (ScheduleJob scheduleJob : jobList) {
	    	log.info("------- Scheduling Job  -------------------"+scheduleJob.getBeanName());
	    	System.out.println(scheduleJob);
	    	addJob(scheduleJob,count);
	    	count++;
		}
	}

	private void addJob(ScheduleJob scheduleJob,int count) throws SchedulerException {
		// computer a time that is on the next round minute
	    Date runTime = evenMinuteDate(new Date());

	    // define the job and tie it to our HelloJob class
	    JobDetail job = newJob(HelloJob.class).withIdentity("job"+count, "group"+count).build();

	    job.getJobDataMap().put("scheduleJob", scheduleJob);
	    // Trigger the job to run on the next round minute
	    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob
				.getCron());
	    Trigger trigger = newTrigger().withIdentity("trigger"+count, "group"+count).withSchedule(scheduleBuilder).build();

	    // Tell quartz to schedule the job using our trigger
	    sched.scheduleJob(job, trigger);
	    log.info(job.getKey() + " will run at: " + runTime);
	}

	public static ApplicationContext context = null;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		JobManager.context=context;
		ContextUtils.setContext(context);
	}
	
	public static Object getBean(String name){
		return context.getBean(name);
	}
}
