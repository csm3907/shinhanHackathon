package com.fund.dto.shinhan;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ContractVariablesDto { // 변액보험 목록조회, /contract/variables
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RequestDto implements Serializable{
		
		JSONObject dataHeader = new JSONObject();
		private DataBody dataBody;
		
		@Data
		public static class DataBody {
			private String workSc;
			private String rdreNo;
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
			private RetrieveCstVariConList retrieveCstVariConList;
			
			@Data
			public static class RetrieveCstVariConList {
				private int __status__;
				private String cstNm;
				private RetrieveCstVariConListCstVariConListDTO retrieveCstVariConListCstVariConListDTO;
				private RetrieveCstVariConListFundMattListDTO retrieveCstVariConListFundMattListDTO;
				
				@Data
				public static class RetrieveCstVariConListCstVariConListDTO{
					private int __status__;
					private List<RetrieveCstVariConListCstVariConDTO> retrieveCstVariConListCstVariConDTO;
					
					@Data
					public static class RetrieveCstVariConListCstVariConDTO {
						private int __status__;
						private String goodNm; // 보험상품명
						private String fundCd;
						private String admsRto; // 편입비율
						private String inmpAm; // 투입금액
						private String inonNo; // 보험계약번
					}
				}
				
				@Data
				public static class RetrieveCstVariConListFundMattListDTO{
					private int __status__;
					private List<RetrieveCstVariConListFundMattDTO> retrieveCstVariConListFundMattDTO;
					
					@Data
					public static class RetrieveCstVariConListFundMattDTO {
						private int __status__;
						private String fundCd;
						private String fundNm;
						private String admsRto; // 편입비율
						private String inonNo; // 보험계약번
					}
				}
			}			
		}
	}
}
