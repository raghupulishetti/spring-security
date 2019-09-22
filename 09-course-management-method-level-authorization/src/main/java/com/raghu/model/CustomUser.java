package com.raghu.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

	private Long id;

	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			Long userId) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = userId;
	}

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
			Long userId) {
		super(username, password, authorities);
		this.id = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
