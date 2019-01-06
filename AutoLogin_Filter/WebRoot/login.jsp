<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>${msg }</div>
<form method="post" action="${pageContext.request.contextPath }/UserLoginServlet">
User<Input type="text" name="username"/><br/>
Pass<Input type="text" name="password"/><br/>
<input type="checkbox" name="autologin" value="yes"/>自动登录<br/>
<button>登录</button>
</form>
</body>
</html>