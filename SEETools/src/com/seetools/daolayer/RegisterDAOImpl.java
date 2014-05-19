package com.seetools.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.seetools.daolayer.mapper.AccountActivationTokenMapper;
import com.seetools.daolayer.mapper.EmailMapper;
import com.seetools.dto.AccountActivationTokenBean;
import com.seetools.dto.EmailBean;
import com.seetools.dto.UserBean;
import com.seetools.presentation.common.SEEUtilities;

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

	public void saveAccountActivationToken(final String emailID, final String token) {
		
		final String ACCOUNT_ACTIVATION_TOKEN = 
				"INSERT INTO ACCOUNT_ACTIVATION_TOKEN(EmailID,Activation_Token,Active,CreatedByUserID,CreatedDate,ModifiedByUserID,ModifiedDate) VALUES (?,?,?,?,?,?,?)";
		
		try{
			this.jdbcTemplate =  new JdbcTemplate(dataSource);
			
			//KeyHolder to hold generated primary keys for email and user data.
			KeyHolder tokenKeyHolder = new GeneratedKeyHolder();
			
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				  public PreparedStatement createPreparedStatement(Connection connection)
				      throws SQLException {
				    PreparedStatement ps = connection.prepareStatement(ACCOUNT_ACTIVATION_TOKEN, Statement.RETURN_GENERATED_KEYS);
				    ps.setInt(1, Integer.parseInt(emailID));
				    ps.setString(2, token);
				    ps.setString(3, "A");
				    ps.setString(4,"test");
				    ps.setTimestamp(5,SEEUtilities.getCurrentTimeStamp());
				    ps.setString(6,"test");
				    ps.setTimestamp(7,SEEUtilities.getCurrentTimeStamp());
				    return ps;
				  }
				}, tokenKeyHolder);
			
			final Long generatedEmailId = new Long(tokenKeyHolder.getKey().longValue());
			
		} catch (Exception e){
			e.printStackTrace();
		}
			
		
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
	
	public List<EmailBean> getEmailDetail(String emailAddress){
		
		final String GET_EMAIL_DETAIL = "select * from email where emailaddress = ?";
		
		this.jdbcTemplate =  new JdbcTemplate(dataSource);
		
		return this.jdbcTemplate.query(GET_EMAIL_DETAIL, new Object[]{emailAddress}, new EmailMapper());
	}
	
	public List<AccountActivationTokenBean> getAccountActivationTokenDetail(String emailAddress, String token){
		
		final String GET_ACCOUNT_ACTIVATION_TOKEN_DETAIL = "select * from email e,account_activation_token t where t.emailID = e.emailID and e.emailaddress = ? and t.activation_token = ?";
		
		this.jdbcTemplate =  new JdbcTemplate(dataSource);
		
		return this.jdbcTemplate.query(GET_ACCOUNT_ACTIVATION_TOKEN_DETAIL, new Object[]{emailAddress, token}, new AccountActivationTokenMapper());
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
