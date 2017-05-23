package com.jojoz.gh.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Common {

	public final static int USER_STATE_ADMINISTRATOR = 3;
	public final static int USER_STATE_OPERATOR= 2;
	public final static int USER_STATE_USER = 1;
	public final static String  YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	
	/**
	 * Bean --> Map 
	 * <利用Introspector和PropertyDescriptor 将Bean --> Map  >
	 * @param obj
	 * @return
	 */
	 public static Map<String, Object> transBean2Map(Object obj) {  
		  
	        if(obj == null){  
	            return null;  
	        }          
	        Map<String, Object> map = new HashMap<String, Object>();  
	        try {  
	            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
	            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
	            for (PropertyDescriptor property : propertyDescriptors) {  
	                String key = property.getName();  
	  
	                // 过滤class属性  
	                if (!key.equals("class")) {  
	                    // 得到property对应的getter方法  
	                    Method getter = property.getReadMethod();  
	                    Object value = getter.invoke(obj);  
	  
	                    map.put(key, value);  
	                }  
	  
	            }  
	        } catch (Exception e) {  
	            System.out.println("transBean2Map Error " + e);  
	        }  
	  
	        return map;  
	  
	    }  
	 
	 /**
	  * 获取当前时间
	  * @return
	  */
	 public static String getNowTime(){
		 SimpleDateFormat df = new SimpleDateFormat(YYYYMMDDHHMMSS);//设置日期格式
		 return df.format(new Date());
	 }
	
}
