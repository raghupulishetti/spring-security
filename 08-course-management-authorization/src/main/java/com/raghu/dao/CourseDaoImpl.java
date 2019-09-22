package com.raghu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.raghu.model.Course;
import com.raghu.model.UserDto;

@Transactional
@Repository
public class CourseDaoImpl implements CourseDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Course> findActiveCourses() {
		String sql = "select id,name, description,instructor,course_start_date, durationInDays from Course where delete_date is null and course_start_date>=(CURDATE()-INTERVAL 5 Day)";
		return this.jdbcTemplate.query(sql, new RowMapper<Course>() {
			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				long id = rs.getLong(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				String instructor = rs.getString(4);
				Date courseStartDate = rs.getDate(5);
				int durationInDays = rs.getInt(6);

				Course c = new Course();
				c.setId(id);
				c.setName(name);
				c.setDescription(description);
				c.setInstructor(instructor);
				c.setCourseStartDate(courseStartDate);
				c.setDurationInDays(durationInDays);

				return c;

			}

		});
	}

	@Override
	public int enrollCourse(long userId, Long courseId) {
		String sqlInsert = "INSERT INTO user_course (user_id,course_id)" + " VALUES (?,?)";
		return jdbcTemplate.update(sqlInsert, userId, courseId);
	}

	@Override
	public int checkIsDuplicate(long userId, Long courseId) {
		String sql = "select count(*) from user_course where user_id=? and course_id=?";
		Number number = this.jdbcTemplate.queryForObject(sql, new Long[] { userId, courseId }, Integer.class);
		return (number != null ? number.intValue() : 0);
	}

}
