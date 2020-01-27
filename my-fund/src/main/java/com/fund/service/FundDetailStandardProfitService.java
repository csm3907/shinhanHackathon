package com.fund.service;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fund.domain.FundDetail;

public interface FundDetailStandardProfitService {

	JSONArray getStandardProfit(FundDetail fundDetail);
}
