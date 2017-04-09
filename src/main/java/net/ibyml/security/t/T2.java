package net.ibyml.security.t;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class T2 {
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
//		HttpGet httpget = new HttpGet(
//				"http://www.szse.cn/szseWeb/ShowReport.szse?SHOWTYPE=xlsx&CATALOGID=1815_stock&txtBeginDate=2017-02-09&txtEndDate=2017-02-10&tab1PAGENUM=1&ENCODE=1&TABKEY=tab1");
// 
		
		String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//todayStr="2017-03-08";
		URI uri = new URIBuilder()
		        .setScheme("http")
		        .setHost("www.szse.cn")
		        .setPath("/szseWeb/ShowReport.szse")
		        .setParameter("SHOWTYPE", "xlsx") 
		        .setParameter("tab1PAGENUM", "1") 
		        .setParameter("ENCODE", "1") 
		        .setParameter("TABKEY", "tab1") 
		        .setParameter("CATALOGID", "1815_stock") 
		        .setParameter("txtBeginDate", todayStr) 
		        .setParameter("txtEndDate", todayStr) 
		        .build();
		System.out.println(uri.getQuery());
		HttpGet httpget = new HttpGet(uri);
		System.out.println(httpget.getURI());
//		httpget = new HttpGet(
// 				"http://www.szse.cn/szseWeb/ShowReport.szse?SHOWTYPE=xlsx&tab1PAGENUM=1&ENCODE=1&TABKEY=tab1&CATALOGID=1815_stock&txtBeginDate=2017-02-02&txtEndDate=2017-02-03");
//				
		CloseableHttpResponse response = httpclient.execute(httpget);		
		OutputStream os = new FileOutputStream(new File("d:\\doc\\行情\\深交所\\"+todayStr+".xlsx"));
		response.getEntity().writeTo(os);
		
		os.flush();
		os.close();
		try {
			// <...>
		} finally {
			response.close();
		}
	}
}
