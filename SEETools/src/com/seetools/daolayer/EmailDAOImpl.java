package com.seetools.daolayer;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.seetools.daolayer.mapper.EmailMapper;
import com.seetools.dto.EmailBean;

public class EmailDAOImpl {

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
	
	public List<EmailBean> getEmailDetail(String emailAddress){
		
		final String GET_EMAIL_DETAIL = "select * from email where emailaddress = ?";
		
		this.jdbcTemplate =  new JdbcTemplate(dataSource);
		
		return this.jdbcTemplate.query(GET_EMAIL_DETAIL, new Object[]{emailAddress}, new EmailMapper());
	}

}
