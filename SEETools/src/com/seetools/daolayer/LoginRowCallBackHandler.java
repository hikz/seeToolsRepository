package com.seetools.daolayer;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.seetools.dto.EmailBean;
import com.seetools.dto.UserBean;

public class LoginRowCallBackHandler implements RowCallbackHandler {

	UserBean userDto;
	
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		
		userDto = new UserBean();
		EmailBean emailDto = new EmailBean();
		emailDto.setEmailAddress(rs.getString("EmailAddress"));
		userDto.setEmailDto(emailDto);
		userDto.setUserId(rs.getString("UserID"));
		userDto.setPassword(rs.getString("Password"));
		
		userDto.setFirstName(rs.getString("FirstName"));
		userDto.setLastName(rs.getString("LastName"));
		userDto.setMobileNumber(rs.getString("MobileNumber"));
		userDto.setMembershipId(rs.getString("MembershipID"));
		userDto.setEnabled(rs.getString("Enabled"));
		userDto.setCreatedByUserId(rs.getString("CreatedByUserID"));
		userDto.setCreatedDate(rs.getTimestamp("CreatedDate"));
		userDto.setModifiedByUserId(rs.getString("ModifiedByUserId"));
		userDto.setModifiedDate(rs.getTimestamp("ModifiedDate"));
	}

	public UserBean getUserDto() {
		return userDto;
	}

	public void setUserDto(UserBean userDto) {
		this.userDto = userDto;
	}

	
}
