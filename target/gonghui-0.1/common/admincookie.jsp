<%@ page language="java" import="java.util.*,javax.servlet.http.Cookie" pageEncoding="utf-8"%>

<%
	Cookie myCookie[] = request.getCookies();
	boolean bool = false;
	for (int i = 0;i<myCookie.length;i++){
		Cookie newCookie = myCookie[i];
		if (newCookie.getName().equals("user"))
		{
			bool = true;
		}
	}
	if(!bool){
		response.sendRedirect("../login.html");
	}
	
		
	
	
%>