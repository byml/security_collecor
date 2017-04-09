package net.ibyml.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.ibyml.szse.collector.ZqhqHistoryCollector;

@Configuration
public class SzseCollectJobConfigration {

	@Value("${szse.file.location}")
	private String fileLocation;


	@Bean(name="szseZqhqHistoryCollector")
	public ZqhqHistoryCollector getZqhqHistoryCollector() {
		CollectorSetting collectorSetting = new CollectorSetting(CollectorSetting.HOST_SZSE, fileLocation);
		ZqhqHistoryCollector collector = new ZqhqHistoryCollector(collectorSetting);
		return collector;
	}
}
