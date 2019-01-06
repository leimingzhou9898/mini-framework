package cn.itcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletOne extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ServletOne() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String servletint = this.getServletConfig().getInitParameter("servletint");
		System.out.println("servletinta"+servletint);
		String servletinta = this.getServletConfig().getInitParameter("servletinta");
		System.out.println("servletinta"+servletinta);
		String contextint = this.getServletContext().getInitParameter("contextint");
		System.out.println("contextint:"+contextint);
		String contextinta = this.getServletContext().getInitParameter("contextinta");
		System.out.println("contextinta:"+contextinta);
		
		request.setCharacterEncoding("UTF-8");
		String parameter = request.getParameter("name");
		System.out.println(parameter);
		String parametera = request.getParameter("namea");
		System.out.println(parametera);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		System.out.println("servletone创建了");
	}

}
