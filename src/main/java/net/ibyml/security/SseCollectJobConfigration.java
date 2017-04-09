package net.ibyml.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.ibyml.sse.collector.SseMarketPriceCollector;

@Configuration
public class SseCollectJobConfigration {

	@Value("${sse.file.location}")
	private String sseFileLocation;

	@Bean(name = "sseZqhqHistoryCollector")
	public SseMarketPriceCollector getZqhqHistoryCollector() {
		CollectorSetting collectorSetting = new CollectorSetting(CollectorSetting.HOST_SSE, sseFileLocation);
		SseMarketPriceCollector collector = new SseMarketPriceCollector(collectorSetting);
		return collector;
	}
}
