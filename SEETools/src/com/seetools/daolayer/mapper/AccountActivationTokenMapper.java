package com.seetools.daolayer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.seetools.dto.AccountActivationTokenBean;


public class AccountActivationTokenMapper implements RowMapper<AccountActivationTokenBean> {

	@Override
	public AccountActivationTokenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AccountActivationTokenBean accountActivationTokenBean = new AccountActivationTokenBean();
		accountActivationTokenBean.setEmailID(Integer.toString(rs.getInt("emailID")));
		accountActivationTokenBean.setActivationToken(rs.getString("activation_token"));
		accountActivationTokenBean.setCreatedByUserId(rs.getString("CreatedByUserID"));
		accountActivationTokenBean.setCreatedDate(rs.getTimestamp("CreatedDate"));
		accountActivationTokenBean.setModifiedByUserId(rs.getString("ModifiedByUserId"));
		accountActivationTokenBean.setModifiedDate(rs.getTimestamp("ModifiedDate"));
		return accountActivationTokenBean;
	}
}
