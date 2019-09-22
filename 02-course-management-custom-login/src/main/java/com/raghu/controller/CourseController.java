package com.raghu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseController {
	@RequestMapping("/home")
	public String homePage(ModelMap model) {
		return "home";
	}

	@RequestMapping("/admin")
	public String productsPage(ModelMap model) {
		return "admin";
	}

	@RequestMapping("/user")
	public String contactUsPage(ModelMap model) {
		return "user";
	}
}