package com.fund.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fund.controller.descriptor.FundDescriptor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class FundControllerTest {
	
	private final Logger logger = LoggerFactory.getLogger(FundControllerTest.class);
	
	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
	
	@Autowired
	private WebApplicationContext context;
	
	private RestDocumentationResultHandler documentationHandler;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.documentationHandler=document(
				"{class-name}/{method-name}",
				preprocessResponse(prettyPrint()));
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation))
				.alwaysDo(documentationHandler)
				.build();
	}
	
	@Test
	public void getFunds() throws Exception {
		FieldDescriptor[] responseFields = FundDescriptor.getFundsResponseFields;
		
		MvcResult mvcResult = this.mockMvc.perform(get("/v1.0/{memberId}/funds", 1))
				.andExpect(status().isOk())
				.andDo(this.documentationHandler.document(
							responseFields(responseFields)
						))
				.andReturn();
		
		String result = mvcResult.getResponse().getContentAsString();
		logger.info(result);
	}
	
	@Test
	public void saveFund() throws Exception {
		FieldDescriptor[] responseFields = FundDescriptor.getFundsResponseFields;
		
		MvcResult mvcResult = this.mockMvc.perform(post("/v1.0/fund/init"))
				.andExpect(status().isOk())
				.andDo(this.documentationHandler.document(
							responseFields(responseFields)
						))
				.andReturn();
		
		String result = mvcResult.getResponse().getContentAsString();
		logger.info(result);
	}

}
