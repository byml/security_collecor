package net.ibyml.collector.sse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import net.ibyml.common.http.client.FileDownloadSetting;
import net.ibyml.common.http.client.GlobalSetting;

public class SseMarketPriceCollector {

	private static Logger logger = Logger.getLogger(SseMarketPriceCollector.class);

	private FileDownloadSetting setting;
	private URIBuilder uriBuilder;

	public SseMarketPriceCollector(FileDownloadSetting setting) {
		this.setting = setting;
		try {
			uriBuilder = new URIBuilder(setting.getUrl());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void collecteData() {
		CloseableHttpResponse response = null;
		try {
			URI uri = uriBuilder.build();
			HttpGet httpget = new HttpGet(uri);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			response = httpclient.execute(httpget);
			String str = EntityUtils.toString(response.getEntity());
			str = "var result = " + str.substring(str.indexOf("(") + 1, str.length() - 1);

			ScriptEngineManager m = new ScriptEngineManager();
			ScriptEngine engine = m.getEngineByExtension("js");
			engine.eval(str);

			Map<String, Object> map = (Map<String, Object>) engine.get("result");
			Map securityListMap = (Map) map.get("list");
			if (!securityListMap.isEmpty()) {
				Workbook wb = new SXSSFWorkbook();
				Sheet sheet = wb.createSheet("new sheet");
				Row firstRow = sheet.createRow(0);

				String[] keyArray = { "证券代码", "证券简称", "最新", "开盘", "最高", "最低", "前收", "涨跌幅", "成交量", "成交额", "涨跌", "振幅",
						"tradephase" };
				for (int i = 0; i < keyArray.length; i++) {
					firstRow.createCell(i).setCellValue(keyArray[i]);
				}

				int rowIdx = 1;
				for (Iterator it = securityListMap.values().iterator(); it.hasNext(); rowIdx++) {
					Row row = sheet.createRow(rowIdx);
					Map securityMap = (Map) it.next();
					for (int colIdx = 0; colIdx < securityMap.size(); colIdx++) {
						Object cellValue = securityMap.get(String.valueOf(colIdx));
						if (cellValue instanceof Double) {
							row.createCell(colIdx).setCellValue((Double) cellValue);
						} else if (cellValue instanceof Integer) {
							row.createCell(colIdx).setCellValue((Integer) cellValue);
						} else {
							row.createCell(colIdx).setCellValue((String) cellValue.toString());
						}
					}
				}

				String fileLocation = GlobalSetting.getDownloadFileHomeLocation() + File.separator
						+ setting.getDirLocation();
				File fileLocaltionDir = new File(fileLocation);
				if (!fileLocaltionDir.exists()) {
					fileLocaltionDir.mkdirs();
				}
				String fileName = fileLocation + File.separator + map.get("date") + ".xlsx";
				FileOutputStream fileOut = new FileOutputStream(fileName);
				wb.write(fileOut);
				fileOut.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(e);
				}
			}
		}
	}
}
