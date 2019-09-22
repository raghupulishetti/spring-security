package com.raghu.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
		antMatchers("/login").permitAll()
		.antMatchers("/logout").permitAll()
		.antMatchers("/home").permitAll()
		.antMatchers("/**").hasRole("USER")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").loginProcessingUrl("/login").failureUrl("/login?error")
		.usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/user",true)
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").invalidateHttpSession(true)
		.and()
		.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

}
