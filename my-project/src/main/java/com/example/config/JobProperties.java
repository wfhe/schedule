package com.example.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="my")
@Configuration
public class JobProperties {

	private List<ScheduleJob> jobs = new ArrayList<ScheduleJob>();

	public List<ScheduleJob> getJobs() {
		return jobs;
	}
}
