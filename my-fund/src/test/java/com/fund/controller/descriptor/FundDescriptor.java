package com.fund.controller.descriptor;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class FundDescriptor {
	
	public static FieldDescriptor[] getFundsResponseFields = new FieldDescriptor[] {
			fieldWithPath("result").type(JsonFieldType.OBJECT).description("결과"),
			fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메세지"),
			fieldWithPath("status").type(JsonFieldType.NUMBER).description("결과 코드"),
			fieldWithPath("result.funds.[].id").type(JsonFieldType.NUMBER).description("펀드 아이디"),
			fieldWithPath("result.funds.[].name").type(JsonFieldType.STRING).description("펀드 이름"),
			fieldWithPath("result.funds.[].code").type(JsonFieldType.STRING).description("펀드 코드"),
	};

}
