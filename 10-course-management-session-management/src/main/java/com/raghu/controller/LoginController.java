package com.raghu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.raghu.model.UserDto;
import com.raghu.service.UserService;
import com.raghu.validator.UserValidator;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "loginError", required = false) String loginError,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "expired", required = false) String expired) {

		ModelAndView model = new ModelAndView();
		if (loginError != null) {
			model.addObject("message", "Invalid Credentials provided.");
		}

		if (logout != null) {
			model.addObject("message", "Logged out successfully.");
		}
		if (status != null) {
			model.addObject("message", status);
		}
		if (expired != null) {
			model.addObject("message", "You have been forcebly logged out due to multiple sessions.");
		}

		model.setViewName("login");
		return model;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signUp() {
		return new ModelAndView("signup", "signupForm", new UserDto());
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("signupForm") UserDto user, BindingResult result) {

		new UserValidator().validate(user, result);
		if (result.hasErrors()) {
			return new ModelAndView("signup");
		}
		String status = userService.signupUser(user);
		return new ModelAndView("redirect:login", "status", status);
	}

}
