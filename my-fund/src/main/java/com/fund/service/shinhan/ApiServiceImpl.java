package com.fund.service.shinhan;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fund.dto.shinhan.ContractVariablesDto;
import com.fund.dto.shinhan.ContractVariablesDto.RequestDto;
import com.fund.dto.shinhan.StockChartDto;
import com.fund.dto.shinhan.StockInfoNewslistDto;
import com.fund.dto.shinhan.StockChartDto.ResponseDto;
import com.fund.exception.CustomException;
import com.fund.exception.ExceptionCode;
import com.fund.dto.shinhan.StockConcstbdSearchDto;
import com.fund.dto.shinhan.StockInfoNewsdtlDto;
import com.fund.service.kakao.KakaoServiceImpl;
import com.fund.util.FileUtil;
import com.fund.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service("ApiService")
public class ApiServiceImpl implements ApiService{
	
	private static final Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);
	
	@Autowired
	UrlUtil urlUtil;
	
	@Value("${shinhan.api.sl.host}")
	private String slHost;
	
	@Value("${shinhan.api.si.host}")
	private String siHost;
	
	private static final String CONTRACT_VARIABLES_URI = "/v1/contract/variables";
	private static final String STOCK_CHART_URI = "/v1/stock/chart";
	private static final String STOCK_NEWS_LIST_URI = "/v1/info/newslist";
	private static final String STOCK_NEWS_INFO_URI = "/v1/info/newsdtl";
	private static final String STOCK_CONCSTBD_SEARCH_URI = "/v1/stock/concstbd/search";
	
	@Override
	public ContractVariablesDto.ResponseDto sendRequestForContractVariables(String rdreNo) {
		
		JSONObject param = new JSONObject();
		JSONObject dataBody = new JSONObject();
		dataBody.put("workSc", "01");
		dataBody.put("rdreNo", rdreNo); //암호화된 주민번호
		param.put("dataHeader", new JSONObject());
		param.put("dataBody", dataBody);
		
		
		ObjectMapper mapper = new ObjectMapper();
		HttpURLConnection con = null;
		ContractVariablesDto.ResponseDto responseDto = null;
		try {
			/*
			 * 신한해커톤 종료로 API 호출 불가
			 * con = urlUtil.sendPost(slHost + CONTRACT_VARIABLES_URI, param.toString());
			 * String response = urlUtil.getResponseMessage(con);
			 */
			String response = FileUtil.getFileContent("/shinhan/ContractVariablesResponse");
			logger.info("response ==>" + response );
			responseDto = new Gson().fromJson(response,ContractVariablesDto.ResponseDto.class);
			String jsonStr = mapper.writeValueAsString(responseDto);
			logger.info("responseDto ==>" + jsonStr );
		} catch (IOException e) {
			throw new CustomException(ExceptionCode.SENDING_REQUEST_FAILED);
		}
		
		return responseDto;
	}
	
	@Override
	public ResponseDto sendRequestForStockChart(String code, String qryStrtYmd, String qryEndYmd) {
		
		JSONObject param = new JSONObject();
		JSONObject dataBody = new JSONObject();
		JSONObject dataHeader = new JSONObject();
		dataBody.put("code", code);
		dataBody.put("chart_gubun", "D");
		dataBody.put("time_cls", "001");
		dataBody.put("qry_strt_ymd", qryStrtYmd);
		dataBody.put("qry_end_ymd", qryEndYmd);
		dataBody.put("rows", "99");
		dataBody.put("repeatKey", "");
		
		param.put("dataHeader", dataHeader);
		param.put("dataBody", dataBody);
		
		ObjectMapper mapper = new ObjectMapper();
		HttpURLConnection con = null;
		StockChartDto.ResponseDto responseDto = null;
		try {
			/*
			 * 신한해커톤 종료로 API 호출 불가
			 * con = urlUtil.sendPost(siHost + STOCK_CHART_URI, param.toString()); String
			 * response = urlUtil.getResponseMessage(con);
			 */
			String response = FileUtil.getFileContent("/shinhan/StockChartResponse");
			responseDto = new Gson().fromJson(response,StockChartDto.ResponseDto.class);
			String jsonStr = mapper.writeValueAsString(responseDto);
			logger.info("responseDto ==>" + jsonStr );
		} catch (IOException e) {
			throw new CustomException(ExceptionCode.SENDING_REQUEST_FAILED);
		}
		
		return responseDto;
	}
	
	
	@Override
	public StockInfoNewslistDto.ResponseDto sendRequestForStockNewsList() {
		
		JSONObject param = new JSONObject();
		JSONObject dataBody = new JSONObject();
		JSONObject dataHeader = new JSONObject();
		dataBody.put("code", "");
		dataBody.put("gubun", "");
		dataBody.put("date", "");
		
		param.put("dataHeader", dataHeader);
		param.put("dataBody", dataBody);
		
		ObjectMapper mapper = new ObjectMapper();
		HttpURLConnection con = null;
		StockInfoNewslistDto.ResponseDto responseDto = null;
		try {
			/*
			 * 신한해커톤 종료로 API 호출 불가
			 * con = urlUtil.sendPost(siHost + STOCK_NEWS_LIST_URI, param.toString());
			 * String response = urlUtil.getResponseMessage(con);
			 */
			String response = FileUtil.getFileContent("/shinhan/StockInfoNewslistResponse");
			responseDto = new Gson().fromJson(response,StockInfoNewslistDto.ResponseDto.class);
			String jsonStr = mapper.writeValueAsString(responseDto);
			logger.info("responseDto ==>" + jsonStr );
		} catch (IOException e) {
			throw new CustomException(ExceptionCode.SENDING_REQUEST_FAILED);
		}
		
		return responseDto;
	}
	
	@Override
	public StockInfoNewsdtlDto.ResponseDto sendRequestForStockNewsInfo(String date, String seq, String newsCls) {
		JSONObject param = new JSONObject();
		JSONObject dataBody = new JSONObject();
		JSONObject dataHeader = new JSONObject();
		dataBody.put("date", date);
		dataBody.put("news_seqn", seq);
		dataBody.put("news_cls", newsCls);
		
		param.put("dataHeader", dataHeader);
		param.put("dataBody", dataBody);
		
		ObjectMapper mapper = new ObjectMapper();
		HttpURLConnection con = null;
		StockInfoNewsdtlDto.ResponseDto responseDto = null;
		try {
			/*
			 * 신한해커톤 종료로 API 호출 불가
			 * con = urlUtil.sendPost(siHost + STOCK_NEWS_INFO_URI, param.toString());
			 * String response = urlUtil.getResponseMessage(con);
			 */
			String response = FileUtil.getFileContent("/shinhan/StockInfoNewsdtlResponse");
			responseDto = new Gson().fromJson(response, StockInfoNewsdtlDto.ResponseDto.class);
			String jsonStr = mapper.writeValueAsString(responseDto);
			logger.info("responseDto ==>" + jsonStr );
		} catch (IOException e) {
			throw new CustomException(ExceptionCode.SENDING_REQUEST_FAILED);
		}
		
		return responseDto;
	}
	
	@Override
	public StockConcstbdSearchDto.ResponseDto sendRequestForStockConcstbdSearch() {
		
		JSONObject param = new JSONObject();
		JSONObject dataBody = new JSONObject();
		JSONObject dataHeader = new JSONObject();
		
		param.put("dataHeader", dataHeader);
		param.put("dataBody", dataBody);
		
		ObjectMapper mapper = new ObjectMapper();
		HttpURLConnection con = null;
		StockConcstbdSearchDto.ResponseDto responseDto = null;
		try {
			/*
			 * 신한해커톤 종료로 API 호출 불가
			 * con = urlUtil.sendPost(siHost + STOCK_CONCSTBD_SEARCH_URI, param.toString());
			 * String response = urlUtil.getResponseMessage(con);
			 */
			String response = FileUtil.getFileContent("/shinhan/StockConcstbdSearchResponse");
			responseDto = new Gson().fromJson(response, StockConcstbdSearchDto.ResponseDto.class);
			String jsonStr = mapper.writeValueAsString(responseDto);
			logger.info("responseDto ==>" + jsonStr );
		} catch (IOException e) {
			throw new CustomException(ExceptionCode.SENDING_REQUEST_FAILED);
		}
		
		return responseDto;
	}
}
