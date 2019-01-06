package cn.itcast.login;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
public class AutoLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AutoLoginFilter() {
    	System.out.println("AutoLoginFilter--create");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("AutoLoginFilter--start");
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		User sessionuser = (User)req.getSession().getAttribute("user");
		if(null!=sessionuser){
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}else{
			Cookie ck=findCookieByName(req.getCookies(), "autologin");
			User cookuser=checkIfHasCookies(req);
			if(null!=cookuser){
				req.getSession().setAttribute("user", cookuser);
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}else{
				chain.doFilter(req, resp);
			}
		}
		System.out.println("AutoLoginFilter--end");
	}

	private User checkIfHasCookies(HttpServletRequest req) {
		User u=new User();
		Cookie[] cookies = req.getCookies();
		if(null==cookies){
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			String name = cookies[i].getName();
			if("autologin".equals(name)){
				String value = cookies[i].getValue();
				String[] split = value.split("@");
				if(("jack".equals(split[0])&&"jack".equals(split[1])) || ("rose".equals(split[0])&&"rose".equals(split[1]))){
					u.setUsername(split[0]);
					u.setPassword(split[1]);
					return u;
				}else{
					return null;
				}
			}
		}
		return null;
	}
	
	public static Cookie findCookieByName(Cookie[] cks,String name){
		if(null!=cks&&cks.length>0){
			for(Cookie ck:cks){
				if(name.equals(ck.getName())){
					return ck;
				}
			}
			
			return null;
			
		}else{
			return null;
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("AutoLoginFilter--init");
	}

}
