package cn.itcast.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class EncodingFilter
 */
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
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
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		if("POST".equals(req.getMethod())){
			req.setCharacterEncoding("utf-8");
			chain.doFilter(req, resp);
		}else{
			Myreq myreq=new Myreq(req);
			chain.doFilter(myreq, resp);
		}
		
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

class Myreq extends HttpServletRequestWrapper{
	HttpServletRequest request;

	public Myreq(HttpServletRequest request) {
		super(request);
		this.request=request;
	}

	@Override
	public String getParameter(String name) {
		String value = request.getParameter(name);
		try {
			value =new String(value.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	
					   
	
}
