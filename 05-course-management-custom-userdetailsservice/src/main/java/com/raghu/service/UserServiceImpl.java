package com.raghu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raghu.dao.UserDao;
import com.raghu.model.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Transactional
	@Override
	public String signupUser(UserDto user) {
		String status = "User Registration is failure";
		try {
			List<UserDto> users = userDao.loadUsersByUsername(user.getUsername());
			if (users.isEmpty()) {
				long userId = userDao.signupUser(user);
				if (userId > 0) {
					long userRoleId = userDao.saveUserRole(userId);
					if (userRoleId > 0) {
						status = "User Registration is Success";
					}
				}
			} else {
				status = "User with this id already exists!!";
			}
		} catch (DataAccessException de) {
			status = "Unable to process Your Request!Please Try Again";
		}
		return status;
	}

}
