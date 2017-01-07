package com.example.listener;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.example.config.Job;
import com.example.config.ScheduleJob;
import com.example.job.JobManager;
import com.example.util.ContextUtils;


public class MyListenerProcessor implements BeanPostProcessor{

	@Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
 
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        if (methods != null) {
            for (Method method : methods) {
            	Job job = AnnotationUtils.findAnnotation(method, Job.class);
                if(job!=null){
                	String[] cron = job.cron();
                	for (String c : cron) {
                		ScheduleJob sjob = new ScheduleJob();
                		sjob.setBeanName(ContextUtils.getBeanName(bean));
                		sjob.setCron(c);
                		sjob.setMethod(method.getName());
                		JobManager.addJob(sjob);
					}
                }
            }
        }
        return bean;
    }
}
