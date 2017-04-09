package net.ibyml.szse.collector;

import org.junit.Test;

import net.ibyml.security.CollectorSetting;

public class ZqhqHistoryCollectorTest {

	@Test
	public void testCollecte() {
		CollectorSetting collectorSetting = new CollectorSetting(CollectorSetting.HOST_SZSE,"d:\\temp2\\hangqing");
		ZqhqHistoryCollector collector = new ZqhqHistoryCollector(collectorSetting);
		collector.collecteData();
	}

}
