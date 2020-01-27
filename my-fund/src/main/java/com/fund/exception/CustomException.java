package com.fund.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
	Integer code;
	String message;
	
	public CustomException(ExceptionCode ec) {
		this.setCode(ec.getCode());
		this.setMessage(ec.getMessage());
	}
}
