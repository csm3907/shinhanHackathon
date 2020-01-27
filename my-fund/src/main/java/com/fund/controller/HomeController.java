package com.fund.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fund.domain.Fund;
import com.fund.domain.FundDetail;
import com.fund.domain.KliaFund;
import com.fund.dto.FundDetailTopItemDto;
import com.fund.dto.shinhan.ContractVariablesDto;
import com.fund.dto.shinhan.ContractVariablesDto.ResponseDto.DataBody.RetrieveCstVariConList;
import com.fund.dto.shinhan.ContractVariablesDto.ResponseDto.DataBody.RetrieveCstVariConList.RetrieveCstVariConListCstVariConListDTO.RetrieveCstVariConListCstVariConDTO;
import com.fund.dto.shinhan.ContractVariablesDto.ResponseDto.DataBody.RetrieveCstVariConList.RetrieveCstVariConListFundMattListDTO.RetrieveCstVariConListFundMattDTO;
import com.fund.exception.CustomException;
import com.fund.exception.ExceptionCode;
import com.fund.service.FundDetailAssetPortfolioService;
import com.fund.service.FundDetailService;
import com.fund.service.FundDetailStandardProfitService;
import com.fund.service.FundDetailTopItemService;
import com.fund.service.FundService;
import com.fund.service.FundSetupProfitService;
import com.fund.service.KliaService;
import com.fund.service.shinhan.ApiService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	FundService fundService;

	@Autowired
	ApiService apiService;

	@Autowired
	FundDetailService fundDetailService;

	@Autowired
	FundSetupProfitService fundSetupProfitService;

	@Autowired
	FundDetailStandardProfitService fundDetailStandardProfitService;

	@Autowired
	FundDetailAssetPortfolioService fundDetailAssetPortfolioService;

	@Autowired
	FundDetailTopItemService fundDetailTopItemService;

	@Autowired
	KliaService kliaService;

	@GetMapping("")
	public String showIndex(Model model) {

		// 1. 변액보험계약 조회
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
			// 실제 fundNm 리턴 값 => "fundNm": "V0047 : TOPS 펀더멘탈 인덱스형                                                                     ", 가공 필요
			String name = retrieveCstVariConListCstVariConDTO.getFundNm().trim();
			if (name.contains(":")) {
				fund.put("name", name.split(":")[1].trim());
			} else {
				fund.put("name", name);
			}
			fund.put("scCode", retrieveCstVariConListCstVariConDTO.getFundCd().trim());
			fundList.add(fund);
		}

		// 2. 대표 펀드 내용 조회
		Fund fund = fundService.findByScCode((String) fundList.get(0).get("scCode"));
		FundDetail fundDetail = fundDetailService.findFundDetail(fund.getId()); // 펀드 상세 내용 조회
		JSONArray assetPortfolo = fundDetailAssetPortfolioService.getAssetPortfolioList(fundDetail); // 펀드 자상구성내역 조회
		JSONArray standardProfit = fundDetailStandardProfitService.getStandardProfit(fundDetail); // 펀드 기준일 기준 수익률 조회
		JSONArray setupProfitList = fundService.getSetupProfitListByScCode(fund.getScCode()); // 펀드 설정일 기준 수익률 조회
		List<FundDetailTopItemDto.ResponseDto> topItems = fundDetailTopItemService.getTopItems(fundDetail); // 펀드 상위
																											// 10종목 조회
		JSONObject fundInfo = fundDetailService.getFundInfo(fund.getId()); // 펀드 기초정보 조회

		// 3. set Model
		// aside 영역
		model.addAttribute("custNm", custNm);
		model.addAttribute("goodNmList", goodNmList);
		model.addAttribute("fundList", fundList);
		// 메인 콘텐츠 영역
		model.addAttribute("assetPortfolo", assetPortfolo);
		model.addAttribute("standardProfit", standardProfit);
		model.addAttribute("topItems", topItems);
		model.addAttribute("fund", fundInfo);
		model.addAttribute("setupProfitList", setupProfitList);
		// set jsp file
		model.addAttribute("content", "index");

		return "layout";
	}

	@GetMapping("/v1.0")
	public String showIndex(HttpServletRequest request, Model model) {

		// 1. 변액보험계약 조회(aside 영역)
		ContractVariablesDto.ResponseDto responseDto = apiService.sendRequestForContractVariables("WmokLBDCO9/yfihlYoJFyg==");
		RetrieveCstVariConList retrieveCstVariConList = responseDto.getDataBody().getRetrieveCstVariConList();

		String custNm = retrieveCstVariConList.getCstNm(); // 고객명
		List<String> goodNmList = new ArrayList<String>(); // 가입한 보험상품명 리스트
		for (RetrieveCstVariConListCstVariConDTO retrieveCstVariConListCstVariConDTO : retrieveCstVariConList.getRetrieveCstVariConListCstVariConListDTO().getRetrieveCstVariConListCstVariConDTO()) {
			String goodNm = retrieveCstVariConListCstVariConDTO.getGoodNm().replaceFirst("무배당", "").trim();
			goodNmList.add(goodNm);
		}

		List<Map<String, Object>> fundList = new ArrayList<Map<String, Object>>(); // 적립한 보험상품명 리스트
		for (RetrieveCstVariConListFundMattDTO retrieveCstVariConListCstVariConDTO : retrieveCstVariConList.getRetrieveCstVariConListFundMattListDTO().getRetrieveCstVariConListFundMattDTO()) {
			Map<String, Object> fund = new HashMap<String, Object>();
			// 실제 fundNm 리턴 값 => "fundNm": "V0047 : TOPS 펀더멘탈 인덱스형                                                                      ", 가공 필요 
			String name = retrieveCstVariConListCstVariConDTO.getFundNm().trim();
			if(name.contains(":")) {
				fund.put("name", name.split(":")[1].trim());
			}else {
				fund.put("name", name);
			}
			fund.put("scCode", retrieveCstVariConListCstVariConDTO.getFundCd().trim());
			fundList.add(fund);
		}

		// 2. 대표 펀드 내용 조회
		Fund fund = fundService.findByScCode((String) fundList.get(0).get("scCode"));
		FundDetail fundDetail = fundDetailService.findFundDetail(fund.getId()); // 펀드 상세 내용 조회
		JSONArray assetPortfolo = fundDetailAssetPortfolioService.getAssetPortfolioList(fundDetail); // 펀드 자상구성내역 조회
		JSONArray standardProfit = fundDetailStandardProfitService.getStandardProfit(fundDetail); // 펀드 기준일 기준 수익률 조회
		JSONArray setupProfitList = fundService.getSetupProfitListByScCode(fund.getScCode()); // 펀드 설정일 기준 수익률 조회
		List<FundDetailTopItemDto.ResponseDto> topItems = fundDetailTopItemService.getTopItems(fundDetail); // 펀드 상위 10종목 조회
		JSONObject fundInfo = fundDetailService.getFundInfo(fund.getId()); // 펀드 기초정보 조회

		// 3. set Model
		// aside 영역
		model.addAttribute("custNm", custNm);
		model.addAttribute("goodNmList", goodNmList);
		model.addAttribute("fundList", fundList);
		// 메인 콘텐츠 영역
		model.addAttribute("standardDate", fundInfo.getString("standardDate"));
		model.addAttribute("assetPortfolo", assetPortfolo);
		model.addAttribute("standardProfit", standardProfit);
		model.addAttribute("topItems", topItems);
		model.addAttribute("fund", fundInfo);
		model.addAttribute("setupProfitList", setupProfitList);
		// set jsp file
		model.addAttribute("content", "index");

		return "layout";
	}

	@GetMapping("/v1.0/{scCode}")
	public String showIndexByScCode(Model model, @PathVariable String scCode) {

		if (scCode == null || scCode.isEmpty()) {
			throw new CustomException(ExceptionCode.INVALID_PARAMETER);
		}

		// 1. 변액보험계약 조회(aside 영역)
		ContractVariablesDto.ResponseDto responseDto = apiService.sendRequestForContractVariables("WmokLBDCO9/yfihlYoJFyg==");
		RetrieveCstVariConList retrieveCstVariConList = responseDto.getDataBody().getRetrieveCstVariConList();

		String custNm = retrieveCstVariConList.getCstNm(); // 고객명
		List<String> goodNmList = new ArrayList<String>(); // 가입한 보험상품명 리스트
		for (RetrieveCstVariConListCstVariConDTO retrieveCstVariConListCstVariConDTO : retrieveCstVariConList.getRetrieveCstVariConListCstVariConListDTO().getRetrieveCstVariConListCstVariConDTO()) {
			String goodNm = retrieveCstVariConListCstVariConDTO.getGoodNm().replaceFirst("무배당", "").trim();
			goodNmList.add(goodNm);
		}

		List<Map<String, Object>> fundList = new ArrayList<Map<String, Object>>(); // 적립한 보험상품명 리스트
		for (RetrieveCstVariConListFundMattDTO retrieveCstVariConListCstVariConDTO : retrieveCstVariConList.getRetrieveCstVariConListFundMattListDTO().getRetrieveCstVariConListFundMattDTO()) {
			Map<String, Object> fund = new HashMap<String, Object>();
			// 실제 fundNm 리턴 값 => "fundNm": "V0047 : TOPS 펀더멘탈 인덱스형                                                                     ", 가공 필요
			String name = retrieveCstVariConListCstVariConDTO.getFundNm().trim();
			if (name.contains(":")) {
				fund.put("name", name.split(":")[1].trim());
			} else {
				fund.put("name", name);
			}
			fund.put("scCode", retrieveCstVariConListCstVariConDTO.getFundCd().trim());
			fundList.add(fund);
		}

		// 2. 대표 펀드 내용 조회
		Fund fund = fundService.findByScCode((String) fundList.get(0).get("scCode"));
		FundDetail fundDetail = fundDetailService.findFundDetail(fund.getId()); // 펀드 상세 내용 조회
		JSONArray assetPortfolo = fundDetailAssetPortfolioService.getAssetPortfolioList(fundDetail); // 펀드 자상구성내역 조회
		JSONArray standardProfit = fundDetailStandardProfitService.getStandardProfit(fundDetail); // 펀드 기준일 기준 수익률 조회
		JSONArray setupProfitList = fundService.getSetupProfitListByScCode(fund.getScCode()); // 펀드 설정일 기준 수익률 조회
		List<FundDetailTopItemDto.ResponseDto> topItems = fundDetailTopItemService.getTopItems(fundDetail); // 펀드 상위 10종목 조회
		JSONObject fundInfo = fundDetailService.getFundInfo(fund.getId()); // 펀드 기초정보 조회

		// 3. set Model
		// aside 영역
		model.addAttribute("custNm", custNm);
		model.addAttribute("goodNmList", goodNmList);
		model.addAttribute("fundList", fundList);
		// 메인 콘텐츠 영역
		model.addAttribute("standardDate", fundInfo.getString("standardDate"));
		model.addAttribute("assetPortfolo", assetPortfolo);
		model.addAttribute("standardProfit", standardProfit);
		model.addAttribute("topItems", topItems);
		model.addAttribute("fund", fundInfo);
		model.addAttribute("setupProfitList", setupProfitList);
		// set jsp file
		model.addAttribute("content", "index");

		return "layout";
	}

	// 생명보험협회(klia) 펀드 화면
	@GetMapping("/v1.0/klia/{id}")
	public String showIndexByKliaFund(Model model, @PathVariable String id) {

		if (id == null || id.isEmpty()) {
			throw new CustomException(ExceptionCode.INVALID_PARAMETER);
		}

		// 1. 변액보험계약 조회(aside 영역)
		ContractVariablesDto.ResponseDto responseDto = apiService.sendRequestForContractVariables("WmokLBDCO9/yfihlYoJFyg==");
		RetrieveCstVariConList retrieveCstVariConList = responseDto.getDataBody().getRetrieveCstVariConList();

		String custNm = retrieveCstVariConList.getCstNm(); // 고객명
		List<String> goodNmList = new ArrayList<String>(); // 가입한 보험상품명 리스트
		for (RetrieveCstVariConListCstVariConDTO retrieveCstVariConListCstVariConDTO : retrieveCstVariConList.getRetrieveCstVariConListCstVariConListDTO().getRetrieveCstVariConListCstVariConDTO()) {
			String goodNm = retrieveCstVariConListCstVariConDTO.getGoodNm().replaceFirst("무배당", "").trim();
			goodNmList.add(goodNm);
		}

		List<Map<String, Object>> fundList = new ArrayList<Map<String, Object>>(); // 적립한 보험상품명 리스트
		for (RetrieveCstVariConListFundMattDTO retrieveCstVariConListCstVariConDTO : retrieveCstVariConList.getRetrieveCstVariConListFundMattListDTO().getRetrieveCstVariConListFundMattDTO()) {
			Map<String, Object> fund = new HashMap<String, Object>();
			// 실제 fundNm 리턴 값 => "fundNm": "V0047 : TOPS 펀더멘탈 인덱스형                                                                     ", 가공 필요
			String name = retrieveCstVariConListCstVariConDTO.getFundNm().trim();
			if (name.contains(":")) {
				fund.put("name", name.split(":")[1].trim());
			} else {
				fund.put("name", name);
			}
			fund.put("scCode", retrieveCstVariConListCstVariConDTO.getFundCd().trim());
			fundList.add(fund);
		}

		KliaFund kliaFund = kliaService.findKliaFund(id);
		JSONArray setupProfitList = kliaService.getSetupProfitListByKliaFund(kliaFund);
		JSONObject fundInfo = kliaService.setAsJson(kliaFund);

		Fund fund = fundService.findByScCode("V0042");
		FundDetail fundDetail = fundDetailService.findFundDetail(fund.getId());
		JSONArray assetPortfolo = fundDetailAssetPortfolioService.getAssetPortfolioList(fundDetail);
		JSONArray standardProfit = fundDetailStandardProfitService.getStandardProfit(fundDetail);
		List<FundDetailTopItemDto.ResponseDto> topItems = fundDetailTopItemService.getTopItems(fundDetail);

		// 3. set Model
		// aside 영역
		model.addAttribute("custNm", custNm);
		model.addAttribute("goodNmList", goodNmList);
		model.addAttribute("fundList", fundList);
		// 메인 콘텐츠 영역
		model.addAttribute("assetPortfolo", assetPortfolo);
		model.addAttribute("standardProfit", standardProfit);
		model.addAttribute("topItems", topItems);
		model.addAttribute("fund", fundInfo);
		model.addAttribute("setupProfitList", setupProfitList);
		// set jsp file
		model.addAttribute("content", "index");

		return "layout";
	}
}
