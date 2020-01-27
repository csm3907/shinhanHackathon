package com.fund.dto.shinhan;

import java.io.Serializable;
import java.util.List;

import com.fund.dto.shinhan.StockConcstbdSearchDto.ResponseDto.DataBody;
import com.fund.dto.shinhan.StockConcstbdSearchDto.ResponseDto.DataHeader;
import com.fund.dto.shinhan.StockConcstbdSearchDto.ResponseDto.DataBody.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class StockConcstbdRegiDto { // 주식 관심종목등록, /v1/stock/concstbd/regi,
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ResponseDto implements Serializable{
		private DataHeader dataHeader;
		private DataBody dataBody;
		
		@Data
		public static class DataHeader {
			private String resultCode;
			private String resultMessage;
			private String successCode;
			private String category;
		}
		
		@Data
		public static class DataBody {
		}
	}
}
