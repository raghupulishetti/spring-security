package com.raghu.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.raghu.dao.UserDao;
import com.raghu.model.UserDto;

//@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserDao userDao;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// UsernamePasswordAuthenticationToken upt =
		// (UsernamePasswordAuthenticationToken)authentication;
		List<UserDto> users = userDao.loadUsersByUsername(authentication.getName());
		if (users.size() == 0) {
			throw new UsernameNotFoundException("User not found");
		}
		UserDto user = users.get(0);
		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
		dbAuthsSet.addAll(userDao.loadUserAuthorities(user.getId()));
		dbAuthsSet.addAll(userDao.loadUserPermissions(user.getId()));

		return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), dbAuthsSet);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
