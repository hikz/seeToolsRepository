package com.seetools.daolayer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.seetools.dto.EmailBean;

public class EmailMapper implements RowMapper<EmailBean> {

	@Override
	public EmailBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EmailBean emailDto = new EmailBean();
		emailDto.setEmailAddress(rs.getString("EmailAddress"));
		return emailDto;
	}

}
