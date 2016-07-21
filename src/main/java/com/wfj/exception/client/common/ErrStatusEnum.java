package com.wfj.exception.client.common;

public enum ErrStatusEnum {

	UNDISPOSED("0"),		//the status is undisposed.
	PROCESSING("1"),	//the status is processed.
	PROCESSEND("2");	//2.process end
	
	private String code;
	
	private ErrStatusEnum(String code){
		this.code=code;
	}
	public String getCode(){
		return code;
	}
}
