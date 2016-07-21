package com.wfj.exception.common;

public enum MessageFlagEnum {
	VALID("0"), INVALID("1");

	private String code;

	private MessageFlagEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.common.MessageFlagEnum
 * JD-Core Version:    0.6.0
 */