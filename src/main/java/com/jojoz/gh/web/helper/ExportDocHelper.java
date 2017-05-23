package com.jojoz.gh.web.helper;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.kit.PathKit;
import com.jojoz.gh.util.WordUtil;

/**
 * 导出2003版Word
 * <另存为xml替换数据>
 * 
 * @author sunkey2017
 * @version 1.0
 * @see
 */
public class ExportDocHelper {

	private Logger logger = LoggerFactory.getLogger(ExportDocHelper.class);

	/**
	 * resource下路径
	 */
	private static final String basePath = PathKit.getRootClassPath() + "/template/2003/";

	/**
	 * 构造测试数据
	 * 
	 * @return
	 */
	private Map<String, Object> createDatas() {
		Map<String, Object> testMap = new HashMap<String, Object>();
		// 构造散数据
		testMap.put("author", "孙珂");
		testMap.put("date", "2017-05-22");

		// 构造列表循环数据存放在ArrayList集合中
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 5; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("xh", (i + 1) + "");
			map.put("name", "张三" + i);
			map.put("phone", "1381111222" + i);
			list.add(map);
		}
		testMap.put("datas", list);

		return testMap;
	}

	/**
	 * need word 03 xml
	 * 
	 * @param dataMap
	 * @return
	 */
	public String exportDoc(Map<String, Object> dataMap) {
		//dataMap = createDatas();
		String resultUrl = "";
		WordUtil handler = new WordUtil();
		Writer out = null;
		try {
			// 生成table.doc的word文件放到某文件路径下
			FileOutputStream fos = new FileOutputStream(basePath + "table.doc");
			out = new OutputStreamWriter(fos, "UTF-8");
			String templatePath = basePath;
			handler.write(templatePath, "table.ftl", dataMap, out);
			resultUrl = templatePath  + "table.doc";
			logger.info("03导出成功！");
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return resultUrl;
	}

//	public static void main(String[] args) {
//		ExportDocHelper exporter = new ExportDocHelper();
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		exporter.exportDoc(dataMap);
//	}
}
