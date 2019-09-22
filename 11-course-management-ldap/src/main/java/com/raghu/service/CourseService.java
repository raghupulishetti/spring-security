package com.raghu.service;

import java.util.List;

import com.raghu.model.Course;

public interface CourseService {

	List<Course> findActiveCourses();

	String enrollCourse(Long courseId);

}
