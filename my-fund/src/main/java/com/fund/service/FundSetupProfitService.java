package com.fund.service;

import org.json.JSONArray;

import com.fund.domain.Fund;

public interface FundSetupProfitService {

	JSONArray getFundSetupProfitListByFund(Fund fund);
}
