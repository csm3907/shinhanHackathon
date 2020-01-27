package com.fund.service;

import org.json.JSONObject;

import com.fund.domain.FundDetail;

public interface FundDetailService {

	FundDetail findFundDetail(long id);
	
	JSONObject setAsJson(FundDetail fundDetail, JSONObject json);

	JSONObject getFundInfo(Long id);

	void setTotalExpenseRatioRank(FundDetail fundDetail);
	
	void setTradingFeeRatioRank(FundDetail fundDetail);
}
