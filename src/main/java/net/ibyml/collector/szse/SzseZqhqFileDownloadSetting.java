package net.ibyml.collector.szse;

import net.ibyml.common.http.client.FileDownloadSetting;

public class SzseZqhqFileDownloadSetting implements FileDownloadSetting {
	private SzseZqhqProcessor processor;

	private String url = "http://www.szse.cn/szseWeb/ShowReport.szse?SHOWTYPE=xlsx&CATALOGID=1815_stock&ENCODE=1&TABKEY=tab1";
	private String dirLocation = "hangqing/shenzhen";

	public SzseZqhqFileDownloadSetting(SzseZqhqProcessor processor) {
		this.processor = processor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDirLocation() {
		return dirLocation;
	}

	public void setDirLocation(String dirLocation) {
		this.dirLocation = dirLocation;
	}

	public String getFileName() {
		return processor.getTxtToday();
	}
}
