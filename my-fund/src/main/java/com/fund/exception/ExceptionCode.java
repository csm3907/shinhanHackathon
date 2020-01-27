package com.fund.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
		//데이터
		INVALID_PARAMETER(1001,"Invalid Parameter"),
		DUPLICATED_DATA(1002,"Duplicated Data"),
		ALREADY_USED(1003, "Already Used"),
		ALREADY_IN_EXISTENCE(1004, "Already In Existence"),
		NOT_FOUND_DATA(1005,"Not Found Data"),
		
		//인증
		NOT_AUTHORIZED(2001,"Not Authorized"),
		ACCESS_DENIDED(2002, "Access Denied"),
		
		// API
		SENDING_REQUEST_FAILED(3001,"Sending request failed"),
		
		// Util
		JSON_PARSED_EXCEPTION(4001, "Json parsed Exception"),
		FILE_NOT_FOUND(4002, "File not Found"),
		FILE_IO_EXCEPTION(4003, "File IO Exception"),
		URL_ENCODING_EXCEPTION(4004, "Url encoding Exception"),
		URL_DECODING_EXCEPTION(4005, "Url decoding Exception"),
		
		// Kakao
		NO_KAKAO_TOKEN_FOUND(5001, "No kakao token Found")
		;
		
		Integer code;
		String message;

}
