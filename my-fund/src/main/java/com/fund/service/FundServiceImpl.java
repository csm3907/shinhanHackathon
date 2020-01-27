package com.fund.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fund.domain.Fund;
import com.fund.domain.FundDetail;
import com.fund.domain.FundDetailAssetPortfolio;
import com.fund.domain.FundDetailStandardProfit;
import com.fund.domain.FundDetailTopItem;
import com.fund.domain.FundSetupProfit;
import com.fund.domain.KliaFund;
import com.fund.domain.KliaFundSetupProfit;
import com.fund.domain.KofiaFund;
import com.fund.domain.enums.AssetType;
import com.fund.domain.enums.FundType;
import com.fund.dto.FundDto;
import com.fund.exception.CustomException;
import com.fund.exception.ExceptionCode;
import com.fund.repository.FundDetailAssetPortfolioRepository;
import com.fund.repository.FundDetailRepository;
import com.fund.repository.FundDetailStandardProfitRepository;
import com.fund.repository.FundDetailTopItemRepository;
import com.fund.repository.FundRepository;
import com.fund.repository.FundSetupProfitRepository;
import com.fund.repository.KliaFundRepository;
import com.fund.repository.KliaFundSetupProfitRepository;
import com.fund.repository.KofiaFundRepository;

@Service("FundService")
public class FundServiceImpl implements FundService{

	private final Logger logger = LoggerFactory.getLogger(FundServiceImpl.class);
	
	@Autowired
	FundSetupProfitService fundSetupProfitService;
	
	@Autowired
	FundRepository fundRepository;
	
	@Autowired
	FundDetailRepository fundDetailRepository;
	
	@Autowired
	FundDetailAssetPortfolioRepository fundDetailAssetPortfolioRepository;
	
	@Autowired
	FundDetailTopItemRepository fundDetailTopItemRepository;
	
	@Autowired
	FundDetailStandardProfitRepository fundDetailStandardProfitRepository;
	
	@Autowired
	FundSetupProfitRepository fundSetupProfitRepository;
	
	@Autowired
	KofiaFundRepository kofiaFundRepository;
	
	@Autowired
	KliaFundRepository kliaFundRepository;
	
	@Autowired
	KliaFundSetupProfitRepository kliaFundSetupProfitRepository;
	
	@Autowired
	ExcelService excelService;
	
	@Override
	public FundDto.ResponseDtoList getFundList() {
		List<Fund> fundList = new ArrayList<Fund>();
		for (int i = 0; i < 3; i++) {
			Fund fund = new Fund((long) i, "test", "VG101");
			fundList.add(fund);
			logger.info(fund.toString());
		}
		
		return new FundDto.ResponseDtoList(fundList);
	}
	
	@Override
	public void saveKofiaInfoFromExcel() {
		String [] fileNames = {"kofia_cost_standardDate.xlsx"};
		
		
		for(String fileName : fileNames) {
			logger.info("fileNmae ==> " + fileName);
			List<Map<String, String>> kofiaFundDataList = excelService.readXlsx(fileName);

			for (Map<String, String> kofiaFundData : kofiaFundDataList) {
				KofiaFund KofiaFund = new KofiaFund();
				
				// 1. set Fund Object
				if(kofiaFundData.get("fundType") != null && !kofiaFundData.get("fundType").isEmpty()) {
					KofiaFund.setFundType(setFundType(kofiaFundData.get("fundType")));
				}
				if(kofiaFundData.get("setupDate") != null && !kofiaFundData.get("setupDate").isEmpty()) {
					KofiaFund.setSetupDate(kofiaFundData.get("setupDate"));
				}
				if(kofiaFundData.get("managementCompany") != null && !kofiaFundData.get("managementCompany").isEmpty()) {
					KofiaFund.setManagementCompany(setManagementCompany(kofiaFundData.get("managementCompany")));
				}
				if(kofiaFundData.get("name") != null && !kofiaFundData.get("name").isEmpty()) {
					KofiaFund.setName(kofiaFundData.get("name").replaceAll("(^\\p{Z}+|\\p{Z}+$)", ""));
				}
				if(kofiaFundData.get("kofiaCode") != null && !kofiaFundData.get("kofiaCode").isEmpty()) {
					KofiaFund.setKofiaCode(kofiaFundData.get("kofiaCode"));
				}
				if(kofiaFundData.get("TER") != null && !kofiaFundData.get("TER").isEmpty()) {
					KofiaFund.setTotalExpenseRatio(kofiaFundData.get("TER"));
				}
				if(kofiaFundData.get("tradingFeeRatio") != null && !kofiaFundData.get("tradingFeeRatio").isEmpty()) {
					KofiaFund.setTradingFeeRatio(kofiaFundData.get("tradingFeeRatio"));
				}
				if(kofiaFundData.get("standardDate") != null && !kofiaFundData.get("standardDate").isEmpty()) {
					KofiaFund.setStandardDate(kofiaFundData.get("standardDate"));
				}
	
				kofiaFundRepository.save(KofiaFund);
				kofiaFundRepository.flush();
			}
		}
	}
	
	@Override
	public void saveScProfitSetupDate() {
		String [] fileNames = {"sc_profit_setupDate.xlsx"};
		
		
		for(String fileName : fileNames) {
			logger.info("fileNmae ==> " + fileName);
			List<Map<String, String>> fundDataList = excelService.readXlsx(fileName);

			for (Map<String, String> fundData : fundDataList) {
				 Fund fund = fundRepository.findByKliaCode(fundData.get("kliaCode"));
				
				List<FundSetupProfit> fundSetupProfits = new ArrayList<FundSetupProfit>();
				
				// 1. set Fund Object
				if(fundData.get("company") != null && !fundData.get("company").isEmpty()) {
					fund.setCompany(fundData.get("company"));
				}
				if(fundData.get("fundType") != null && !fundData.get("fundType").isEmpty()) {
					fund.setFundType(setFundType(fundData.get("fundType")));
				}
				if(fundData.get("setupDate") != null && !fundData.get("setupDate").isEmpty()) {
					fund.setSetUpDate(fundData.get("setupDate"));
				}
				if(fundData.get("managementCompany") != null && !fundData.get("managementCompany").isEmpty()) {
					fund.setManagementCompany(setManagementCompany(fundData.get("managementCompany")));
				}
				/*
				 * if(fundData.get("name") != null && !fundData.get("name").isEmpty()) {
				 * fund.setName(fundData.get("name").replaceAll("(^\\p{Z}+|\\p{Z}+$)", "")); }
				 */
				/*
				 * if(fundData.get("kofiaCode") != null && !fundData.get("kofiaCode").isEmpty())
				 * { fund.setKofiaCode(fundData.get("kofiaCode")); } if(fundData.get("kliaCode")
				 * != null && !fundData.get("kliaCode").isEmpty()) {
				 * fund.setKliaCode(fundData.get("kliaCode")); } if(fundData.get("scCode") !=
				 * null && !fundData.get("scCode").isEmpty()) {
				 * fund.setScCode(fundData.get("scCode")); }
				 */
				
				fundRepository.save(fund);
				
				// 2. set FundDetail Object
				if(fundData.get("profitRatio_1") != null && !fundData.get("profitRatio_1").isEmpty() 
						&& !fundData.get("profitRatio_1").equals("-")) {
					FundSetupProfit fundSetupProfit = new FundSetupProfit();
					fundSetupProfit.setDate(fundData.get("date_1"));
					fundSetupProfit.setProfitRatio(fundData.get("profitRatio_1"));
					fundSetupProfit.setFund(fund);
					fundSetupProfits.add(fundSetupProfit);
				}
				if(fundData.get("profitRatio_2") != null && !fundData.get("profitRatio_2").isEmpty() 
						&& !fundData.get("profitRatio_2").equals("-")) {
					FundSetupProfit fundSetupProfit = new FundSetupProfit();
					fundSetupProfit.setDate(fundData.get("date_2"));
					fundSetupProfit.setProfitRatio(fundData.get("profitRatio_2"));
					fundSetupProfit.setFund(fund);
					fundSetupProfits.add(fundSetupProfit);
				}
				if(fundData.get("profitRatio_3") != null && !fundData.get("profitRatio_3").isEmpty() 
						&& !fundData.get("profitRatio_3").equals("-")) {
					FundSetupProfit fundSetupProfit = new FundSetupProfit();
					fundSetupProfit.setDate(fundData.get("date_3"));
					fundSetupProfit.setProfitRatio(fundData.get("profitRatio_3"));
					fundSetupProfit.setFund(fund);
					fundSetupProfits.add(fundSetupProfit);
				}
				if(fundData.get("profitRatio_4") != null && !fundData.get("profitRatio_4").isEmpty() 
						&& !fundData.get("profitRatio_4").equals("-")) {
					FundSetupProfit fundSetupProfit = new FundSetupProfit();
					fundSetupProfit.setDate(fundData.get("date_4"));
					fundSetupProfit.setProfitRatio(fundData.get("profitRatio_4"));
					fundSetupProfit.setFund(fund);
					fundSetupProfits.add(fundSetupProfit);
				}
				if(fundData.get("profitRatio_5") != null && !fundData.get("profitRatio_5").isEmpty() 
						&& !fundData.get("profitRatio_5").equals("-")) {
					FundSetupProfit fundSetupProfit = new FundSetupProfit();
					fundSetupProfit.setDate(fundData.get("date_5"));
					fundSetupProfit.setProfitRatio(fundData.get("profitRatio_5"));
					fundSetupProfit.setFund(fund);
					fundSetupProfits.add(fundSetupProfit);
				}
				if(fundData.get("profitRatio_6") != null && !fundData.get("profitRatio_6").isEmpty() 
						&& !fundData.get("profitRatio_6").equals("-")) {
					FundSetupProfit fundSetupProfit = new FundSetupProfit();
					fundSetupProfit.setDate(fundData.get("date_6"));
					fundSetupProfit.setProfitRatio(fundData.get("profitRatio_6"));
					fundSetupProfit.setFund(fund);
					fundSetupProfits.add(fundSetupProfit);
				}
				if(fundData.get("profitRatio_7") != null && !fundData.get("profitRatio_7").isEmpty() 
						&& !fundData.get("profitRatio_7").equals("-")) {
					FundSetupProfit fundSetupProfit = new FundSetupProfit();
					fundSetupProfit.setDate(fundData.get("date_7"));
					fundSetupProfit.setProfitRatio(fundData.get("profitRatio_7"));
					fundSetupProfit.setFund(fund);
					fundSetupProfits.add(fundSetupProfit);
				}
				
				fund.setFundSetupProfits(fundSetupProfits);
				fundSetupProfitRepository.saveAll(fundSetupProfits);
			}
			
			fundRepository.flush();
			fundSetupProfitRepository.flush();
		}
	}
	
	@Override
	public void saveScFromExcel() {
		String [] fileNames = {"sc_profit_standardDate.xlsx"};
		
		
		for(String fileName : fileNames) {
			logger.info("fileNmae ==> " + fileName);
			List<Map<String, String>> fundDataList = excelService.readXlsx(fileName);

			for (Map<String, String> fundData : fundDataList) {
				Fund fund = new Fund();
				FundDetail fundDetail = new FundDetail();
				FundDetailStandardProfit standardProfit = new FundDetailStandardProfit();
				
				// 1. set Fund Object
				if(fundData.get("company") != null && !fundData.get("company").isEmpty()) {
					fund.setCompany(fundData.get("company"));
				}
				if(fundData.get("fundType") != null && !fundData.get("fundType").isEmpty()) {
					fund.setFundType(setFundType(fundData.get("fundType")));
				}
				if(fundData.get("setupDate") != null && !fundData.get("setupDate").isEmpty()) {
					fund.setSetUpDate(fundData.get("setupDate"));
				}
				if(fundData.get("managementCompany") != null && !fundData.get("managementCompany").isEmpty()) {
					fund.setManagementCompany(setManagementCompany(fundData.get("managementCompany")));
				}
				if(fundData.get("name") != null && !fundData.get("name").isEmpty()) {
					fund.setName(fundData.get("name").replaceAll("(^\\p{Z}+|\\p{Z}+$)", ""));
				}
				if(fundData.get("kofiaCode") != null && !fundData.get("kofiaCode").isEmpty()) {
					fund.setKofiaCode(fundData.get("kofiaCode"));
				}
				if(fundData.get("kliaCode") != null && !fundData.get("kliaCode").isEmpty()) {
					fund.setKliaCode(fundData.get("kliaCode"));
				}
				if(fundData.get("scCode") != null && !fundData.get("scCode").isEmpty()) {
					fund.setScCode(fundData.get("scCode"));
				}
				
				fundRepository.save(fund);
				
				// 2. set FundDetail Object
				if(fundData.get("standardDate") != null && !fundData.get("standardDate").isEmpty()) {
					fundDetail.setStandardDate((fundData.get("standardDate")));
				}
				if(fundData.get("standardPrice") != null && !fundData.get("standardPrice").isEmpty()) {
					fundDetail.setStandardPrice((fundData.get("standardPrice")));
				}
				if(fundData.get("TotalExpenseRatio") != null && !fundData.get("TotalExpenseRatio").isEmpty()) {
					fundDetail.setTotalExpenseRatio((fundData.get("TotalExpenseRatio")));
				}
				if(fundData.get("tradingFeeRatio") != null && !fundData.get("tradingFeeRatio").isEmpty()) {
					fundDetail.setTradingFeeRatio((fundData.get("tradingFeeRatio")));
				}
				if(fundData.get("turnoverRatio") != null && !fundData.get("turnoverRatio").isEmpty()) {
					fundDetail.setTurnoverRatio((fundData.get("turnoverRatio")));
				}
				if(fundData.get("netAssets") != null && !fundData.get("netAssets").isEmpty()) {
					fundDetail.setNetAssets((fundData.get("netAssets")));
				}
				fundDetail.setFund(fund);
				fundDetailRepository.save(fundDetail);
				
				// 3. set FundDetailStandardProfit Object
				standardProfit.setFundDetail(fundDetail);
				if(fundData.get("3M") != null && !fundData.get("3M").isEmpty() 
						&& !fundData.get("3M").equals("-")) {
					standardProfit.setThreeMonths(fundData.get("3M"));
				}
				if(fundData.get("6M") != null && !fundData.get("6M").isEmpty() 
						&& !fundData.get("6M").equals("-")) {
					standardProfit.setSixMonths(fundData.get("6M"));
				}
				if(fundData.get("9M") != null && !fundData.get("9M").isEmpty() 
						&& !fundData.get("9M").equals("-")) {
					standardProfit.setNineMonths(fundData.get("9M"));
				}
				if(fundData.get("1Y") != null && !fundData.get("1Y").isEmpty() 
						&& !fundData.get("1Y").equals("-")) {
					standardProfit.setOneYear(fundData.get("1Y"));
				}
				if(fundData.get("2Y") != null && !fundData.get("2Y").isEmpty() 
						&& !fundData.get("2Y").equals("-")) {
					standardProfit.setTwoYears(fundData.get("2Y"));
				}
				if(fundData.get("3Y") != null && !fundData.get("3Y").isEmpty() 
						&& !fundData.get("3Y").equals("-")) {
					standardProfit.setThreeYears(fundData.get("3Y"));
				}
				if(fundData.get("5Y") != null && !fundData.get("5Y").isEmpty() 
						&& !fundData.get("5Y").equals("-")) {
					standardProfit.setFiveYears(fundData.get("5Y"));
				}
				fundDetailStandardProfitRepository.save(standardProfit);
				
			}
		}
		fundRepository.flush();
		fundDetailRepository.flush();
		fundDetailStandardProfitRepository.flush();
		
	}
	
	@Override
	public void saveScTopItemFromExcel() {
		String [] fileNames = {"sc_topItem_standardDate.xlsx"};
		
		for(String fileName : fileNames) {
			logger.info("fileNmae ==> " + fileName);
			List<Map<String, String>> fundDataList = excelService.readXlsx(fileName);
			
			for (Map<String, String> fundData : fundDataList) {
				Fund fund = null;
				FundDetail fundDetail = null;
				fund = fundRepository.findByScCode(fundData.get("scCode")).get();
				logger.info("fund ==> " + fund.getId());
				if (fund == null) {
					throw new CustomException(ExceptionCode.NOT_FOUND_DATA);
				}
				fundDetail = fundDetailRepository.findByFund(fund);
				logger.info("fundDetail ==> " + fundDetail.getId());
				if (fundDetail == null) {
					throw new CustomException(ExceptionCode.NOT_FOUND_DATA);
				}
				
				// 2. set FundDetailTopItem Object
				FundDetailTopItem topItem = new FundDetailTopItem();
				if(fundData.get("itemName") != null && !fundData.get("itemName").isEmpty()) {
					topItem.setName(fundData.get("itemName"));
				}
				if(fundData.get("rate") != null && !fundData.get("rate").isEmpty()) {
					topItem.setRate(fundData.get("rate"));
				}
				if(fundData.get("totalRate") != null && !fundData.get("totalRate").isEmpty()) {
					fundDetail.setTopItemsRate(fundData.get("totalRate"));
				}
				
				topItem.setFundDetail(fundDetail);
				fundDetailTopItemRepository.save(topItem);
			}
		}
		
		fundDetailTopItemRepository.flush();
	}
	
	@Override
	public void saveKliaInfoFromExcel() {
		String [] fileNames = {"klia_info_20191105.xlsx"};
		
		
		for(String fileName : fileNames) {
			logger.info("fileNmae ==> " + fileName);
			List<Map<String, String>> kliaFundDataList = excelService.readXlsx(fileName);

			for (Map<String, String> kliaFundData : kliaFundDataList) {
				KliaFund kliaFund = new KliaFund();
				
				List<KliaFundSetupProfit> fundSetupProfits = new ArrayList<KliaFundSetupProfit>();
				
				// 1. set Fund Object
				if(kliaFundData.get("company") != null && !kliaFundData.get("company").isEmpty()) {
					kliaFund.setCompany(kliaFundData.get("company"));
				}
				if(kliaFundData.get("fundType") != null && !kliaFundData.get("fundType").isEmpty()) {
					kliaFund.setFundType(setFundType(kliaFundData.get("fundType")));
				}
				if(kliaFundData.get("setupDate") != null && !kliaFundData.get("setupDate").isEmpty()) {
					kliaFund.setSetupDate(kliaFundData.get("setupDate"));
				}

				kliaFundRepository.save(kliaFund);
				kliaFundRepository.flush();
				
				// 2. set FundDetail Object
				if(kliaFundData.get("profitRatio_1") != null && !kliaFundData.get("profitRatio_1").isEmpty() 
						&& !kliaFundData.get("profitRatio_1").equals("-")) {
					KliaFundSetupProfit fundSetupProfit = new KliaFundSetupProfit();
					fundSetupProfit.setDate(kliaFundData.get("date_1"));
					fundSetupProfit.setProfitRatio(kliaFundData.get("profitRatio_1"));
					fundSetupProfit.setKliaFund(kliaFund);
					fundSetupProfits.add(fundSetupProfit);
				}
				if(kliaFundData.get("profitRatio_2") != null && !kliaFundData.get("profitRatio_2").isEmpty() 
						&& !kliaFundData.get("profitRatio_2").equals("-")) {
					KliaFundSetupProfit fundSetupProfit = new KliaFundSetupProfit();
					fundSetupProfit.setDate(kliaFundData.get("date_2"));
					fundSetupProfit.setProfitRatio(kliaFundData.get("profitRatio_2"));
					fundSetupProfit.setKliaFund(kliaFund);
					fundSetupProfits.add(fundSetupProfit);
				}
				if(kliaFundData.get("profitRatio_3") != null && !kliaFundData.get("profitRatio_3").isEmpty() 
						&& !kliaFundData.get("profitRatio_3").equals("-")) {
					KliaFundSetupProfit fundSetupProfit = new KliaFundSetupProfit();
					fundSetupProfit.setDate(kliaFundData.get("date_3"));
					fundSetupProfit.setProfitRatio(kliaFundData.get("profitRatio_3"));
					fundSetupProfit.setKliaFund(kliaFund);
					fundSetupProfits.add(fundSetupProfit);
				}
				if(kliaFundData.get("profitRatio_4") != null && !kliaFundData.get("profitRatio_4").isEmpty() 
						&& !kliaFundData.get("profitRatio_4").equals("-")) {
					KliaFundSetupProfit fundSetupProfit = new KliaFundSetupProfit();
					fundSetupProfit.setDate(kliaFundData.get("date_4"));
					fundSetupProfit.setProfitRatio(kliaFundData.get("profitRatio_4"));
					fundSetupProfit.setKliaFund(kliaFund);
					fundSetupProfits.add(fundSetupProfit);
				}
				if(kliaFundData.get("profitRatio_5") != null && !kliaFundData.get("profitRatio_5").isEmpty() 
						&& !kliaFundData.get("profitRatio_5").equals("-")) {
					KliaFundSetupProfit fundSetupProfit = new KliaFundSetupProfit();
					fundSetupProfit.setDate(kliaFundData.get("date_5"));
					fundSetupProfit.setProfitRatio(kliaFundData.get("profitRatio_5"));
					fundSetupProfit.setKliaFund(kliaFund);
					fundSetupProfits.add(fundSetupProfit);
				}
				if(kliaFundData.get("profitRatio_6") != null && !kliaFundData.get("profitRatio_6").isEmpty() 
						&& !kliaFundData.get("profitRatio_6").equals("-")) {
					KliaFundSetupProfit fundSetupProfit = new KliaFundSetupProfit();
					fundSetupProfit.setDate(kliaFundData.get("date_6"));
					fundSetupProfit.setProfitRatio(kliaFundData.get("profitRatio_6"));
					fundSetupProfit.setKliaFund(kliaFund);
					fundSetupProfits.add(fundSetupProfit);
				}
				if(kliaFundData.get("profitRatio_7") != null && !kliaFundData.get("profitRatio_7").isEmpty() 
						&& !kliaFundData.get("profitRatio_7").equals("-")) {
					KliaFundSetupProfit fundSetupProfit = new KliaFundSetupProfit();
					fundSetupProfit.setDate(kliaFundData.get("date_7"));
					fundSetupProfit.setProfitRatio(kliaFundData.get("profitRatio_7"));
					fundSetupProfit.setKliaFund(kliaFund);
					fundSetupProfits.add(fundSetupProfit);
				}
				
				kliaFund.setKliaFundSetupProfits(fundSetupProfits);
				kliaFundSetupProfitRepository.saveAll(fundSetupProfits);
				
				kliaFundRepository.flush();
				kliaFundSetupProfitRepository.flush();
			}	
		}
	}
	
	@Override
	public void saveScAssetPortfolioFromExcel() {
		String [] fileNames = {"sc_assetPortfolio_standardDate.xlsx"};
		
		for(String fileName : fileNames) {
			logger.info("fileNmae ==> " + fileName);
			List<Map<String, String>> fundDataList = excelService.readXlsx(fileName);
			
			for (Map<String, String> fundData : fundDataList) {
				Fund fund = null;
				FundDetail fundDetail = null;
				fund = fundRepository.findByKliaCode(fundData.get("kliaCode"));
				logger.info("fund ==> " + fund.getId());
				if (fund == null || fund.getId() == null) {
					throw new CustomException(ExceptionCode.NOT_FOUND_DATA);
				}
				fundDetail = fundDetailRepository.findByFund(fund);
				logger.info("fundDetail ==> " + fundDetail.getId());
				if (fundDetail == null || fundDetail.getId() == null) {
					throw new CustomException(ExceptionCode.NOT_FOUND_DATA);
				}
				
				// 2. set FundDetailTopItem Object
				List<FundDetailAssetPortfolio> assetPortfolioList = new ArrayList<FundDetailAssetPortfolio>();
				if(fundData.get("stock") != null && !fundData.get("stock").isEmpty()
						&& !fundData.get("stock").equals("0")) {
					FundDetailAssetPortfolio assetPortfolio = new FundDetailAssetPortfolio();
					assetPortfolio.setAssetType(AssetType.STOCK);
					assetPortfolio.setRatio(fundData.get("stock"));
					assetPortfolio.setFundDetail(fundDetail);
					assetPortfolioList.add(assetPortfolio);
				}
				if(fundData.get("bond") != null && !fundData.get("bond").isEmpty()
						&& !fundData.get("bond").equals("0")) {
					FundDetailAssetPortfolio assetPortfolio = new FundDetailAssetPortfolio();
					assetPortfolio.setAssetType(AssetType.BOND);
					assetPortfolio.setRatio(fundData.get("bond"));
					assetPortfolio.setFundDetail(fundDetail);
					assetPortfolioList.add(assetPortfolio);
				}
				if(fundData.get("beneficiaryCertificate") != null && !fundData.get("beneficiaryCertificate").isEmpty()
						&& !fundData.get("beneficiaryCertificate").equals("0")) {
					FundDetailAssetPortfolio assetPortfolio = new FundDetailAssetPortfolio();
					assetPortfolio.setAssetType(AssetType.BENEFICIARY_CERTIFICATE);
					assetPortfolio.setRatio(fundData.get("beneficiaryCertificate"));
					assetPortfolio.setFundDetail(fundDetail);
					assetPortfolioList.add(assetPortfolio);
				}
				if(fundData.get("liquidity") != null && !fundData.get("liquidity").isEmpty()
						&& !fundData.get("liquidity").equals("0")) {
					FundDetailAssetPortfolio assetPortfolio = new FundDetailAssetPortfolio();
					assetPortfolio.setAssetType(AssetType.LIQUIDITY);
					assetPortfolio.setRatio(fundData.get("liquidity"));
					assetPortfolio.setFundDetail(fundDetail);
					assetPortfolioList.add(assetPortfolio);
				}
				if(fundData.get("etc") != null && !fundData.get("etc").isEmpty()
						&& !fundData.get("etc").equals("0")) {
					FundDetailAssetPortfolio assetPortfolio = new FundDetailAssetPortfolio();
					assetPortfolio.setAssetType(AssetType.ETC);
					assetPortfolio.setRatio(fundData.get("etc"));
					assetPortfolio.setFundDetail(fundDetail);
					assetPortfolioList.add(assetPortfolio);
				}
				fundDetailAssetPortfolioRepository.saveAll(assetPortfolioList);
			}
		}
		
		fundDetailAssetPortfolioRepository.flush();
		
	}

	private String setManagementCompany(String managementCompany) {
		if(managementCompany.contains("/")) {
			return managementCompany.split("/")[0];
		}
		return managementCompany;
	}
	
	private FundType setFundType(String type) {
		if(type.equals("주식형")) return FundType.STOCK;
		else if(type.equals("주식혼합형")) return FundType.MIXED_STOCK;
		else if(type.equals("혼합주식형")) return FundType.MIXED_STOCK;
		else if(type.equals("채권형")) return FundType.BOND;
		else if(type.equals("채권혼합형")) return FundType.MIXED_BOND;
		else if(type.equals("혼합채권형")) return FundType.MIXED_BOND;
		else if(type.equals("커머더티형")) return FundType.REINDIRECTNESS;
		else if(type.contains("부동산")) return FundType.REAL_ESTATE;
		else if(type.contains("기타")) return FundType.ETC;
		else {
			logger.info("fundType ===> " + type);
			throw new CustomException(ExceptionCode.NOT_FOUND_DATA);
		}
	}
	
	@Override
	public JSONObject setAsJson(Fund fund, JSONObject json) {
		if(fund.getName() != null) {
			json.put("name", fund.getName());
		}
		if(fund.getCompany() != null) {
			json.put("company", fund.getCompany());
		}
		if(fund.getFundType() != null) {
			json.put("fundType", fund.getFundType().getName());
		}
		if(fund.getKliaCode() != null) {
			json.put("kliaCode", fund.getKliaCode());
		}
		if(fund.getKofiaCode() != null) {
			json.put("kofiaCode", fund.getKofiaCode());
		}
		if(fund.getScCode() != null) {
			json.put("scCode", fund.getScCode());
		}
		if(fund.getManagementCompany() != null) {
			json.put("managementCompany", fund.getManagementCompany());
		}
		if(fund.getSetUpDate() != null) {
			json.put("setUpDate", fund.getSetUpDate());
		}
		if(fund.getPlan() != null) {
			json.put("plan", fund.getPlan());
		}
		if(fund.getElapse() != null) {
			json.put("elapse", fund.getElapse());
		}
		return json;
	}
	
	@Override
	public JSONArray getFundNameList(List<FundDetail> fundDetails) {
		JSONArray result = new JSONArray();
		//List<String> fundNameList = new ArrayList<String>();
		for(FundDetail fundDetail : fundDetails) {
			JSONObject json = new JSONObject();
			logger.info("name ===>" + fundDetail.getFund().getName());
			json.put("name", fundDetail.getFund().getName()); 
			result.put(json);
		} 
		logger.info(result.toString());
		return result;
	}
	
	@Override
	public Fund findById(long id) {
		Fund fund = fundRepository.findById(id).get();
		logger.info("fund id ==> " + fund.getId());
		return fund;
	}
	
	
	@Override
	public Fund findByScCode(String scCode) {
		Fund fund = fundRepository.findByScCode(scCode).get();
		logger.info("fund id ==> " + fund.getId());
		return fund;
	}
	
	
	@Override
	public JSONArray getSetupProfitListByScCode(String scCode) {
		Fund fund = this.findByScCode(scCode);
		return fundSetupProfitService.getFundSetupProfitListByFund(fund);
	}
	
}
