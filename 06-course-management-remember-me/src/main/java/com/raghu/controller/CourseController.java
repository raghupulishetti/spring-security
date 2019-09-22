package com.raghu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.raghu.model.Course;
import com.raghu.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	CourseService courseService;

	@RequestMapping("/home")
	public String homePage(ModelMap model) {
		
		
		List<Course> courses = courseService.findActiveCourses();
		model.addAttribute("courses",courses);
		return "home";
	}
	
	@RequestMapping("/enroll")
	public String enroll(@RequestParam("courseId") Long courseId,Model model,final RedirectAttributes redirectAttributes) {
		if(courseId!=null && courseId>0) {
			
			String status = courseService.enrollCourse(courseId);
			redirectAttributes.addFlashAttribute("status", status);
		}
		List<Course> courses = courseService.findActiveCourses();
		model.addAttribute("courses",courses);
		return "redirect:home";
	}
	
	@RequestMapping("/admin")
	public String productsPage(ModelMap model) {
	
		return "admin";
	}

	@RequestMapping("/user")
	public String contactUsPage(ModelMap model) {
		return "user";
	}
	
	@RequestMapping("/addcourse")
	public ModelAndView addCoursePage() {
		return new ModelAndView("course", "course", new Course());
	}
	@RequestMapping(value="/addcourse",method=RequestMethod.POST)
	public String saveCourse(@ModelAttribute Course course) {
		
		return null;
	}
	
}