package net.ibyml.collector.sse;

import org.junit.Before;
import org.junit.Test;

import net.ibyml.collector.sse.SseMarketPriceCollector;
import net.ibyml.common.http.client.FileDownloadSetting;
import net.ibyml.common.http.client.GlobalSetting;
import net.ibyml.common.http.client.SimpleFileDownloadSetting;

public class SseMarketPriceCollectorTest {
	@Before
	public void setup() {
		GlobalSetting.setDownloadFileHomeLocation("d:\\temp6");
	}

	@Test
	public void testCollecteData() {
		FileDownloadSetting setting = new SimpleFileDownloadSetting(
				"http://yunhq.sse.com.cn:32041/v1/sh1/list/exchange/equity?callback=jQuery111209115037098085547_1488103518194&select=code%2Cname%2Clast%2Copen%2Chigh%2Clow%2Cprev_close%2Cchg_rate%2Cvolume%2Camount%2Cchange%2Camp_rate%2Ctradephase&order=&begin=0&end=10000",
				"hangqing/shanghai", null);
		 SseMarketPriceCollector collector = new  SseMarketPriceCollector(setting);
		 collector.collecteData();
	}

}
