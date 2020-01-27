package com.fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fund.domain.Result;
import com.fund.dto.FundDto;
import com.fund.service.FundService;

@RestController
@RequestMapping("/v1.0")
public class FundController {
	
	@Autowired
	FundService fundService;
	
	@GetMapping("/{memberId}/funds")
	public Result getFundList() {
		
		FundDto.ResponseDtoList response = fundService.getFundList(); 
		return new Result(response, "OK", 200);
	}
	
	@GetMapping("/fund/init/kofia")
	public Result initKofia() {
		fundService.saveKofiaInfoFromExcel();
		
		return new Result("", "OK", 200);
	}
	
	@GetMapping("/fund/init/klia")
	public Result initKlia() {
		fundService.saveKliaInfoFromExcel();
		
		return new Result("", "OK", 200);
	}
	
	@GetMapping("/fund/init/sc/profit")
	public Result initScProfit() {
		fundService.saveScProfitSetupDate();
		
		return new Result("", "OK", 200);
	}

	@GetMapping("/fund/init/sc")
	public Result initSc() {
		fundService.saveScFromExcel();;
		
		return new Result("", "OK", 200);
	}
	
	@GetMapping("/fund/init/sc/topItem")
	public Result initScTopItem() {
		fundService.saveScTopItemFromExcel();
		
		return new Result("", "OK", 200);
	}
	
	
	
	@GetMapping("/fund/init/sc/asset")
	public Result initScAsset() {
		fundService.saveScAssetPortfolioFromExcel();
		
		return new Result("", "OK", 200);
	}

}
