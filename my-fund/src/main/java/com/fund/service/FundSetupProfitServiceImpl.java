package com.fund.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fund.domain.Fund;
import com.fund.domain.FundDetailTopItem;
import com.fund.domain.FundSetupProfit;
import com.fund.exception.CustomException;
import com.fund.exception.ExceptionCode;
import com.fund.repository.FundSetupProfitRepository;

@Service("FundSetupProfitService")
public class FundSetupProfitServiceImpl implements FundSetupProfitService {

	@Autowired
	FundSetupProfitRepository fundSetupProfitRepository; 
	
	
	@Override
	public JSONArray getFundSetupProfitListByFund(Fund fund) {
		JSONArray result = new JSONArray();
		if(fund.getId() == null) {
			throw new CustomException(ExceptionCode.NOT_FOUND_DATA);
		}
		
		List<FundSetupProfit> fundSetupProfits = fundSetupProfitRepository.findByFund(fund);
		if(fundSetupProfits == null || fundSetupProfits.size() == 0) {
			return null;
		}
		
		for(FundSetupProfit setupProfit : fundSetupProfits) {
			JSONObject object = new JSONObject();
			object.put("date", setupProfit.getDate());
			object.put("profit", setupProfit.getProfitRatio());
			result.put(object);
		}
		
		return result;
	}
}
