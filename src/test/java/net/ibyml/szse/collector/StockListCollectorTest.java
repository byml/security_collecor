package net.ibyml.szse.collector;

import org.junit.Test;

import net.ibyml.security.CollectorSetting;

public class StockListCollectorTest {

	@Test
	public void testCollecte() {
		CollectorSetting collectorSetting = new CollectorSetting(CollectorSetting.HOST_SZSE,"d:\\temp2");
		StockListCollector collector = new StockListCollector(collectorSetting);
		collector.collecteData();
	}

}
