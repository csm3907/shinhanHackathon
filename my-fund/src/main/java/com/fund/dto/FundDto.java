package com.fund.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fund.domain.Fund;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FundDto {
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class RequestDto implements Serializable{
		private long id;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ResponseDto implements Serializable{
		private long id;
		private String name;
		private String code;
	}
	
	@Data
	@NoArgsConstructor
	public static class ResponseDtoList implements Serializable{

		private static final long serialVersionUID = 718663592750838046L;
		private List<ResponseDto> funds;
		
		public ResponseDtoList(List<Fund> fundList) {
			this.funds = new ArrayList<FundDto.ResponseDto>();
			List<FundDto.ResponseDto> responseDtos = fundList.stream().map(r->r.getResponseDto()).collect(Collectors.toList());
			funds.addAll(responseDtos);
		}
	}

}
