package com.example.config;


public class ScheduleJob {
	
	private String beanName;
	private String method;
	private String cron;
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	@Override
	public String toString() {
		return "ScheduleJob [beanName=" + beanName + ", method=" + method + ", cron=" + cron + "]";
	}
	
}
