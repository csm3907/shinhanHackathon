package com.fund.dto.shinhan;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class StockSearchDto { // 주식 종목검색, GET /v1/stock/search?category=kospi%2Ckosdaq&q=%EC%8B%A0%ED%95%9C&start=0&rows=20 HTTP/1.1
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ResponseDto implements Serializable{
		private DataHeader dataHeader;
		private DataBody dataBody;
		
		@Data
		public static class DataHeader {
			private String status;
			private String QTime;
			private Params params;
			
			@Data
			public static class Params{
				private String category;
				private String q;
			}
		}
		
		@Data
		public static class DataBody {
			private List<Item> list;
			private String numFound; // 전체조회갯수
			
			@Data
			public static class Item{
				private String id; // 종목코드
				private String name; // 종목명
				private String category; // 종목분류(kospi/kosdaq/etn/etf )
				private String iskeyword; // 키워드 포함여부
			}
		}
	}
}
