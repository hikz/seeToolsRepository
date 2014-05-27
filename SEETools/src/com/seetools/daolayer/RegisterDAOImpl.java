package com.seetools.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.seetools.dto.UserBean;

public class RegisterDAOImpl {

	private DataSource  dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public UserBean registerUser(final UserBean userDto){
		
		final String REGISTER_EMAIL_INSERT_STMT = 
			"INSERT INTO EMAIL(EmailAddress,CreatedByUserID,CreatedDate,ModifiedByUserID,ModifiedDate) VALUES (?,?,?,?,?)";
		final String REGISTER_USER_INSERT_STMT = 
			"INSERT INTO USER(EmailID,FirstName,LastName,MobileNumber,Password,MembershipID,Enabled,CreatedByUserID,CreatedDate,ModifiedByUserID,ModifiedDate) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?)";
		try{
			this.jdbcTemplate =  new JdbcTemplate(dataSource);
			
			//KeyHolder to hold generated primary keys for email and user data.
			KeyHolder emailKeyHolder = new GeneratedKeyHolder();
			KeyHolder userKeyHolder = new GeneratedKeyHolder();
			
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				  public PreparedStatement createPreparedStatement(Connection connection)
				      throws SQLException {
				    PreparedStatement ps = connection.prepareStatement(REGISTER_EMAIL_INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
				    ps.setString(1,userDto.getEmail().getEmailAddress());
				    ps.setString(2,userDto.getEmail().getCreatedByUserId());
				    ps.setTimestamp(3,userDto.getEmail().getCreatedDate());
				    ps.setString(4,userDto.getEmail().getModifiedByUserId());
				    ps.setTimestamp(5,userDto.getEmail().getModifiedDate());
				    return ps;
				  }
				}, emailKeyHolder);
			
			final Long generatedEmailId = new Long(emailKeyHolder.getKey().longValue());
			
			//Password Hashing
			
			userDto.setPassword(getHashedPassword(userDto.getPassword()));
			/*Object[] userParams = new Object[]{generatedEmailId, userDto.getFirstName(), userDto.getLastName(),userDto.getMobileNumber(),
					userDto.getPassword(),null,userDto.getCreatedByUserId(),userDto.getCreatedDate(),userDto.getModifiedByUserId(),userDto.getModifiedDate()};*/
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				  public PreparedStatement createPreparedStatement(Connection connection)
				      throws SQLException {
				    PreparedStatement ps = connection.prepareStatement(REGISTER_USER_INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
				    ps.setLong(1,generatedEmailId);
				    ps.setString(2,userDto.getFirstName());
				    ps.setString(3, userDto.getLastName());
				    ps.setString(4,userDto.getMobileNumber());
				    ps.setString(5,userDto.getPassword());
				    ps.setString(6,null);
				    ps.setString(7,"N");
				    ps.setString(8,userDto.getCreatedByUserId());
				    ps.setTimestamp(9,userDto.getCreatedDate());
				    ps.setString(10,userDto.getModifiedByUserId());
				    ps.setTimestamp(11,userDto.getModifiedDate());
				    return ps;
				  }
				}, userKeyHolder);
			
			final Long generatedUserId = new Long(userKeyHolder.getKey().longValue());
			userDto.getEmail().setEmailID(generatedEmailId.toString());
			userDto.setUserId(generatedUserId.toString());
			
			System.out.println("yaaaaaaa hooooooooo");
			
			
			}
			
			catch(DataAccessException e){
				e.printStackTrace();
			}
			
			catch(Exception e){
				e.printStackTrace();
			}
		return userDto;
	}

	
	public boolean updateRegistrationActivation(String email,String token){
		
		boolean active = false;
		final String UPDATE_REGISTRATION_ACTIVATION = "update user set enabled = 'Y' where " + 
				"emailid = (select emailid from email where emailaddress = ?)";
		
		this.jdbcTemplate =  new JdbcTemplate(dataSource);
		
		int rows = this.jdbcTemplate.update(UPDATE_REGISTRATION_ACTIVATION, new Object[]{email},new int[]{Types.VARCHAR});
		
		if(rows == 1){
			active = true;
		}
		return active;
	}
	

	private String getHashedPassword(String password){
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
		
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
