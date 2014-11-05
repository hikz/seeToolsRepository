package com.seetools.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.seetools.daolayer.mapper.AccountActivationTokenMapper;
import com.seetools.dto.AccountActivationTokenBean;
import com.seetools.util.Utilities;

public class TokenVerificationDAOImpl {

	private DataSource  dataSource;
	private JdbcTemplate jdbcTemplate;
	
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
				    ps.setTimestamp(5,Utilities.getCurrentTimestamp());
				    ps.setString(6,"test");
				    ps.setTimestamp(7,Utilities.getCurrentTimestamp());
				    return ps;
				  }
				}, tokenKeyHolder);
			
			final Long generatedEmailId = new Long(tokenKeyHolder.getKey().longValue());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	
	public List<AccountActivationTokenBean> getAccountActivationTokenDetail(String emailAddress, String token){
		
		final String GET_ACCOUNT_ACTIVATION_TOKEN_DETAIL = "select t.* from email e,account_activation_token t where t.emailID = e.emailID and e.emailaddress = ? and t.activation_token = ?";
		
		this.jdbcTemplate =  new JdbcTemplate(dataSource);
		
		return this.jdbcTemplate.query(GET_ACCOUNT_ACTIVATION_TOKEN_DETAIL, new Object[]{emailAddress, token}, new AccountActivationTokenMapper());
	}



}
