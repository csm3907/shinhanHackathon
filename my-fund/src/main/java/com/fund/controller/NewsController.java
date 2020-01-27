package com.fund.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fund.domain.Result;
import com.fund.dto.shinhan.StockInfoNewsdtlDto;
import com.fund.exception.CustomException;
import com.fund.exception.ExceptionCode;
import com.fund.service.shinhan.ApiService;

@RestController
@RequestMapping("/v1.0")
public class NewsController {
	
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@Autowired
	ApiService apiService;

	@GetMapping("/news/{seq}/{date}")
	public Result showNewInfo(HttpServletRequest request, Model model, @PathVariable String seq, @PathVariable String date) {
		
		if(seq == null || seq.isEmpty()) {
			throw new CustomException(ExceptionCode.INVALID_PARAMETER);
		}		
		
		// 뉴스 상세 조회
		JSONObject result = new JSONObject();
		StockInfoNewsdtlDto.ResponseDto stockInfoNewslistDto = apiService.sendRequestForStockNewsInfo("20190924", seq, "E");
		logger.info("getNews_date --> " + stockInfoNewslistDto.getDataBody().getNews_date() + ", getNews_data --> " + stockInfoNewslistDto.getDataBody().getNews_data());
		result.put("date", stockInfoNewslistDto.getDataBody().getNews_date());
		result.put("content", stockInfoNewslistDto.getDataBody().getNews_data());
		
		return new Result(stockInfoNewslistDto.getDataBody(), "success", 200);
	}
}
