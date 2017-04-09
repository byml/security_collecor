package net.ibyml.collector.szse;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.utils.URIBuilder;

import net.ibyml.common.http.client.URIBuilderProcessor;

public class SzseZqhqProcessor implements URIBuilderProcessor {
	private String todayStr;

	public String getTxtToday() {
		return todayStr;
	}

	public void process(URIBuilder uriBuilder) {
		todayStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		uriBuilder.addParameter("txtBeginDate", todayStr);
		uriBuilder.addParameter("txtEndDate", todayStr);
	}
}