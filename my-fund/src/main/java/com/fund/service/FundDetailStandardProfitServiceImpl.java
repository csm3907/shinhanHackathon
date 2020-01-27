package com.fund.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fund.domain.FundDetail;
import com.fund.domain.FundDetailStandardProfit;
import com.fund.exception.CustomException;
import com.fund.exception.ExceptionCode;
import com.fund.repository.FundDetailStandardProfitRepository;

@Service("FundDetailStandardProfitService")
public class FundDetailStandardProfitServiceImpl implements FundDetailStandardProfitService{
	
	@Autowired
	FundDetailStandardProfitRepository fundDetailStandardProfitRepository;
	
	@Override
	public JSONArray getStandardProfit(FundDetail fundDetail) {
		JSONArray result = new JSONArray();
		if(fundDetail.getId() == null) {
			throw new CustomException(ExceptionCode.NOT_FOUND_DATA);
		}
		
		FundDetailStandardProfit standardProfit = fundDetailStandardProfitRepository.findByFundDetail(fundDetail);
		if(standardProfit.getThreeMonths() != null) {
			JSONObject object = new JSONObject();
			object.put("date", "3M");
			object.put("profit", standardProfit.getThreeMonths());
			result.put(object);
		}
		if(standardProfit.getSixMonths() != null) {
			JSONObject object = new JSONObject();
			object.put("date", "6M");
			object.put("profit", standardProfit.getSixMonths());
			result.put(object);
		}
		if(standardProfit.getNineMonths() != null) {
			JSONObject object = new JSONObject();
			object.put("date", "9M");
			object.put("profit", standardProfit.getNineMonths());
			result.put(object);
		}
		if(standardProfit.getOneYear() != null) {
			JSONObject object = new JSONObject();
			object.put("date", "1Y");
			object.put("profit", standardProfit.getOneYear());
			result.put(object);
		}
		if(standardProfit.getTwoYears() != null) {
			JSONObject object = new JSONObject();
			object.put("date", "2Y");
			object.put("profit", standardProfit.getTwoYears());
			result.put(object);
		}
		if(standardProfit.getThreeYears() != null) {
			JSONObject object = new JSONObject();
			object.put("date", "3Y");
			object.put("profit", standardProfit.getThreeYears());
			result.put(object);
		}
		if(standardProfit.getFiveYears() != null) {
			JSONObject object = new JSONObject();
			object.put("date", "5Y");
			object.put("profit", standardProfit.getFiveYears());
			result.put(object);
		}
		 
		return result; 
	}

}
