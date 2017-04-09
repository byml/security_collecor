package net.ibyml.collector.szse;

import org.junit.Before;
import org.junit.Test;

import net.ibyml.collector.szse.SzseZqhqCollector;
import net.ibyml.common.http.client.GlobalSetting;

public class SzseZqhqCollectorTest {
	@Before
	public void setup() {
		GlobalSetting.setDownloadFileHomeLocation("d:\\temp6");
	}

	@Test
	public void testCollecteData() {
		SzseZqhqCollector collector = new SzseZqhqCollector();
		collector.collecteData();
	}

}
