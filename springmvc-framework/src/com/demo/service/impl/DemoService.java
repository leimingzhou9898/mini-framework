package com.demo.service.impl;

import com.mvcframework.annotation.DMService;

@DMService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "welcome a zhou "+name;
	}

}
