package com.seetools.dto;

import java.sql.Timestamp;

public class AccountActivationTokenBean {

	private String emailID;
	private String activationToken;
	private String createdByUserId;
	private Timestamp createdDate;
	private String modifiedByUserId;
	private Timestamp modifiedDate;
	
	
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getActivationToken() {
		return activationToken;
	}
	public void setActivationToken(String activationToken) {
		this.activationToken = activationToken;
	}
	public String getCreatedByUserId() {
		return createdByUserId;
	}
	public void setCreatedByUserId(String createdByUserId) {
		this.createdByUserId = createdByUserId;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedByUserId() {
		return modifiedByUserId;
	}
	public void setModifiedByUserId(String modifiedByUserId) {
		this.modifiedByUserId = modifiedByUserId;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
