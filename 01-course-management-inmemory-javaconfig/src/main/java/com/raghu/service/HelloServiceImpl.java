package com.raghu.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		String message = "Hello :" + name + " welcome to spring MVC";
		return message;
	}

}
