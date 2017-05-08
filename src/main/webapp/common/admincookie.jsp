<%@ page language="java" import="java.util.*,javax.servlet.http.Cookie" pageEncoding="utf-8"%>

<%
	Cookie myCookie[] = request.getCookies();
	boolean bool = false;
	for (int i = 0;i<myCookie.length;i++){
		Cookie newCookie = myCookie[i];
		if (newCookie.getName().equals("user"))
		{
			if(!newCookie.getValue().equals("null")){
				bool = true;
			}else{
				bool = false;
			}
			
		}
	}
	if(!bool){
		response.sendRedirect("/gonghui/login.html");
	}
	
		
	
	
%>