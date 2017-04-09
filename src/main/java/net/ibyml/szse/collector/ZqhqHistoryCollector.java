package net.ibyml.szse.collector;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.ibyml.security.CollectorSetting;

/**
 * 证券历史行情采集器
 * 
 * @author Byml
 *
 */
public class ZqhqHistoryCollector extends DefaultStockListCollector{
 

	public ZqhqHistoryCollector(CollectorSetting setting) {
		super(setting); 
	}

	public void collecteData() {
		String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 

		Map<String,String> params = new HashMap<String, String>();
		params.put("CATALOGID", "1815_stock");
		params.put("txtBeginDate", todayStr);
		params.put("txtEndDate", todayStr);
		
		super.collecteData(params, todayStr +".xlsx");
	}
 
}
