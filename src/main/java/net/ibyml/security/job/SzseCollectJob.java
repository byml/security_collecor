package net.ibyml.security.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import net.ibyml.security.ApplicationAware;
import net.ibyml.szse.collector.ZqhqHistoryCollector;

public class SzseCollectJob implements Job {
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		ZqhqHistoryCollector collector = (ZqhqHistoryCollector) ApplicationAware.getBean("szseZqhqHistoryCollector");
		collector.collecteData();
	}

}
