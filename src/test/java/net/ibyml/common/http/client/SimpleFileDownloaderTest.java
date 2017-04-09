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
	// �����Ͻ���A�ɹ�Ʊ�б�
	public void downloadSseStockList() throws URISyntaxException {
		FileDownloadSetting setting = new SimpleFileDownloadSetting(
				"http://query.sse.com.cn/security/stock/downloadStockListFile.do?csrcCode=&stockCode=&areaName=&stockType=1",
				"����Ʒ��", "�Ϻ�A��");

		HttpRequestProcessor requestProcessor = new HttpRequestProcessor() {
			public void process(HttpRequestBase httpRequest) {
				httpRequest.addHeader("Referer", "http://www.sse.com.cn/assortment/stock/list/share/");
			}
		};

		new SimpleFileDownloader(setting, null, requestProcessor).download();
	}

	@Test
	// ���������Ʊ�б�
	public void downloadSzseStockList() throws URISyntaxException {
		FileDownloadSetting setting = new SimpleFileDownloadSetting(
				"http://www.szse.cn/szseWeb/ShowReport.szse?SHOWTYPE=xlsx&CATALOGID=1110&tab1PAGENO=1&ENCODE=1&TABKEY=tab1",
				"����Ʒ��", "����A��");
		new SimpleFileDownloader(setting).download();
	}

	@Test
	// ���������תծ�б�
	public void downloadSzseKzz() throws URISyntaxException {
		FileDownloadSetting setting = new SimpleFileDownloadSetting(
				"http://www.szse.cn/szseWeb/ShowReport.szse?SHOWTYPE=xlsx&CATALOGID=1277&ENCODE=1&TABKEY=tab1", "����Ʒ��",
				"���ڿ�תծ");
		new SimpleFileDownloader(setting).download();
	}

	@Test
	// ���������Ʊ��ʷ����
	public void downloadSzseZqhqHistory() throws URISyntaxException {
		SzseZqhqHistoryProcessor uriBuilderProcessor = new SzseZqhqHistoryProcessor("2017-04-05",
				"2017-04-07");
		FileDownloadSetting setting = new SimpleFileDownloadSetting(
				"http://www.szse.cn/szseWeb/ShowReport.szse?SHOWTYPE=xlsx&CATALOGID=1815_stock&ENCODE=1&TABKEY=tab1",
				"����/����", uriBuilderProcessor.getTxtDateRange());
		new SimpleFileDownloader(setting, uriBuilderProcessor, null).download();

	}
}
