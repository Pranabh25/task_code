package com.springboot.task.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessageDto {
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	} 
	
}