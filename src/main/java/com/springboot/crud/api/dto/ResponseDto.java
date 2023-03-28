package com.springboot.crud.api.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseDto {
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ResponseDto [msg=" + msg + "]";
	}
}
