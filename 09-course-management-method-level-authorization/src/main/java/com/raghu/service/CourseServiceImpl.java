package com.raghu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raghu.dao.CourseDao;
import com.raghu.model.Course;
import com.raghu.model.CustomUser;

@Transactional
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseDao courseDao;

	@Override
	public List<Course> findActiveCourses() {
		List<Course> courses = courseDao.findActiveCourses();
		return courses;
	}

	@Override
	public String enrollCourse(Long courseId) {
		String status = "Enroll is failure";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUser user = (CustomUser) authentication.getPrincipal();
		long userId = user.getId();

		try {
			int duplicateCount = courseDao.checkIsDuplicate(userId,courseId);
			if(duplicateCount>0) {
				status = "You have already enrolled for this course.";
			}else {
				int count = courseDao.enrollCourse(userId, courseId);
				if (count > 0)
					status = "Enroll is successful.";
			}
			
		} catch (DataAccessException de) {
			status = "Unable to process Your Request!Please Try Again";
		}
		return status;
	}

}
