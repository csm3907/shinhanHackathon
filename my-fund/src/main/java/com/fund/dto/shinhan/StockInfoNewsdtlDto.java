package com.fund.dto.shinhan;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class StockInfoNewsdtlDto { // 정보 주식 뉴스내용, /v1/info/newsdtl
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RequestDto implements Serializable{
		// private DataHeader dataHeader;
		JSONObject dataHeader = new JSONObject();
		//private String dataHeader;
		private DataBody databody;
		
		@Data
		public static class DataBody implements Serializable{
			private String news_cls; // 뉴스구분
			private String date; //  날짜
			private String news_seqn; // 일련번호
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
			private String news_date; //뉴스_일자
			private String search_engine_code; // 검색엔진추출종목코드
			private String news_cls; // 뉴스_종류구분
			private String news_data; // 뉴스_내용
			private String news_data1; // 뉴스_내용1
			private String news_in_time; // 뉴스_입력시간
		}
	}
}
