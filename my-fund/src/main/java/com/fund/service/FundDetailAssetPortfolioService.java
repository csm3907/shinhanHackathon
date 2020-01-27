package com.fund.service;

import org.json.JSONArray;

import com.fund.domain.FundDetail;

public interface FundDetailAssetPortfolioService {
	
	JSONArray getAssetPortfolioList(FundDetail fundDetail);

}
