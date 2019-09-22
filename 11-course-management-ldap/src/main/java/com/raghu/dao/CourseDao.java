package com.raghu.dao;

import java.util.List;

import com.raghu.model.Course;

public interface CourseDao {

	List<Course> findActiveCourses();

	int enrollCourse(long userId, Long courseId);

	int checkIsDuplicate(long userId, Long courseId);

}
