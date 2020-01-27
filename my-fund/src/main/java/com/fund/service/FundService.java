package com.fund.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fund.domain.Fund;
import com.fund.domain.FundDetail;
import com.fund.dto.FundDto;

public interface FundService {

	FundDto.ResponseDtoList getFundList();

	void saveKofiaInfoFromExcel();
	
	void saveScProfitSetupDate();

	void saveScFromExcel();

	void saveScTopItemFromExcel();
	
	void saveScAssetPortfolioFromExcel();

	JSONObject setAsJson(Fund fund, JSONObject json);

	Fund findById(long id);
	
	Fund findByScCode(String scCode);

	JSONArray getSetupProfitListByScCode(String scCode);

	void saveKliaInfoFromExcel();

	JSONArray getFundNameList(List<FundDetail> fundDetails);
}
