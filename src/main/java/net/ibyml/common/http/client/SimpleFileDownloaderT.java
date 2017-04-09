package net.ibyml.common.http.client;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;

public class SimpleFileDownloaderT {
	public static void main(String[] args) throws URISyntaxException {
		SimpleFileDownloader.setHomeLocation("d:\\temp5");

//		m1();
//		m2();
//		m3();

		m4();
	}

	// �����Ͻ���A�ɹ�Ʊ�б�
	private static void m1() throws URISyntaxException {
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

	// ���������Ʊ�б�
	private static void m2() throws URISyntaxException {
		FileDownloadSetting setting = new SimpleFileDownloadSetting(
				"http://www.szse.cn/szseWeb/ShowReport.szse?SHOWTYPE=xlsx&CATALOGID=1110&tab1PAGENO=1&ENCODE=1&TABKEY=tab1",
				"����Ʒ��", "����A��");
		new SimpleFileDownloader(setting).download();
	}

	// ���������תծ�б�
	private static void m3() throws URISyntaxException {
		FileDownloadSetting setting = new SimpleFileDownloadSetting(
				"http://www.szse.cn/szseWeb/ShowReport.szse?SHOWTYPE=xlsx&CATALOGID=1277&ENCODE=1&TABKEY=tab1", "����Ʒ��",
				"���ڿ�תծ");
		new SimpleFileDownloader(setting).download();
	}

	// ���������Ʊ��ʷ����
	private static void m4() throws URISyntaxException {
		SzseZqhqHistoryURIBuilderProcessor uriBuilderProcessor = new SzseZqhqHistoryURIBuilderProcessor("2017-04-05","2017-04-07");
		FileDownloadSetting setting = new SimpleFileDownloadSetting(
				"http://www.szse.cn/szseWeb/ShowReport.szse?SHOWTYPE=xlsx&CATALOGID=1815_stock&ENCODE=1&TABKEY=tab1",
				"����/����", uriBuilderProcessor.getTxtDateRange());
		new SimpleFileDownloader(setting, uriBuilderProcessor, null).download();
		
	}

	static class SzseZqhqHistoryURIBuilderProcessor implements URIBuilderProcessor {
		private String txtBeginDate;
		private String txtEndDate;

		public SzseZqhqHistoryURIBuilderProcessor() {
			String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			this.txtBeginDate = todayStr;
			this.txtEndDate = todayStr;
		}

		public SzseZqhqHistoryURIBuilderProcessor(String txtBeginDate, String txtEndDate) {
			this.txtBeginDate = txtBeginDate;
			this.txtEndDate = txtEndDate;
		}

		public String getTxtDateRange() {
			if (txtBeginDate != null && txtBeginDate.equals(txtEndDate)) {
				return txtBeginDate;
			} else {
				return txtBeginDate + "_" + txtEndDate;
			}
		}

		public void process(URIBuilder uriBuilder) {
			uriBuilder.addParameter("txtBeginDate", txtBeginDate);
			uriBuilder.addParameter("txtEndDate", txtEndDate);
		}

	}
}
