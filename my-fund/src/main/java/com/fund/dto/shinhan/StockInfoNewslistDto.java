package com.fund.dto.shinhan;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

import com.fund.dto.shinhan.StockTrdprcTrendDto.ResponseDto.DataBody;
import com.fund.dto.shinhan.StockTrdprcTrendDto.ResponseDto.DataHeader;
import com.fund.dto.shinhan.StockTrdprcTrendDto.ResponseDto.DataBody.StockTransitionList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class StockInfoNewslistDto { // 정보 주식 뉴스목록, /v1/info/newslist
	
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
			private String news_cls; // 뉴스구분
			private String date; // 날짜
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
			private List<Item> list;
			private String repeatKey; // 반복키
			private String code; // 종목코드
			private String kor_name; // 종목명
			private String market_cls; // 장구분
			
			@Data
			public static class Item{
				private String news_date; //일자
				private String news_in_time; // 시간
				private String news_titl; // 제목
				private String news_cls; // 뉴스구분
				private String commitment_bidsize; // 기사번호
				private String news_no1; // 기사번호1
				private String shrt_code; // 종목코드
				private String press; // 언론사
			}
		}
	}

}
