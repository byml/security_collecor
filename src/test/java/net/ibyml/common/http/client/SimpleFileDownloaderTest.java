package net.ibyml.common.http.client;

import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Before;
import org.junit.Test;

import net.ibyml.collector.szse.SzseZqhqHistoryProcessor;

public class SimpleFileDownloaderTest {
	@Before
	public void setup() {
		GlobalSetting.setDownloadFileHomeLocation("d:\\temp6");
	}

	@Test
	// 下载上交所A股股票列表
	public void downloadSseStockList() throws URISyntaxException {
		FileDownloadSetting setting = new SimpleFileDownloadSetting(
				"http://query.sse.com.cn/security/stock/downloadStockListFile.do?csrcCode=&stockCode=&areaName=&stockType=1",
				"交易品种", "上海A股");

		HttpRequestProcessor requestProcessor = new HttpRequestProcessor() {
			public void process(HttpRequestBase httpRequest) {
				httpRequest.addHeader("Referer", "http://www.sse.com.cn/assortment/stock/list/share/");
			}
		};

		new SimpleFileDownloader(setting, null, requestProcessor).download();
	}

	@Test
	// 下载深交所股票列表
	public void downloadSzseStockList() throws URISyntaxException {
		FileDownloadSetting setting = new SimpleFileDownloadSetting(
				"http://www.szse.cn/szseWeb/ShowReport.szse?SHOWTYPE=xlsx&CATALOGID=1110&tab1PAGENO=1&ENCODE=1&TABKEY=tab1",
				"交易品种", "深圳A股");
		new SimpleFileDownloader(setting).download();
	}

	@Test
	// 下载深交所可转债列表
	public void downloadSzseKzz() throws URISyntaxException {
		FileDownloadSetting setting = new SimpleFileDownloadSetting(
				"http://www.szse.cn/szseWeb/ShowReport.szse?SHOWTYPE=xlsx&CATALOGID=1277&ENCODE=1&TABKEY=tab1", "交易品种",
				"深圳可转债");
		new SimpleFileDownloader(setting).download();
	}

	@Test
	// 下载深交所股票历史行情
	public void downloadSzseZqhqHistory() throws URISyntaxException {
		SzseZqhqHistoryProcessor uriBuilderProcessor = new SzseZqhqHistoryProcessor("2017-04-05",
				"2017-04-07");
		FileDownloadSetting setting = new SimpleFileDownloadSetting(
				"http://www.szse.cn/szseWeb/ShowReport.szse?SHOWTYPE=xlsx&CATALOGID=1815_stock&ENCODE=1&TABKEY=tab1",
				"行情/深圳", uriBuilderProcessor.getTxtDateRange());
		new SimpleFileDownloader(setting, uriBuilderProcessor, null).download();

	}
}
