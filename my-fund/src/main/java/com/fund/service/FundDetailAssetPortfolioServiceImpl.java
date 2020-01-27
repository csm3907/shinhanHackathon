package com.fund.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fund.domain.FundDetail;
import com.fund.domain.FundDetailAssetPortfolio;
import com.fund.exception.CustomException;
import com.fund.exception.ExceptionCode;
import com.fund.repository.FundDetailAssetPortfolioRepository;
import com.fund.util.JsonUtil;

@Service("FundDetailAssetPortfolioService")
public class FundDetailAssetPortfolioServiceImpl implements FundDetailAssetPortfolioService {

	private final static Logger logger = LoggerFactory.getLogger(FundDetailAssetPortfolioServiceImpl.class);
	
	@Autowired
	FundDetailAssetPortfolioRepository fundDetailAssetPortfolioRepository;
	
	@Override
	public JSONArray getAssetPortfolioList(FundDetail fundDetail) {
		JSONArray result = new JSONArray();
		if(fundDetail.getId() == null) {
			throw new CustomException(ExceptionCode.NOT_FOUND_DATA);
		}
		
		List<FundDetailAssetPortfolio> assetPortfolios = fundDetailAssetPortfolioRepository.findByFundDetail(fundDetail);
		if(assetPortfolios == null || assetPortfolios.size() == 0) {
			return null;
		}
		
		for(FundDetailAssetPortfolio assetPortfolio : assetPortfolios) {
			JSONObject object = new JSONObject();
			object.put("assetType", assetPortfolio.getAssetType().getName());
			object.put("ratio", assetPortfolio.getRatio());
			result.put(object);
		}
		
		return result;
	}
}
