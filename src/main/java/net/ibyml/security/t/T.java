package net.ibyml.security.t;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class T {
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		
		URI uri = new URIBuilder()
		        .setScheme("http")
		        .setHost("www.szse.cn")
		        .setPath("/ShowReport.szse?SHOWTYPE=xlsx&tab1PAGENUM=1&ENCODE=1&TABKEY=tab1")
		        .setParameter("CATALOGID", "1110") 
		        .build();
		HttpGet httpget = new HttpGet(uri);
		CloseableHttpResponse response = httpclient.execute(httpget);		
		OutputStream os = new FileOutputStream(new File("d:\\t13.xlsx"));
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
