package com.jojoz.gh.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

/**
 * word公共方法
 * @author sunke
 * @version 1.0
 * @see
 */
public class WordUtil {

	private Configuration configuration = null;

	/**
	 * 构造方法
	 */
	public WordUtil() {
		try {
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据类路径获取模板
	 * 
	 * @param templatePath
	 * @param templateName
	 * @return
	 * @throws IOException
	 */
	public Template getTemplate(String templatePath, String templateName)
			throws IOException {
		configuration.setDirectoryForTemplateLoading(new File(templatePath));
		Template t = configuration.getTemplate(templateName);
		t.setEncoding("UTF-8");
		return t;
	}

	/**
	 * 生成word文档
	 * 
	 * @param templatePath
	 * @param templateName
	 * @param dataMap
	 * @param out
	 */
	public void write(String templatePath, String templateName,
			Map<String, Object> dataMap, Writer out) {
		try {
			Template t = getTemplate(templatePath, templateName);
			t.process(dataMap, out);
			if (out != null) {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeTemlate(String outFilePath, Template template,
			Map<String, Object> dataMap) {
		File docFile = new File(outFilePath);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(docFile);
			Writer out = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"), 10240);
			template.process(dataMap, out);
			if (out != null) {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
