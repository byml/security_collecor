package net.ibyml.sse.collector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import net.ibyml.security.CollectorSetting;

/**
 * 股票列表采集器
 * 
 * @author Byml
 *
 */
public class StockListCollector  {
    //http://query.sse.com.cn/security/stock/downloadStockListFile.do?csrcCode=&stockCode=&areaName=&stockType=1


	private CollectorSetting setting;
	private URIBuilder uriBuilder;
	 

	public StockListCollector(CollectorSetting setting) {
		this.setting = setting;

		/*uriBuilder = new URIBuilder().setScheme("http").setHost(setting.getHost()).setPath("/szseWeb/ShowReport.szse")
				.setParameter("SHOWTYPE", "xlsx").setParameter("tab1PAGENUM", "1")
				.setParameter("ENCODE", "1").setParameter("TABKEY", "tab1");*/
		
		uriBuilder = new URIBuilder().setScheme("http").setHost("query.sse.com.cn")
				.setPath("security/stock/downloadStockListFile.do")
				.setParameter("stockType", "1");
		
		
	}
	
	public void collecteData() {
		Map<String,String> params = new HashMap<String, String>();
		//params.put("CATALOGID", "1110");
		collecteData(params);
	}

	public void collecteData(Map<String, String> params) {
		collecteData(params, null);
	}

	public void collecteData(Map<String, String> params, String fileName) {
		try {

			if (params != null && !params.isEmpty()) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					uriBuilder.addParameter(entry.getKey(), entry.getValue());
				}
			}
			URI uri = uriBuilder.build();
			
			System.out.println(uri.toString());

			HttpGet httpget = new HttpGet(uri);
			httpget.addHeader("Referer", "http://www.sse.com.cn/assortment/stock/list/share/");

			CloseableHttpClient httpclient = HttpClients.createDefault();

			CloseableHttpResponse response = httpclient.execute(httpget);

			String fileLocation = setting.getFileLocation();
			File fileLocaltionDir = new File(fileLocation);
			if (!fileLocaltionDir.exists()) {
				fileLocaltionDir.mkdirs();
			}

			if (fileName == null) {
				fileName = getFileName(response);
			}
			String fullFileName = fileLocation + File.separator + fileName;

			OutputStream os = new FileOutputStream(fullFileName);
			response.getEntity().writeTo(os);

			os.flush();
			os.close();

			System.out.println("________________________________fullFileName:" + fullFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getFileName(HttpResponse response) {

		Header headerContentDisposition = response.getFirstHeader("Content-Disposition");

		if (headerContentDisposition != null) {
			return getFileName(headerContentDisposition.getValue());
		} else {
			return "file";
		}
	}
	
	protected String getFileName(String contentDisposition) {
		if (contentDisposition != null) {
			int pos = contentDisposition.indexOf("=\"");
			if (pos > 0) {
				try {
					String fileName = new String(contentDisposition.substring(pos + 2, contentDisposition.length() - 1)
							.getBytes("ISO-8859-1"), "utf-8");
					return fileName;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return "file";
	}
 
}
