package com.github.quartz;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloQuartz implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(LocalDateTime.now() + "  " + getClass().getName() + "execute");
	}
	
}
