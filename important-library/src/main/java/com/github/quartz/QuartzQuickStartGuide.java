package com.github.quartz;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see http://www.quartz-scheduler.org/overview/quick-start
 * @see http://www.quartz-scheduler.org/generated/2.2.1/html/qs-all/#page/Quartz_Scheduler_Documentation_Set%2Fre-exp_example1.html%23
 * @author doctor
 *
 * @time   2015年2月2日 上午11:41:08
 */
public class QuartzQuickStartGuide {

	public static void main(String[] args) {

		try {
			Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
			
			JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
					   .withIdentity(UUID.randomUUID().toString(),"jobs")
					   .build();
			
			Trigger trigger = TriggerBuilder.newTrigger()
							  .withIdentity(UUID.randomUUID().toString(), "tiggers")
							  .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
							  .startNow()
					          .build();
			defaultScheduler.scheduleJob(jobDetail, trigger);
			defaultScheduler.start();
			
			TimeUnit.MINUTES.sleep(2);
			defaultScheduler.shutdown(true);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static class HelloJob implements Job{
		private static final Logger log = LoggerFactory.getLogger(HelloJob.class);
		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException {
			log.info("helloJob! " + LocalDateTime.now());
			
		}
		
	}
}
