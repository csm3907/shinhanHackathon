package com.fund.dto.shinhan;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class StockTrdprcTrendDto { // 주식 현재가 주가추이, /v1/stock/trdprc-trend
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RequestDto implements Serializable{
		// private DataHeader dataHeader;
		JSONObject dataHeader = new JSONObject();
		//private String dataHeader;
		private DataBody databody;
		
		@Data
		public static class DataBody {
			private String code;
		}
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
		}
		
		@Data
		public static class DataBody {
			private List<StockTransitionList> stockTransitionList;
			private String trdprc; //현재가
			private String code; // 종목코드
			private String kor_name; // 종목명
			private String market_cls; // 장구분
			
			@Data
			public static class StockTransitionList{
				private String trdprc_1; //거래가
				private String trade_date; // 거래일자
				private String acvol_1; // 거래량
			}
		}
	}
}
