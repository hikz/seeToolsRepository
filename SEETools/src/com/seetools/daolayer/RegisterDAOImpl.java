package com.seetools.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.seetools.dto.UserBean;
import com.seetools.util.PasswordEncoder;

public class RegisterDAOImpl {

	final Logger logger = LoggerFactory.getLogger(RegisterDAOImpl.class);
	private DataSource  dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public UserBean registerUser(final UserBean userDto) {
		
		logger.info("Registration process started");
		final String REGISTER_EMAIL_INSERT_STMT = 
			"INSERT INTO EMAIL(EmailAddress,CreatedByUserID,CreatedDate,ModifiedByUserID,ModifiedDate) VALUES (?,?,?,?,?)";
		final String REGISTER_USER_INSERT_STMT = 
			"INSERT INTO USER(EmailID,FirstName,LastName,MobileNumber,Password,MembershipID,Enabled,CreatedByUserID,CreatedDate,ModifiedByUserID,ModifiedDate) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?)";
	
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
			logger.debug("Email Details created with Email ID:", generatedEmailId);
			//Password Hashing
			
			userDto.setPassword(PasswordEncoder.getHashedPassword(userDto.getPassword()));
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
			
			logger.debug("User Details created with User ID:", generatedUserId);
			userDto.getEmail().setEmailID(generatedEmailId.toString());
			userDto.setUserId(generatedUserId.toString());
			
			logger.info("Registration process ended");
			return userDto;
	}

	
	public boolean updateRegistrationActivation(String email,String token){
		
		logger.info("Update Registration Activation Details - Start");
		boolean active = false;
		final String UPDATE_REGISTRATION_ACTIVATION = "update user set enabled = 'Y' where " + 
				"emailid = (select emailid from email where emailaddress = ?)";
		
		this.jdbcTemplate =  new JdbcTemplate(dataSource);
		int rows = this.jdbcTemplate.update(UPDATE_REGISTRATION_ACTIVATION, new Object[]{email},new int[]{Types.VARCHAR});
		
		if(rows == 1){
			logger.info("");
			active = true;
		}
		logger.info("Update Registration Activation Details - Complete");
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
