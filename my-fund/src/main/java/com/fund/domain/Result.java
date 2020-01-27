package com.fund.domain;
import com.fund.dto.FundDto.ResponseDtoList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

	public Result(ResponseDtoList response, String string, int i) {
		// TODO Auto-generated constructor stub
	}
	private Object result;
	private String message;
	private Integer status;	
}
