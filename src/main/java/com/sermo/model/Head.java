package com.sermo.model;

import java.util.Date;

public class Head {
	
	private String Messagetype;
	private String messageID;
	private String OriginatorAddress;
	private String RecipientAddress;
	private Date CreationTime;
	
	public String getMessagetype() {
		return Messagetype;
	}
	public void setMessagetype(String messagetype) {
		Messagetype = messagetype;
	}
	
	public String getMessageID() {
		return messageID;
	}
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}
	public String getOriginatorAddress() {
		return OriginatorAddress;
	}
	public void setOriginatorAddress(String originatorAddress) {
		OriginatorAddress = originatorAddress;
	}
	public String getRecipientAddress() {
		return RecipientAddress;
	}
	public void setRecipientAddress(String recipientAddress) {
		RecipientAddress = recipientAddress;
	}
	public Date getCreationTime() {
		return CreationTime;
	}
	public void setCreationTime(Date creationTime) {
		CreationTime = creationTime;
	}
}
