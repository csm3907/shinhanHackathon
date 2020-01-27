package com.fund.dto;

import java.io.Serializable;

import com.fund.domain.FundDetailTopItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FundDetailTopItemDto {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class RequestDto implements Serializable{
		private long fundDetailId;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ResponseDto implements Serializable{
		private long id;
		private String name;
		private String ratio;
		
		public ResponseDto(FundDetailTopItem item) {
			this.setName(item.getName());
			this.setRatio(item.getRate());
		}
	}
}
