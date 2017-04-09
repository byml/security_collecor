package net.ibyml.security;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import net.ibyml.common.http.client.GlobalSetting;
import net.ibyml.security.job.sse.SseMarketPriceCollectJob;
import net.ibyml.security.job.szse.SzseZqhqCollectJob;

public class SecurityDataCollecteApplication {
	public static void main(String[] args) {
		Properties configProperties = new Properties();
		FileInputStream fis;
		try {
			URI uri = ClassLoader.getSystemResource("config.properties").toURI();
			fis = new FileInputStream(new File(uri));

			configProperties.load(fis);

			GlobalSetting.setDownloadFileHomeLocation(configProperties.getProperty("download.file.location"));			

			String shCron;
			String szCron;
			if (args.length == 0) {
				shCron = configProperties.getProperty("sse.cron");
				szCron = configProperties.getProperty("szse.cron");
			} else if (args.length == 1) {
				shCron = args[0];
				szCron = configProperties.getProperty("szse.cron");
			} else {
				shCron = args[0];
				szCron = args[1];
			}
			System.out.println("________________________________________________");
			System.out.println("shCron:" + shCron);
			System.out.println("szCron:" + szCron);

			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			// and start it off
			scheduler.start();

			m1(scheduler, shCron);
			m2(scheduler, szCron);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void m1(Scheduler scheduler, String shCron) throws SchedulerException {
		JobDetail sseJob = newJob(SseMarketPriceCollectJob.class).withIdentity("job1", "group1").build();

		Trigger sseTrigger = newTrigger().withIdentity("trigger1", "group1").startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule(shCron)).build();

		// Tell quartz to schedule the job using our trigger
		scheduler.scheduleJob(sseJob, sseTrigger);
	}

	private static void m2(Scheduler scheduler, String szCron) throws SchedulerException {
		JobDetail szseJob = newJob(SzseZqhqCollectJob.class).withIdentity("job2", "group2").build();

		Trigger szseTrigger = newTrigger().withIdentity("trigger2", "group2").startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule(szCron)).build();

		// Tell quartz to schedule the job using our trigger
		scheduler.scheduleJob(szseJob, szseTrigger);
	}
}
