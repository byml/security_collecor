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

public class T4 {
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
 		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		while(calendar.get(Calendar.YEAR)>2016){
			String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
			//todayStr="2017-03-03";
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
			CloseableHttpResponse response = httpclient.execute(httpget);	
			
			if(response.getEntity().getContentLength()>5000){
				OutputStream os = new FileOutputStream(new File("d:\\doc\\行情\\深交所\\"+todayStr+".xlsx"));
				response.getEntity().writeTo(os);
				
				os.flush();
				os.close();
			}			

			
			try {
				// <...>
			} finally {
				response.close();
			}
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		}
	}
}
