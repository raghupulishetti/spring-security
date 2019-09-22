package com.raghu.dao;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.raghu.model.UserDto;

public interface UserDao {

	List<UserDto> loadUsersByUsername(String username);
	public List<GrantedAuthority> loadUserAuthorities(Long userId);
	public List<GrantedAuthority> loadUserPermissions(Long userId);
	long signupUser(UserDto user);
	long saveUserRole(long id);

}
