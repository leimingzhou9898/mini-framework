package com.mvcframework.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvcframework.annotation.DMAutowired;
import com.mvcframework.annotation.DMController;
import com.mvcframework.annotation.DMRequestMapping;
import com.mvcframework.annotation.DMService;

public class DMDispatcherServlet extends HttpServlet {
	private static final String LOCATION="contextConfigLocation";
	private Properties p =new Properties();
	private List<String> classNames=new ArrayList<String>();
	private Map<String,Object> ioc=new HashMap<String, Object>();
	private Map<String,Method> handlerMapping=new HashMap<String,Method>();
	
	
	public DMDispatcherServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doDispatch(request, response);
		} catch (Exception e) {
			response.getWriter().write("500 Exception,Details:\r\n"+Arrays.toString(e.getStackTrace()).
					replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n"));
		}
		
	}

	public void init(ServletConfig config) throws ServletException {
		doLoadConfig(config.getInitParameter(LOCATION));
		doScanner(p.getProperty("scanPackage"));
		doInstance();
		doAutowired();
		initHandlerMapping();
		System.out.println("=============spring初始化完成==============");
	}
	private void doLoadConfig(String location){
		InputStream fis=null;
		fis=this.getClass().getClassLoader().getResourceAsStream(location);
		try {
			p.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null!=fis){ fis.close(); }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void doScanner(String packageName){
		URL url=this.getClass().getClassLoader().getResource("/"+packageName.replaceAll("\\.", "/"));
		File dir=new File(url.getFile());
		for(File file:dir.listFiles()){
			if(file.isDirectory()){
				doScanner(packageName+"."+file.getName());
			}else{
				classNames.add(packageName+"."+file.getName().replace(".class", "").trim());
			}
			
		}
	}
	private String lowerFirstCase(String str){
		char[] chars=str.toCharArray();
		chars[0] +=32;
		return String.valueOf(chars);
	}
	private void doInstance(){
		if(classNames.size()==0){return;}
		try {
			for(String className:classNames){
				Class<?> clazz=Class.forName(className);
				if(clazz.isAnnotationPresent(DMController.class)){
					String beanName=lowerFirstCase(clazz.getSimpleName());
					ioc.put(beanName, clazz.newInstance());
				}else if(clazz.isAnnotationPresent(DMService.class)){
					DMService service = clazz.getAnnotation(DMService.class);
					String beanName=service.value();
					if(!"".equals(beanName.trim())){
						ioc.put(beanName, clazz.newInstance());
						continue;
					}
					Class<?>[] interfaces = clazz.getInterfaces();
					for(Class i:interfaces){
						ioc.put(i.getName(), clazz.newInstance());
					}
				}else{
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void doAutowired(){
		if(ioc.isEmpty()){return;}
		for(Entry<String,Object> entry:ioc.entrySet()){
			Field[] fields=entry.getValue().getClass().getDeclaredFields();
			for(Field field:fields){
				if(!field.isAnnotationPresent(DMAutowired.class)){continue;}
				DMAutowired annotation = field.getAnnotation(DMAutowired.class);
				String beanName = annotation.value().trim();
				if("".equals(beanName)){
					beanName=field.getType().getName();
				}
				field.setAccessible(true);
				try {
					field.set(entry.getValue(), ioc.get(beanName));
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				} 
			}
		}
	}
	private void initHandlerMapping(){
		if(ioc.isEmpty()){return;}
		for(Entry<String,Object> entry:ioc.entrySet()){
			Class<?> clazz=entry.getValue().getClass();
			if(!clazz.isAnnotationPresent(DMController.class)){continue;}
			String baseUrl="";
			if(clazz.isAnnotationPresent(DMRequestMapping.class)){
				DMRequestMapping annotation = clazz.getAnnotation(DMRequestMapping.class);
				baseUrl=annotation.value();
			}
			Method[] methods = clazz.getMethods();
			for(Method method:methods){
				if(!method.isAnnotationPresent(DMRequestMapping.class)){continue;}
				DMRequestMapping annotation = method.getAnnotation(DMRequestMapping.class);
				String url=(baseUrl+annotation.value()).replaceAll("/+", "/");
				handlerMapping.put(url, method);
				System.out.println("=======mapped======"+url+","+method);
			}
		}
	}
	private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(this.handlerMapping.isEmpty()){return;}
		String url=request.getRequestURI();
		String contextPath=request.getContextPath();
		url=url.replace(contextPath, "").replaceAll("/+", "/");
		if(!this.handlerMapping.containsKey(url)){
			response.getWriter().write("404 Not Found!");
			return;
		}
		Method method = handlerMapping.get(url);
		Class<?>[] parameterTypes = method.getParameterTypes();
		Map<String,String[]> parameterMap = request.getParameterMap();
		Object [] paramValues=new Object[parameterTypes.length];
		for(int i=0;i<parameterTypes.length;i++){
			Class<?> parameterType = parameterTypes[i];
			if(parameterType==HttpServletRequest.class){
				paramValues[i]=request;
				continue;
			}else if (parameterType==HttpServletResponse.class){
				paramValues[i]=response;
				continue;
			}else if(parameterType==String.class){
				for(Entry<String,String[]> entry:parameterMap.entrySet()){
					String value = Arrays.toString(entry.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
					paramValues[i]=value;
				}
			}
		}
		try {
			String beanName = lowerFirstCase(method.getDeclaringClass().getSimpleName());
			method.invoke(ioc.get(beanName), paramValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	

}
