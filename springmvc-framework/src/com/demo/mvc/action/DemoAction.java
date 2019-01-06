package com.demo.mvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.service.impl.IDemoService;
import com.mvcframework.annotation.DMAutowired;
import com.mvcframework.annotation.DMController;
import com.mvcframework.annotation.DMRequestMapping;
import com.mvcframework.annotation.DMRequestParam;

@DMController
@DMRequestMapping("/demo")
public class DemoAction {
	@DMAutowired 
	private IDemoService demoService;
	
	@DMRequestMapping("/query.json")
	public void query(HttpServletRequest req,HttpServletResponse resp,@DMRequestParam("name")String name){
		String result=demoService.get(name);
		try {
			resp.getWriter().write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@DMRequestMapping("/add.json")
	public void add(HttpServletRequest req,HttpServletResponse resp,
			@DMRequestParam("a")Integer a,@DMRequestParam("b")Integer b){
		try {
			resp.getWriter().write(a+"+"+b+"="+(a+b));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@DMRequestMapping("/remove.json")
	public void remove(HttpServletRequest req,HttpServletResponse resp,
			@DMRequestParam("id")Integer id){
	}

}
