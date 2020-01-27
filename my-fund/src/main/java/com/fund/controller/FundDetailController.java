package com.fund.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fund.domain.FundDetail;
import com.fund.domain.Result;
import com.fund.dto.FundDetailTopItemDto.ResponseDto;
import com.fund.service.FundDetailAssetPortfolioService;
import com.fund.service.FundDetailService;
import com.fund.service.FundDetailStandardProfitService;
import com.fund.service.FundDetailTopItemService;

@RestController
@RequestMapping("/v1.0")
public class FundDetailController {
	
	@Autowired
	FundDetailService fundDetailService;
	
	@Autowired
	FundDetailStandardProfitService fundDetailStandardProfitService;
	
	@Autowired
	FundDetailAssetPortfolioService fundDetailAssetPortfolioService;
	
	@Autowired
	FundDetailTopItemService fundDetailTopItemService;
	
	
	@GetMapping("/{fundDetailId}/asset")
	public Result getAssetPortfolo(@PathVariable Long fundDetailId) {
		
		FundDetail fundDetail = fundDetailService.findFundDetail(fundDetailId);
		JSONArray result = fundDetailAssetPortfolioService.getAssetPortfolioList(fundDetail);
		return new Result(result.toString(), "success", 200);
	}
	
	@GetMapping("/{fundDetailId}/profit")
	public Result getStandardProfit(@PathVariable Long fundDetailId) {
		
		FundDetail fundDetail = fundDetailService.findFundDetail(fundDetailId);
		JSONArray result = fundDetailStandardProfitService.getStandardProfit(fundDetail);
		return new Result(result.toString(), "success", 200);
	}
	
	@GetMapping("/{fundDetailId}/topItem")
	public Result getTopItems(@PathVariable Long fundDetailId) {
		
		FundDetail fundDetail = fundDetailService.findFundDetail(fundDetailId);
		List<ResponseDto> result = fundDetailTopItemService.getTopItems(fundDetail);
		return new Result(result.toString(), "success", 200);
	}
	
	@GetMapping("/{fundDetailId}/ter")
	public Result setTer(@PathVariable Long fundDetailId) {
		FundDetail fundDetail = fundDetailService.findFundDetail(fundDetailId);
		fundDetailService.setTotalExpenseRatioRank(fundDetail);
		
		return new Result("", "success", 200);
	}
	
	@GetMapping("/{fundDetailId}/tradingFee")
	public Result setTradingFee(@PathVariable Long fundDetailId) {
		FundDetail fundDetail = fundDetailService.findFundDetail(fundDetailId);
		fundDetailService.setTradingFeeRatioRank(fundDetail);
		
		return new Result("", "success", 200);
	}

}
