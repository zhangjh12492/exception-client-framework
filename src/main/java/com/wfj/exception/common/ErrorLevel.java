package com.wfj.exception.common;

public enum ErrorLevel {
	WARING("1"), ERROR("2");

	private String code;

	private ErrorLevel(String code) {
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
 * Qualified Name:     com.wfj.exception.common.ErrorLevel
 * JD-Core Version:    0.6.0
 */