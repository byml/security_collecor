package net.ibyml.security.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import net.ibyml.security.ApplicationAware;
import net.ibyml.sse.collector.SseMarketPriceCollector;

public class SseCollectJob implements Job {
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		System.out.println("SseCollectJob Execute:______");
		SseMarketPriceCollector collector = (SseMarketPriceCollector) ApplicationAware
				.getBean("sseZqhqHistoryCollector");
		collector.collecteData();
	}

}
