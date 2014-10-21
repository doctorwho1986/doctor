package com.github.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzPractice {

	public static void main(String[] args) throws SchedulerException {
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		
		JobDetail jobDetail = JobBuilder.newJob(HelloQuartz.class).withIdentity("HelloQuartz", "QuartzPractice-group").build();
		SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().startNow().withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(10, 1)).build();
		
		scheduler.scheduleJob(jobDetail, simpleTrigger);
		
	}

	
}

