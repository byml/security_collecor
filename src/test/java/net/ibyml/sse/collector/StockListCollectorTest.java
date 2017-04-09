package net.ibyml.sse.collector;

import static org.junit.Assert.*;

import org.junit.Test;

import net.ibyml.security.CollectorSetting; 

public class StockListCollectorTest {

 
	
	@Test
	public void testCollecte() {
		CollectorSetting collectorSetting = new CollectorSetting(CollectorSetting.HOST_SSE,"d:\\temp3");
		StockListCollector collector = new StockListCollector(collectorSetting);
		collector.collecteData();
	}


}
