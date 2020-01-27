package com.fund.service;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fund.domain.Fund;
import com.fund.domain.FundDetail;
import com.fund.domain.KofiaFund;
import com.fund.exception.CustomException;
import com.fund.exception.ExceptionCode;
import com.fund.repository.FundDetailRepository;
import com.fund.repository.KofiaFundRepository;

@Service("FundDetailService")
public class FundDetailServiceImpl implements FundDetailService{

	private final static Logger logger = LoggerFactory.getLogger(FundDetailServiceImpl.class);
	
	@Autowired
	FundService fundService;
	
	@Autowired
	FundDetailRepository fundDetailRepository;
	
	@Autowired
	KofiaFundRepository kofiaFundRepository;
	
	
	@Override
	public FundDetail findFundDetail(long id) {
		FundDetail fundDetail =  fundDetailRepository.findById(id).get();
		logger.info("fundDetail Id ==> " + fundDetail.getId());
		return fundDetail;
	}
	
	@Override
	public JSONObject getFundInfo(Long id) {
		JSONObject result = new JSONObject();
		
		FundDetail fundDetail = this.findFundDetail(id);
		Fund fund = fundDetail.getFund();
		if(fundDetail.getId() == null || fund.getId() == null) {
			throw new CustomException(ExceptionCode.NOT_FOUND_DATA);
		}
		
		result = fundService.setAsJson(fund, result);
		result = this.setAsJson(fundDetail, result);
		
		return result;
	}
	
	@Override
	public JSONObject setAsJson(FundDetail fundDetail, JSONObject json) {
		if(fundDetail.getNetAssets() != null) {
			json.put("netAssets", fundDetail.getNetAssets());
		}
		if(fundDetail.getStandardDate() != null) {
			json.put("standardDate", fundDetail.getStandardDate());
		}
		if(fundDetail.getStandardPrice() != null) {
			json.put("standardPrice", fundDetail.getStandardPrice());
		}
		if(fundDetail.getTopItemsRate() != null) {
			json.put("topItemsRatio", fundDetail.getTopItemsRate());
		}
		if(fundDetail.getTotalExpenseRatio() != null) {
			json.put("totalExpenseRatio", fundDetail.getTotalExpenseRatio());
		}
		if(fundDetail.getTotalExpenseRatioRank() != null) {
			json.put("totalExpenseRatioRank", fundDetail.getTotalExpenseRatioRank());
		}
		if(fundDetail.getTradingFeeRatio() != null) {
			json.put("tradingFeeRatio", fundDetail.getTradingFeeRatio());
		}
		if(fundDetail.getTradingFeeRatioRank() != null) {
			json.put("tradingFeeRatioRank", fundDetail.getTradingFeeRatioRank());
		}
		if(fundDetail.getTurnoverRatio() != null) {
			json.put("turnoverRatio", fundDetail.getTurnoverRatio());
		}
		if(fundDetail.getTurnoverRatio() != null) {
			json.put("totalFundCount", fundDetail.getTotalFundCount());
		}
		if(fundDetail.getManager() != null) {
			json.put("manager", fundDetail.getManager());
		}
		if(fundDetail.getManagerDate() != null) {
			json.put("managerDate", fundDetail.getManagerDate());
		}
		
		return json;
	}
	
	@Override
	public void setTotalExpenseRatioRank(FundDetail fundDetail) {
		String fundType = fundDetail.getFund().getFundType().getName();
		logger.info("fundType == >" + fundType);
		List<KofiaFund> kofiaFunds = kofiaFundRepository.findByFundTypeOrderByTotalExpenseRatio(fundType);
		logger.info("kofiaFunds.size ==> " + kofiaFunds.size());
		float ter = Float.parseFloat(fundDetail.getTotalExpenseRatio());
		for (int i = 0; i < kofiaFunds.size(); i++) {
			String rank = String.valueOf(i);
			KofiaFund kofiaFund = kofiaFunds.get(i);
			float compareTer = Float.parseFloat(kofiaFund.getTotalExpenseRatio());
			logger.info("rank ==> " +rank + ", compareTer ==> " + String.valueOf(compareTer) + ", ter ==> " + ter);
			
			if(compareTer > ter) {
				fundDetail.setTotalExpenseRatioRank(rank);
				break;
			}
		}
		if(fundDetail.getTotalExpenseRatioRank() == null || fundDetail.getTotalExpenseRatioRank().isEmpty()) {
			fundDetail.setTotalExpenseRatioRank(String.valueOf(kofiaFunds.size()));
		}
		fundDetail.setTotalFundCount(String.valueOf(kofiaFunds.size()));
		fundDetailRepository.flush();
	}
	
	@Override
	public void setTradingFeeRatioRank(FundDetail fundDetail) {
		String fundType = fundDetail.getFund().getFundType().getName();
		logger.info("fundType == >" + fundType);
		
		List<KofiaFund> kofiaFunds =  kofiaFundRepository.findByFundTypeOrderByTradingFeeRatio(fundType);
		logger.info("kofiaFunds.size ==> " + kofiaFunds.size());
		
		float tradingFeeRatio = Float.parseFloat(fundDetail.getTradingFeeRatio());
		for (int i = 0; i < kofiaFunds.size(); i++) {
			String rank = String.valueOf(i);
			KofiaFund kofiaFund = kofiaFunds.get(i);
			float compareTradingFeeRatio = Float.parseFloat(kofiaFund.getTradingFeeRatio());
			logger.info("rank ==> " +rank + ", compareTer ==> " + String.valueOf(compareTradingFeeRatio) + ", ter ==> " + tradingFeeRatio);
			
			if(compareTradingFeeRatio > tradingFeeRatio) {
				fundDetail.setTradingFeeRatioRank(rank);
				break;
			}
		}
		if(fundDetail.getTradingFeeRatioRank() == null || fundDetail.getTradingFeeRatioRank().isEmpty()) {
			fundDetail.setTradingFeeRatioRank(String.valueOf(kofiaFunds.size()));
		}
		fundDetail.setTotalFundCount(String.valueOf(kofiaFunds.size()));
		fundDetailRepository.flush();
		
	}
}
