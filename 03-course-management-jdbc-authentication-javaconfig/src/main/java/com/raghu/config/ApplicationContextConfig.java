package com.raghu.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@Import({ WebSecurityConfig.class })
public class ApplicationContextConfig {
	@Bean
	public DataSource dataSource(@Value("${db.driverClassName}") String driverClsName, @Value("${db.url}") String url,
			@Value("${db.username}") String username, @Value("${db.password}") String password) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClsName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	public static PropertyPlaceholderConfigurer placeholderConfigurer() {
		PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
		placeholderConfigurer.setLocation(new ClassPathResource("database.properties"));
		return placeholderConfigurer;
	}

	@Bean
	public DatabasePopulator databasePopulator(DataSource dataSource) {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(true);
		populator.setIgnoreFailedDrops(true);
		populator.addScript(new ClassPathResource("security-schema.sql"));
		populator.addScript(new ClassPathResource("security-users.sql"));
		populator.addScript(new ClassPathResource("security-user-authorities.sql"));
		try {
			populator.populate(dataSource.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return populator;
	}

}
