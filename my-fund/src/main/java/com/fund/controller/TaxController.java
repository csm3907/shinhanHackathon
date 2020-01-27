package com.fund.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fund.dto.shinhan.ContractVariablesDto;
import com.fund.dto.shinhan.ContractVariablesDto.ResponseDto.DataBody.RetrieveCstVariConList;
import com.fund.dto.shinhan.ContractVariablesDto.ResponseDto.DataBody.RetrieveCstVariConList.RetrieveCstVariConListCstVariConListDTO.RetrieveCstVariConListCstVariConDTO;
import com.fund.dto.shinhan.ContractVariablesDto.ResponseDto.DataBody.RetrieveCstVariConList.RetrieveCstVariConListFundMattListDTO.RetrieveCstVariConListFundMattDTO;
import com.fund.service.shinhan.ApiService;

@Controller
@RequestMapping("/v1.0")
public class TaxController {
	
	@Autowired
	ApiService apiService;
	
	
	@GetMapping("/tax")
	public String showCalcTax(Model model) {
		
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
		
		// 비과세 테스트데이터 세팅
		
		// aside 영역
		model.addAttribute("custNm", custNm);
		model.addAttribute("goodNmList", goodNmList);
		model.addAttribute("fundList", fundList);
		// set jsp file
		model.addAttribute("content", "tax");
		
		return "layout";
	}
}
