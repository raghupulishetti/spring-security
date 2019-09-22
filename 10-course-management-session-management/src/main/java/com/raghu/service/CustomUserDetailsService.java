package com.raghu.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raghu.dao.UserDao;
import com.raghu.model.CustomUser;
import com.raghu.model.UserDto;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserDao userDao;	
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserDto> users = userDao.loadUsersByUsername(username);
		if (users.size() == 0) {
			throw new UsernameNotFoundException("User not found");
		}
		UserDto user = users.get(0);
		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
		dbAuthsSet.addAll(userDao.loadUserAuthorities(user.getId()));
			dbAuthsSet.addAll(userDao.loadUserPermissions(user.getId()));
		return new CustomUser(
				user.getUsername(), user.getPassword(), user.getEnabled(), 
				true, true, true, dbAuthsSet,user.getId());
		
	}

}
