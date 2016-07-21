package com.wfj.exception.client.http.vo;

import java.io.Serializable;

public class ServerExceptionResp implements Serializable {
	private static final long serialVersionUID = 1L;
	private String errId;
	private String msg;
	private boolean success;

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrId() {
		return this.errId;
	}

	public void setErrId(String errId) {
		this.errId = errId;
	}

	public String toString() {
		return "ServerExceptionResp [errId=" + this.errId + ", msg=" + this.msg + ", success=" + this.success + "]";
	}
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.netty.vo.ServerExceptionResp
 * JD-Core Version:    0.6.0
 */