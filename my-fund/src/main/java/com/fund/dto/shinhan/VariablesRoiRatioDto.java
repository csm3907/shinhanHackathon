package com.fund.dto.shinhan;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class VariablesRoiRatioDto { //변액보험 계약 수익률 조회, /v1/contract/variables/roi-ratio
	
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
			private String workSc;
			private String inonNo;
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
			private RetrieveCstRvnRtList retrieveCstRvnRtList;
			
			@Data
			public static class RetrieveCstRvnRtList {
				private int __status__;
				private String cstNm;
				private String rdreNo; // 주민번호
				private RetrieveCstRvnRtListRvnRtListDTO retrieveCstRvnRtListRvnRtListDTO;
				
				@Data
				public static class RetrieveCstRvnRtListRvnRtListDTO{
					private int __status__;
					private List<RetrieveCstRvnRtListRvnRtDTO> retrieveCstRvnRtListRvnRtDTO;
					
					@Data
					public static class RetrieveCstRvnRtListRvnRtDTO {
						private int __status__;
						private String fundNm;
						private String fundCd;
						private String admsRto; // 편입비율
						private String inmpAm; // 투입금액
						private String inonNo; // 보험계약번
					}
				}
			}			
		}
	}
}
