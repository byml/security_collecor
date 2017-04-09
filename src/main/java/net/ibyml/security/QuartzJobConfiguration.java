package net.ibyml.security;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.ibyml.security.job.SseCollectJob;
import net.ibyml.security.job.SzseCollectJob;

@SpringBootApplication
public class QuartzJobConfiguration {

	public static void main(String[] args) {
		try {
			String shCron;
			String szCron;

			if (args.length == 0) {
				shCron = "0 0,30 9-20 ? * MON-FRI";
				szCron = "0 0,30 9-20 ? * MON-FRI";
			} else if (args.length == 1) {
				shCron = args[0];
				szCron = "0 0,30 9-20 ? * MON-FRI";
			} else {
				shCron = args[0];
				szCron = args[1];
			}
			System.out.println("________________________________________________");
			System.out.println("shCron:" + shCron);
			System.out.println("szCron:" + szCron);

			SpringApplication.run(QuartzJobConfiguration.class, args);

			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// and start it off
			scheduler.start();

			JobDetail sseJob = newJob(SseCollectJob.class).withIdentity("job1", "group1").build();

			Trigger sseTrigger = newTrigger().withIdentity("trigger1", "group1").startNow()
					.withSchedule(CronScheduleBuilder.cronSchedule(shCron)).build();			

			// Tell quartz to schedule the job using our trigger
			scheduler.scheduleJob(sseJob, sseTrigger);
			
			
			JobDetail szseJob = newJob(SzseCollectJob.class).withIdentity("job2", "group2").build();

			Trigger szseTrigger = newTrigger().withIdentity("trigger2", "group2").startNow()
					.withSchedule(CronScheduleBuilder.cronSchedule(szCron)).build();			

			// Tell quartz to schedule the job using our trigger
			scheduler.scheduleJob(szseJob, szseTrigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}