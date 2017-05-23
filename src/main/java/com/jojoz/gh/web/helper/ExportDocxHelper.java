package com.jojoz.gh.web.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.kit.PathKit;
import com.jojoz.gh.util.WordUtil;
import com.jojoz.gh.util.ZipUtils;

import freemarker.template.Template;

/**
 * 导出2007版Word
 * @author sunke
 * @version 1.0
 * @see
 *
 */
public class ExportDocxHelper {

	private Logger logger = LoggerFactory.getLogger(ExportDocxHelper.class);
	
	/**
	 *resource下路径 
	 */
	private static final String basePath = PathKit.getRootClassPath() + "/template/2007/";

	/**
	 * 构造测试数据
	 * 
	 * @return
	 */
	private Map<String, Object> createDatas() {
		Map<String, Object> testMap = new HashMap<String, Object>();
		// 构造散数据
		testMap.put("author", "孙珂");
		testMap.put("date", "2015-11-20");

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
	 * 生成docx
	 * <修改zip中document.xml,替换后生产>
	 * 
	 * @throws Exception
	 */
	public String exportDocx(Map<String, Object> dataMap) throws Exception {

		/** 准备数据 **/
	    //dataMap = createDatas();
		
	    String resultUrl = "";
	    
		WordUtil handler = new WordUtil();

		/** 加载模板 **/
		Template template = handler.getTemplate(basePath, "document.xml");


		/** 指定输出word文件的路径 **/
		String outFilePath = basePath + "data.xml";
		handler.writeTemlate(outFilePath, template, dataMap);
		try {

			ZipInputStream zipInputStream = ZipUtils
					.wrapZipInputStream(new FileInputStream(new File(basePath
							+ "table.zip")));
			ZipOutputStream zipOutputStream = ZipUtils
					.wrapZipOutputStream(new FileOutputStream(new File(basePath
							+ "table.docx")));
			String itemname = "word/document.xml";
			ZipUtils.replaceItem(zipInputStream, zipOutputStream, itemname,
					new FileInputStream(new File(basePath + "data.xml")));
			
			resultUrl = basePath + "table.docx";
			logger.info("07导出成功");

		} catch (Exception e) {
			logger.error(e.toString());
		}
		return resultUrl;
	}

	public static void main(String[] args) throws Exception {
		ExportDocxHelper exporter = new ExportDocxHelper();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		exporter.exportDocx(dataMap);
	}
}
