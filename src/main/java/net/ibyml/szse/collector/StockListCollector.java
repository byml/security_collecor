package net.ibyml.szse.collector;

import java.util.HashMap;
import java.util.Map;

import net.ibyml.security.CollectorSetting;

/**
 * 股票列表采集器
 * 
 * @author Byml
 *
 */
public class StockListCollector extends DefaultStockListCollector{
  

	public StockListCollector(CollectorSetting setting) {
		super(setting);
	}

	public void collecteData() {
		Map<String,String> params = new HashMap<String, String>();
		params.put("CATALOGID", "1110");
		super.collecteData(params);
	}
}
