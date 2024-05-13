package com.usermanagement.service.payload.response;

public class MessageResponse {

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public MessageResponse(String msg) {
		super();
		this.msg = msg;
	}

	public MessageResponse() {
		super();

	}

}