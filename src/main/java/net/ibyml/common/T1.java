package net.ibyml.common;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;

public class T1 implements I {
	private String fileName;
	
	public URI getURI(){
		URIBuilder uriBuilder = new URIBuilder().setScheme("http").setHost("query.sse.com.cn")
				.setPath("security/stock/downloadStockListFile.do")
				.setParameter("stockType", "1");
		
		try {
			return uriBuilder.build();
		} catch (URISyntaxException e) {
			return null;
		}
	}

	public void xxx(HttpRequestBase httpRequest) {
		String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		Map<String, String> params = new HashMap<String, String>();
		params.put("CATALOGID", "1815_stock");
		params.put("txtBeginDate", todayStr);
		params.put("txtEndDate", todayStr);

		fileName = todayStr + ".xlsx";
	}

	public String getFileLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFileName() {
		return fileName;
	}
	
	public static void main(String[] args) {
		try {
			URIBuilder uriBuilder = new URIBuilder("http://query.sse.com.cn/security/stock/downloadStockListFile.do?csrcCode=&stockCode=&areaName=&stockType=1");
			System.out.println(uriBuilder.build().toString());
			
			uriBuilder.addParameter("abc", "def"); 
			System.out.println(uriBuilder.build().toString()); 
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
