/* 
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */
 
package com.example.job;

import static org.quartz.DateBuilder.evenMinuteDate;

import java.lang.reflect.Method;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.config.ScheduleJob;

/**
 * <p>
 * This is just a simple job that says "Hello" to the world.
 * </p>
 * 
 * @author Bill Kratzer
 */
public class HelloJob implements Job {

    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() {
    }

  
    public void execute(JobExecutionContext context)
        throws JobExecutionException {

    	Date runTime = evenMinuteDate(new Date());
    	JobDetail jobDetail = context.getJobDetail();
    	_log.info(jobDetail.getKey() + " run at: " + runTime);
    	ScheduleJob job = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
    	Object bean = JobManager.getBean(job.getBeanName());
    	Class<? extends Object> claz = bean.getClass();
    	try {
    		Class<?>[] p= new Class<?>[]{};
			Method method = claz.getMethod(job.getMethod(), p);
			Object[] o= new Object[]{};
			method.invoke(bean, o);
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }

}
