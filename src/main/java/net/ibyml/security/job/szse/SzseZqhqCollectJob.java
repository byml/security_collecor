package net.ibyml.security.job.szse;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import net.ibyml.security.collector.szse.SzseZqhqCollector;

public class SzseZqhqCollectJob implements Job {
	private SzseZqhqCollector collector;

	public SzseZqhqCollectJob() {
		collector = new SzseZqhqCollector();
	}

	public void execute(JobExecutionContext jec) throws JobExecutionException {
		collector.collecteData();
	}

}
