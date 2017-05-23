package com.jojoz.gh.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

public class HttpUtil {
	
	public static void setHeader(HttpServletResponse response,String filename, String path) throws UnsupportedEncodingException{
		  response.setContentType("octets/stream");  
		  response.addHeader("Content-Type", "text/html; charset=utf-8");  
		  String downLoadName = new String(filename.getBytes("gbk"), "iso8859-1");  
		  response.addHeader("Content-Disposition", "attachment;filename=" + downLoadName);  
	}
}
