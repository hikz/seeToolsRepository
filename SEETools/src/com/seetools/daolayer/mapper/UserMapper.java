package com.seetools.daolayer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.seetools.dto.EmailBean;
import com.seetools.dto.UserBean;

public class UserMapper implements RowMapper<UserBean> {

	@Override
	public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserBean userDto = new UserBean();
		EmailBean emailDto = new EmailBean();
		emailDto.setEmailAddress(rs.getString("EmailAddress"));
		userDto.setEmailDto(emailDto);
		userDto.setUserId(rs.getString("UserID"));
		userDto.setPassword(rs.getString("Password"));
		
		userDto.setFirstName(rs.getString("FirstName"));
		userDto.setLastName(rs.getString("LastName"));
		userDto.setMobileNumber(rs.getString("MobileNumber"));
		userDto.setMembershipId(rs.getString("MembershipID"));
		userDto.setCreatedByUserId(rs.getString("CreatedByUserID"));
		userDto.setCreatedDate(rs.getTimestamp("CreatedDate"));
		userDto.setModifiedByUserId(rs.getString("ModifiedByUserId"));
		userDto.setModifiedDate(rs.getTimestamp("ModifiedDate"));
		return userDto;
	}

}
