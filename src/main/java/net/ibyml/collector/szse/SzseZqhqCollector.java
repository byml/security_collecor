package net.ibyml.collector.szse;

import java.net.URISyntaxException;

import net.ibyml.common.http.client.FileDownloadSetting;
import net.ibyml.common.http.client.FileDownloader;
import net.ibyml.common.http.client.SimpleFileDownloader;

public class SzseZqhqCollector {
	private FileDownloader downloader;

	public SzseZqhqCollector() {
		SzseZqhqProcessor processor = new SzseZqhqProcessor();
		FileDownloadSetting setting = new SzseZqhqFileDownloadSetting(processor);
		try {
			downloader = new SimpleFileDownloader(setting, processor, null);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void collecteData() {
		downloader.download();
	}
}
