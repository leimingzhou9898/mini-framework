<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
  </head>
  
  <body>
    <c:if test="${empty user }">
    	<a href="${pageContext.request.contextPath }/login.jsp">请登录后再访问本页面</a>
    </c:if>
    <c:if test="${not empty user }">
    	欢迎${user.username }
    </c:if>
  </body>
</html>
