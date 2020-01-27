package com.fund.dto.shinhan;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class StockConcstbdSearchDto { // 주식 관심종목조회, /v1/stock/concstbd/search
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RequestDto implements Serializable{
		// private DataHeader dataHeader;
		private JSONObject dataHeader = new JSONObject();
		//private String dataHeader;
		private JSONObject databody = new JSONObject();
	}
	
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
			private List<Item> list;
			
			@Data
			public static class Item{
				private String dom_for_tp_code; // 국내해외구분코드 1: 국내
				private String stbd_code; // 종목코드
				private String stbd_nm; // 종목명
				private int price; // 현재가
				private int fluctuationPrice; // 변동가격
				private boolean isIncrease; // 상승유무
			}
		}
	}
}
