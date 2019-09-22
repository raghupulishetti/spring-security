package com.raghu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.raghu.model.UserDto;
@Transactional
@Repository
public class UserDaoImpl implements UserDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	
	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public static final String DEF_USERS_BY_USERNAME_QUERY = "select username,password,enabled,ID " + "from user "
			+ "where username = ?";

	public static final String DEF_PERMISSIONS_BY_USERNAME_QUERY = "SELECT DISTINCT P.NAME FROM  PERMISSION P 	INNER JOIN ROLE_PERMISSION RP ON P.ID=RP.PERMISSION_ID"
			+ "						INNER JOIN ROLE R ON R.ID=RP.ROLE_ID"
			+ "						WHERE R.ID IN (SELECT UR.ROLE_ID  FROM USER_ROLE UR WHERE UR.USER_ID=?)";

	public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "SELECT R.NAME FROM ROLE R INNER JOIN USER_ROLE UR ON R.ID=UR.ROLE_ID"
			+ "			  INNER JOIN USER U ON U.ID=UR.USER_ID WHERE U.ID=?";

	@Override
	public List<UserDto> loadUsersByUsername(String username) {
		return this.jdbcTemplate.query(DEF_USERS_BY_USERNAME_QUERY, new String[] { username }, new RowMapper<UserDto>() {
			@Override
			public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				String username = rs.getString(1);
				String password = rs.getString(2);
				boolean enabled = rs.getBoolean(3);
				Long userId = rs.getLong(4);
				UserDto u = new UserDto();
				u.setUsername(username);
				u.setPassword(password);
				u.setEnabled(enabled);
				u.setId(userId);
				return u;
				// return new User(username, password, enabled, true, true, true,
				// loadUserAuthorities(userId));
			}

		});
	}

	public List<GrantedAuthority> loadUserAuthorities(Long userId) {
		return this.jdbcTemplate.query(DEF_AUTHORITIES_BY_USERNAME_QUERY, new Long[] { userId },
				new RowMapper<GrantedAuthority>() {
					@Override
					public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
						String roleName = "ROLE_" + rs.getString(1);

						return new SimpleGrantedAuthority(roleName);
					}
				});
	}

	public List<GrantedAuthority> loadUserPermissions(Long userId) {
		return this.jdbcTemplate.query(DEF_PERMISSIONS_BY_USERNAME_QUERY, new Long[] { userId },
				new RowMapper<GrantedAuthority>() {
					@Override
					public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
						String roleName = "ROLE_" + rs.getString(1);

						return new SimpleGrantedAuthority(roleName);
					}
				});
	}


	@Override
	public long signupUser(UserDto user) {
		String sql = "insert into User(username,password,enabled,firstname,email) values(?,?,?,?,?)";

		KeyHolder key = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String result = encoder.encode(user.getPassword());
				ps.setString(1, user.getUsername());
				ps.setString(2, result);
				ps.setBoolean(3, true);
				ps.setString(4, user.getFirstName());
				ps.setString(5, user.getEmail());

				return ps;
			}
		}, key);

		return key.getKey().longValue();
	}

	@Override
	public long saveUserRole(long id) {
		String sql = "insert into User_Role(user_id,role_id) values(?,?)";

		KeyHolder key = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, id);
				ps.setLong(2, 1L);
				return ps;
			}
		}, key);

		return key.getKey().longValue();
	}

}
