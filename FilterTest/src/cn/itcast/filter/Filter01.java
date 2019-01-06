package cn.itcast.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class Filter01
 */
public class Filter01 implements Filter {

    /**
     * Default constructor. 
     */
    public Filter01() {
        System.out.println("Filter01创建。。。");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("Filter01销毁");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Filter01过滤器前111");
		chain.doFilter(request, response);
		System.out.println("Filter01过滤器后222");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filter01关于初始化的函数");
		
		String filterName=fConfig.getFilterName();
		System.out.println("过滤器名称："+filterName);
		System.out.println("===================");
		String um1=fConfig.getInitParameter("username1");
		String um2=fConfig.getInitParameter("username2");
		System.out.println(um1+um2);
		System.out.println("-=------------------------------");
		Enumeration<String> em=fConfig.getInitParameterNames();
		while(em.hasMoreElements()){
			String name=em.nextElement();
			String value=fConfig.getInitParameter(name);
			System.out.println(name+"<===>"+value);
		}
	}

}
