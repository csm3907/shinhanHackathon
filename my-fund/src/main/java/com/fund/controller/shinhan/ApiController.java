package com.fund.controller.shinhan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fund.domain.Result;
import com.fund.dto.shinhan.ContractVariablesDto;
import com.fund.dto.shinhan.StockChartDto;
import com.fund.dto.shinhan.StockConcstbdSearchDto;
import com.fund.dto.shinhan.StockInfoNewsdtlDto;
import com.fund.dto.shinhan.StockInfoNewsdtlDto.RequestDto;
import com.fund.dto.shinhan.StockInfoNewslistDto;
import com.fund.dto.shinhan.StockSearchDto;
import com.fund.dto.shinhan.StockTrdprcTrendDto;
import com.fund.dto.shinhan.VariablesRoiRatioDto;
import com.fund.dto.shinhan.VariablesRoiRatioDto.RequestDto.DataBody;
import com.fund.service.shinhan.ApiService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/v1.0/api")
public class ApiController {
	
	@Autowired
	ApiService apiService;

	@GetMapping(value="/contract/variables/roi-ratio")
	public Object testVariablesRoiRatioApi() throws ParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		VariablesRoiRatioDto.RequestDto requestDto = new VariablesRoiRatioDto.RequestDto();
		DataBody dataBody = new DataBody();
		dataBody.setInonNo("12hRBH91VMe6SH1TdQN0EQ==");
		dataBody.setWorkSc("02");
		requestDto.setDatabody(dataBody);

		ClassPathResource cPathResource = new ClassPathResource("/shinhan/VariablesRoiRatioResponse");
		byte[] byteArr = FileCopyUtils.copyToByteArray(cPathResource.getInputStream());
		String str = new String(byteArr, StandardCharsets.UTF_8);
		// BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Documents\\shinhan-hackathon\\my-fund\\src\\main\\java\\com\\fund\\dto\\shinhan\\example\\VariablesRoiRatioResponse"));
		VariablesRoiRatioDto.ResponseDto responseDto = new Gson().fromJson(str, VariablesRoiRatioDto.ResponseDto.class);

		String jsonStr = mapper.writeValueAsString(responseDto); 
		JSONParser parser = new JSONParser(jsonStr);
		return  parser.parse();	
	}
	
	@GetMapping(value="/stock/trdprc-trend")
	public Object testStockTrdprcTrendApi() throws JsonProcessingException, ParseException, FileNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Documents\\shinhan-hackathon\\my-fund\\src\\main\\java\\com\\fund\\dto\\shinhan\\example\\StockTrdprcTrendResponse"));
		StockTrdprcTrendDto.ResponseDto responseDto = new Gson().fromJson(br, StockTrdprcTrendDto.ResponseDto.class);
	
		String jsonStr = mapper.writeValueAsString(responseDto); 
		JSONParser parser = new JSONParser(jsonStr);
		return  parser.parse();	
	}
	
	@GetMapping("/stock/chart")
	public Object testStockChartApi() throws JsonProcessingException, ParseException, FileNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Documents\\shinhan-hackathon\\my-fund\\src\\main\\java\\com\\fund\\dto\\shinhan\\example\\StockChartResponse"));
		StockChartDto.ResponseDto responseDto = new Gson().fromJson(br, StockChartDto.ResponseDto.class);
			
		String jsonStr = mapper.writeValueAsString(responseDto); 
		JSONParser parser = new JSONParser(jsonStr);
		return  parser.parse();	
	}
	
	@GetMapping("/stock/search")
	public Object testStockSearchApi() throws JsonProcessingException, ParseException, FileNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Documents\\shinhan-hackathon\\my-fund\\src\\main\\java\\com\\fund\\dto\\shinhan\\example\\StockSearchResponse"));
		StockSearchDto.ResponseDto responseDto = new Gson().fromJson(br, StockSearchDto.ResponseDto.class);
			
		String jsonStr = mapper.writeValueAsString(responseDto); 
		JSONParser parser = new JSONParser(jsonStr);
		return  parser.parse();	
	}
	
	@GetMapping("/stock/concstbd/search")
	public Object testStockConcstbdSearchhApi() throws JsonProcessingException, ParseException, FileNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Documents\\shinhan-hackathon\\my-fund\\src\\main\\java\\com\\fund\\dto\\shinhan\\example\\StockConcstbdSearchResponse"));
		StockConcstbdSearchDto.ResponseDto responseDto = new Gson().fromJson(br, StockConcstbdSearchDto.ResponseDto.class);
			
		String jsonStr = mapper.writeValueAsString(responseDto); 
		JSONParser parser = new JSONParser(jsonStr);
		return  parser.parse();	
	}
	
	@GetMapping("/stock/info/newsdtl")
	public Object testStockInfoNewsdtlhApi() throws JsonProcessingException, ParseException, FileNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		
		StockInfoNewsdtlDto.RequestDto requestDto = new RequestDto();
		
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Documents\\shinhan-hackathon\\my-fund\\src\\main\\java\\com\\fund\\dto\\shinhan\\example\\StockInfoNewsdtlResponse"));
		StockInfoNewsdtlDto.ResponseDto responseDto = new Gson().fromJson(br, StockInfoNewsdtlDto.ResponseDto.class);
			
		String jsonStr = mapper.writeValueAsString(responseDto); 
		JSONParser parser = new JSONParser(jsonStr);
		return  parser.parse();	
	}
	
	@GetMapping("/stock/info/newslist")
	public Object testStockInfoNewslistApi() throws JsonProcessingException, ParseException, FileNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Documents\\shinhan-hackathon\\my-fund\\src\\main\\java\\com\\fund\\dto\\shinhan\\example\\StockInfoNewslistResponse"));
		StockInfoNewslistDto.ResponseDto responseDto = new Gson().fromJson(br, StockInfoNewslistDto.ResponseDto.class);
			
		String jsonStr = mapper.writeValueAsString(responseDto); 
		JSONParser parser = new JSONParser(jsonStr);
		return  parser.parse();	
	}
	
	@GetMapping("/contract/variables")
	public Object testContractVariablesApi() throws JsonProcessingException, ParseException, FileNotFoundException {
		ContractVariablesDto.RequestDto dto = new ContractVariablesDto.RequestDto();
		com.fund.dto.shinhan.ContractVariablesDto.RequestDto.DataBody dataBody = new com.fund.dto.shinhan.ContractVariablesDto.RequestDto.DataBody();
		dataBody.setRdreNo("WmokLBDCO9/yfihlYoJFyg==");
		dataBody.setWorkSc("01");
		dto.setDataBody(dataBody);
		ContractVariablesDto.ResponseDto responseDto = apiService.sendRequestForContractVariables("WmokLBDCO9/yfihlYoJFyg==");
		
		/*
		 * ObjectMapper mapper = new ObjectMapper();
		 * 
		 * BufferedReader br = new BufferedReader(new FileReader(
		 * "C:\\Users\\user\\Documents\\shinhan-hackathon\\my-fund\\src\\main\\java\\com\\fund\\dto\\shinhan\\example\\ContractVariablesResponse"
		 * )); ContractVariablesDto.ResponseDto responseDto = new Gson().fromJson(br,
		 * ContractVariablesDto.ResponseDto.class);
		 * 
		 * String jsonStr = mapper.writeValueAsString(responseDto); JSONParser parser =
		 * new JSONParser(jsonStr);
		 */
		return  new Result(responseDto,"success",200);	
	}
}
