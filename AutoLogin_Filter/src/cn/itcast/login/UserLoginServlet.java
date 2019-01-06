package cn.itcast.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginServlet extends HttpServlet {

	public UserLoginServlet() {
		super();
		System.out.println("UserLoginServlet--create");
	}

	public void destroy() {
		super.destroy(); 
	}
	private boolean checkUser(String username,String password){
		Enumeration<String> initParameterNames = this.getInitParameterNames();
		while(initParameterNames.hasMoreElements()){
			String ss = initParameterNames.nextElement();
			String str1 = this.getInitParameter(ss);
			if(null != str1 && !"".equals(str1)){
				String[] split1 = str1.split("@");
				if(split1[0].equals(username) && split1[1].equals(password)){
					System.out.println("str1校验通过："+str1);
					return true;
				}
			}
		}
		System.out.println("登录名校验未通过："+username);
		return false;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//	    <url-pattern>/UserLoginServlet</url-pattern>
//		servlet的访问路径为上面时，访问其他页面，也会带autologin cookie过来，
//		访问路径改为<url-pattern>/a/UserLoginServlet</url-pattern>,访问其他页面时，不会携带此cookie，导致无法自动登录
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		boolean checkUser = this.checkUser(username, password);
		if(!checkUser){
			//登录失败
			request.setAttribute("msg", "用户名或密码错误，请重新登录");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			User loginUser=new User();
			loginUser.setUsername(username);
			loginUser.setPassword(password);
			String autologin = (String)request.getParameter("autologin");
			if("yes".equals(autologin)){
				Cookie c =new Cookie("autologin", username+"@"+password);
				c.setMaxAge(Integer.MAX_VALUE);
				c.setPath("AutoLogin_Filter");
				response.addCookie(c);
			}
			request.getSession().setAttribute("user", loginUser);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {
		System.out.println("UserLoginServlet--init");
	}

}
