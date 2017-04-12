package net.ibyml.security.collector.szse;

import org.junit.Before;
import org.junit.Test;

import net.ibyml.common.http.client.GlobalSetting;
import net.ibyml.security.collector.szse.SzseZqhqCollector;

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
