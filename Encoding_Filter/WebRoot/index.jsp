<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>POST方式提交中文</h3>
<form action="${pageContext.request.contextPath }/servlet/LoginServlet"  method="POST">
UserName<input type="text" name="username"/><br>
<button>登录</button>
</form>
<h3>GET方式提交中文</h3>
<form action="${pageContext.request.contextPath }/servlet/LoginServlet" method="GET">
UserName<input type="text" name="username"/><br>
<button>登录</button>
</form>
</body>
</html>