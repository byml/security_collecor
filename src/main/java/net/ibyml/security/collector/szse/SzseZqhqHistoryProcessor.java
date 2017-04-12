package net.ibyml.security.collector.szse;

import org.apache.http.client.utils.URIBuilder;

import net.ibyml.common.http.client.URIBuilderProcessor;

public class SzseZqhqHistoryProcessor implements URIBuilderProcessor {
	private String txtBeginDate;
	private String txtEndDate;

	public SzseZqhqHistoryProcessor(String txtBeginDate, String txtEndDate) {
		this.txtBeginDate = txtBeginDate;
		this.txtEndDate = txtEndDate;
	}

	public String getTxtDateRange() {
		return txtBeginDate + "_" + txtEndDate;
	}

	public void process(URIBuilder uriBuilder) {
		uriBuilder.addParameter("txtBeginDate", txtBeginDate);
		uriBuilder.addParameter("txtEndDate", txtEndDate);
	}

}