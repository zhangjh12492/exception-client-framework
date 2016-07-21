package com.wfj.exception.client.util;

public class MqResboundDto {

	private String messageID;
	private String serviceID;
	private String respStatus;
	private String bizCode;
	private String bizDesc;
	public String getMessageID() {
		return messageID;
	}
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public String getRespStatus() {
		return respStatus;
	}
	public void setRespStatus(String respStatus) {
		this.respStatus = respStatus;
	}
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public String getBizDesc() {
		return bizDesc;
	}
	public void setBizDesc(String bizDesc) {
		this.bizDesc = bizDesc;
	}
	
	
}
