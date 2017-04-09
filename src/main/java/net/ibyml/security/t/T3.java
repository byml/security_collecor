package net.ibyml.security.t;

import java.io.FileOutputStream;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class T3 {
	public static void main(String[] args) throws Exception {
		String keys = "code,name,last,open,high,low,prev_close,chg_rate,volume,amount,change,amp_rate,tradephase";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		URI uri = new URIBuilder().setScheme("http").setHost("yunhq.sse.com.cn:32041")
			.setPath("/v1/sh1/list/exchange/equity")
			.setParameter("callback", "jQuery111209115037098085547_1488103518194")
			.setParameter("select", keys)
			.setParameter("order", "")
			.setParameter("begin", "0")
			.setParameter("end", "10000")
			.build();

		HttpGet httpget = new HttpGet(uri);

		CloseableHttpResponse response = httpclient.execute(httpget);
	 
		try {			
			String str = EntityUtils.toString(response.getEntity());
			str ="var result = "+ str.substring(str.indexOf("(")+1, str.length()-1);

			ScriptEngineManager m = new ScriptEngineManager();
			ScriptEngine engine = m.getEngineByExtension("js");	 
			engine.eval(str);
			
			Map map = (Map<String, Object>) engine.get("result"); 		
			Map securityListMap = (Map ) map.get("list");	
			if(!securityListMap.isEmpty()){
				Workbook wb = new SXSSFWorkbook();
			    Sheet sheet = wb.createSheet("new sheet"); 
			    Row firstRow = sheet.createRow(0);
			     		    	 				
			    String[] keyArray = {"证券代码","证券简称","最新","开盘","最高","最低",
			    		"前收","涨跌幅","成交量","成交额","涨跌","振幅","tradephase"};
			    for (int i = 0; i < keyArray.length; i++) {
					firstRow.createCell(i).setCellValue(keyArray[i]);
				}
			    		
			    int rowIdx = 1;
				for (Iterator it = securityListMap.values().iterator(); it.hasNext();rowIdx++) {
					Row row = sheet.createRow(rowIdx);
					Map securityMap = (Map) it.next();		
					for (int colIdx =0 ; colIdx <securityMap.size(); colIdx ++ ){
						Object cellValue = securityMap.get(String.valueOf(colIdx));
						if(cellValue instanceof Double){
							row.createCell(colIdx).setCellValue((Double) cellValue); 
						}else if(cellValue instanceof Integer){
							row.createCell(colIdx).setCellValue((Integer) cellValue); 
						}else{
							row.createCell(colIdx).setCellValue((String) cellValue.toString()); 
						}						
					}					
				} 
				
			    // Write the output to a file
			    FileOutputStream fileOut = new FileOutputStream("d:\\doc\\行情\\上交所\\"+map.get("date")+".xlsx");
			    wb.write(fileOut);
			    fileOut.close();
			}
		} finally {
			response.close();
		}
	}
}
