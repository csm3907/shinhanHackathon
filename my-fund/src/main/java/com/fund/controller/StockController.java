package com.fund.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fund.domain.FundDetail;
import com.fund.domain.Result;
import com.fund.dto.shinhan.ContractVariablesDto;
import com.fund.dto.shinhan.ContractVariablesDto.ResponseDto.DataBody.RetrieveCstVariConList;
import com.fund.dto.shinhan.ContractVariablesDto.ResponseDto.DataBody.RetrieveCstVariConList.RetrieveCstVariConListCstVariConListDTO.RetrieveCstVariConListCstVariConDTO;
import com.fund.dto.shinhan.ContractVariablesDto.ResponseDto.DataBody.RetrieveCstVariConList.RetrieveCstVariConListFundMattListDTO.RetrieveCstVariConListFundMattDTO;
import com.fund.dto.shinhan.StockChartDto;
import com.fund.dto.shinhan.StockChartDto.ResponseDto.DataBody.StockTransitionList;
import com.fund.dto.shinhan.StockConcstbdSearchDto;
import com.fund.dto.shinhan.StockInfoNewslistDto;
import com.fund.dto.shinhan.StockInfoNewslistDto.ResponseDto.DataBody.Item;
import com.fund.exception.CustomException;
import com.fund.exception.ExceptionCode;
import com.fund.service.FundDetailService;
import com.fund.service.FundDetailTopItemService;
import com.fund.service.FundService;
import com.fund.service.shinhan.ApiService;
import com.fund.util.FileUtil;
import com.google.gson.Gson;

@Controller
@RequestMapping("/v1.0/stocks")
public class StockController {
	
	@Autowired
	ApiService apiService;
	
	@Autowired
	FundService fundService;
	
	@Autowired
	FundDetailService fundDetailService;
	
	@Autowired
	FundDetailTopItemService fundDetailTopItemService;
	
	
	@GetMapping("/{code}")
	public String showStockInfo(HttpServletRequest request, Model model, @PathVariable String code) {
		
		if(code == null || code.isEmpty()) {
			throw new CustomException(ExceptionCode.INVALID_PARAMETER);
		}
		
		// 1. 변액보험계약 조회(aside 영역)
		ContractVariablesDto.ResponseDto responseDto = apiService.sendRequestForContractVariables("WmokLBDCO9/yfihlYoJFyg==");
		RetrieveCstVariConList retrieveCstVariConList = responseDto.getDataBody().getRetrieveCstVariConList();

		String custNm = retrieveCstVariConList.getCstNm(); // 고객명
		List<String> goodNmList = new ArrayList<String>(); // 가입한 보험상품명 리스트
		for (RetrieveCstVariConListCstVariConDTO retrieveCstVariConListCstVariConDTO : retrieveCstVariConList
				.getRetrieveCstVariConListCstVariConListDTO().getRetrieveCstVariConListCstVariConDTO()) {
			String goodNm = retrieveCstVariConListCstVariConDTO.getGoodNm().replaceFirst("무배당", "").trim();
			goodNmList.add(goodNm);
		}

		List<Map<String, Object>> fundList = new ArrayList<Map<String, Object>>(); // 적립한 보험상품명 리스트
		for (RetrieveCstVariConListFundMattDTO retrieveCstVariConListCstVariConDTO : retrieveCstVariConList
				.getRetrieveCstVariConListFundMattListDTO().getRetrieveCstVariConListFundMattDTO()) {
			Map<String, Object> fund = new HashMap<String, Object>();
			// 실제 fundNm 리턴 값 => "fundNm": "V0047 : TOPS 펀더멘탈 인덱스형 ", 가공 필요
			String name = retrieveCstVariConListCstVariConDTO.getFundNm().trim();
			if (name.contains(":")) {
				fund.put("name", name.split(":")[1].trim());
			} else {
				fund.put("name", name);
			}
			fund.put("scCode", retrieveCstVariConListCstVariConDTO.getFundCd().trim());
			fundList.add(fund);
		}
		
		// 2. 종목 현재가 추이 
		StockChartDto.ResponseDto stockChartResponseDto = apiService.sendRequestForStockChart(code, "20170901", "20170911");
		List<StockTransitionList> stockTransitionList = stockChartResponseDto.getDataBody().getStockTransitionList();
		JSONArray stockProfitList = new JSONArray();
		for(StockTransitionList stockTransition : stockTransitionList) {
			JSONObject stockProfit = new JSONObject();
			stockProfit.put("price", stockTransition.getTrdprc_1());
			stockProfit.put("date", stockTransition.getTrade_date());
			stockProfitList.put(stockProfit);
		}
		
		// 3. 뉴스 목록 조회
		StockInfoNewslistDto.ResponseDto stockInfoNewslistDto = apiService.sendRequestForStockNewsList();
		List<Item> newsList = stockInfoNewslistDto.getDataBody().getList();
		// 언론사 임의세팅
		String[] press = {"매일경제", "중앙일보", "머니투데이", "서울경제", "한국경제","MBN", "주간일보", "세계일보", "재경일보", "한국경제"};
		for(int i=0; i< newsList.size(); i++) {
			newsList.get(i).setPress(press[i%10]);
		}
		
		// aside 영역
		model.addAttribute("custNm", custNm);
		model.addAttribute("goodNmList", goodNmList);
		model.addAttribute("fundList", fundList);
		
		// 메인 콘텐츠 영역
		model.addAttribute("stockProfitList", stockProfitList); // 종목 현재가 추이
		model.addAttribute("newsList", newsList); // 종목 현재가 추이
		// set jsp file
		model.addAttribute("content", "stockView");
		
		return "layout";
	}
	
	@GetMapping("/favorite")
	public String showConcstbdSearch(Model model) {
		
		// 1. 변액보험계약 조회(aside 영역)
		ContractVariablesDto.ResponseDto responseDto = apiService
				.sendRequestForContractVariables("WmokLBDCO9/yfihlYoJFyg==");
		RetrieveCstVariConList retrieveCstVariConList = responseDto.getDataBody().getRetrieveCstVariConList();

		String custNm = retrieveCstVariConList.getCstNm(); // 고객명
		List<String> goodNmList = new ArrayList<String>(); // 가입한 보험상품명 리스트
		for (RetrieveCstVariConListCstVariConDTO retrieveCstVariConListCstVariConDTO : retrieveCstVariConList
				.getRetrieveCstVariConListCstVariConListDTO().getRetrieveCstVariConListCstVariConDTO()) {
			String goodNm = retrieveCstVariConListCstVariConDTO.getGoodNm().replaceFirst("무배당", "").trim();
			goodNmList.add(goodNm);
		}

		List<Map<String, Object>> fundList = new ArrayList<Map<String, Object>>(); // 적립한 보험상품명 리스트
		for (RetrieveCstVariConListFundMattDTO retrieveCstVariConListCstVariConDTO : retrieveCstVariConList
				.getRetrieveCstVariConListFundMattListDTO().getRetrieveCstVariConListFundMattDTO()) {
			Map<String, Object> fund = new HashMap<String, Object>();
			// 실제 fundNm 리턴 값 => "fundNm": "V0047 : TOPS 펀더멘탈 인덱스형 ", 가공 필요
			String name = retrieveCstVariConListCstVariConDTO.getFundNm().trim();
			if (name.contains(":")) {
				fund.put("name", name.split(":")[1].trim());
			} else {
				fund.put("name", name);
			}
			fund.put("scCode", retrieveCstVariConListCstVariConDTO.getFundCd().trim());
			fundList.add(fund);
		}
		
		// 관심종목 목록 조회
		StockConcstbdSearchDto.ResponseDto stockConsDto = apiService.sendRequestForStockConcstbdSearch();
		List<StockConcstbdSearchDto.ResponseDto.DataBody.Item> itemList = stockConsDto.getDataBody().getList();
		for(StockConcstbdSearchDto.ResponseDto.DataBody.Item item : itemList) {
			Random random = new Random();
			item.setPrice(random.nextInt(10000) + 100);
			item.setFluctuationPrice(random.nextInt(100));
			item.setIncrease(random.nextBoolean());
		}
		
		// 관심종목 등록 가능한 테스트 데이터 세팅
		String consctList= null;
		try {
			consctList = FileUtil.getFileContent("/shinhan/StockConcstbdSearchTestData");
		} catch (IOException e) {
			throw new CustomException(ExceptionCode.FILE_NOT_FOUND);
		}
		StockConcstbdSearchDto.ResponseDto concstbdList = new Gson().fromJson(consctList, StockConcstbdSearchDto.ResponseDto.class);
		List<StockConcstbdSearchDto.ResponseDto.DataBody.Item> concstbd = concstbdList.getDataBody().getList();
		
		// aside 영역
		model.addAttribute("custNm", custNm);
		model.addAttribute("goodNmList", goodNmList);
		model.addAttribute("fundList", fundList);
		// 메인 콘텐츠 영역
		model.addAttribute("itemList", itemList);
		model.addAttribute("concstbd", concstbd);
		// set jsp file
		model.addAttribute("content", "favorite");
				
		return "layout";
	}
	
	@ResponseBody 
	@GetMapping("/favorite/{name}")
	public Object registerConcstbd(Model model, @PathVariable String name) {
		List<FundDetail> fundDetails = fundDetailTopItemService.getFundDetailListByName(name);
		
		return fundService.getFundNameList(fundDetails).toString();
	}
	

}
