package com.seetools.daolayer;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.seetools.dto.UserBean;

@Repository
public class LoginDAOImpl implements UserDetailsService {

	private DataSource  dataSource;
	private JdbcTemplate jdbcTemplate;
	
	
	public LoginDAOImpl() {
		super();
	}

	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		System.out.println("Inside main method fro db");
		UserDetails user = null;
		boolean enabled = false;
		try{
		this.jdbcTemplate =  new JdbcTemplate(dataSource);
		LoginRowCallBackHandler loginRowCallBackHandler = new LoginRowCallBackHandler();
		this.jdbcTemplate.query( "select u.*,e.emailAddress from user u inner join email e where e.emailID = u.emailID and e.emailAddress = ? and u.enabled = 'Y'",new Object[]{username}, loginRowCallBackHandler);
		
		/*UserBean userDto = this.jdbcTemplate.queryForObject(
		        "select * from user where UserID = ?",
		        new Object[]{username},new UserMapper());*/
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		UserBean userDto = loginRowCallBackHandler.getUserDto();
		
		if(userDto == null){
			throw new UsernameNotFoundException("Invalid Credentials. Please try again");
		}
		if(userDto.getEnabled().equals("Y")){
			enabled = true;
		}
		user = new User(userDto.getFirstName(), userDto.getPassword(), enabled , true, true, true, authorities);

		}
		
		catch(DataAccessException e){
			e.printStackTrace();
		}
		
		return user;
	}

	public boolean updatePassword(String emailAddress, String password) {
		
		boolean active = false;
		final String UPDATE_PASSWORD = "update user set password = ? where " + 
				"emailid = (select emailid from email where emailaddress = ?)";
		
		this.jdbcTemplate =  new JdbcTemplate(dataSource);
		
		int rows = this.jdbcTemplate.update(UPDATE_PASSWORD, new Object[]{password, emailAddress},new int[]{Types.VARCHAR, Types.VARCHAR});
		
		if(rows == 1){
			active = true;
		}
		return active;
		
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
