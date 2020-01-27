package com.fund.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fund.domain.KliaFund;
import com.fund.domain.KliaFundSetupProfit;
import com.fund.repository.KliaFundRepository;
import com.fund.repository.KliaFundSetupProfitRepository;

@Service("KliaService")
public class KliaServiceImpl implements KliaService {

	
	@Autowired
	KliaFundRepository kliaFundRepository;
	
	@Autowired
	KliaFundSetupProfitRepository kliaFundSetupProfitRepository;
	
	@Override
	public KliaFund findKliaFund(String id) {
		return kliaFundRepository.findById(Long.parseLong(id));
	}
	
	@Override
	public JSONObject setAsJson(KliaFund kliaFund) {
		JSONObject json = new JSONObject();
		if(kliaFund.getName() != null) {
			json.put("name", kliaFund.getName());
		}
		if(kliaFund.getCompany() != null) {
			json.put("company", kliaFund.getCompany());
		}
		if(kliaFund.getFundType() != null) {
			json.put("fundType", kliaFund.getFundType().getName());
		}
		if(kliaFund.getKliaCode() != null) {
			json.put("kliaCode", kliaFund.getKliaCode());
		}
		if(kliaFund.getSetupDate() != null) {
			json.put("setUpDate", kliaFund.getSetupDate());
		}
		if(kliaFund.getPlan() != null) {
			json.put("plan", kliaFund.getPlan());
		}
		if(kliaFund.getElapse() != null) {
			json.put("elapse", kliaFund.getElapse());
		}
		
		return json;
	}
	
	@Override
	public JSONArray getSetupProfitListByKliaFund(KliaFund kliaFund) {
		JSONArray result = new JSONArray();
		
		List<KliaFundSetupProfit> fundSetupProfits = kliaFundSetupProfitRepository.findByKliaFund(kliaFund);
		if(fundSetupProfits == null || fundSetupProfits.size() == 0) {
			return null;
		}
		
		for(KliaFundSetupProfit setupProfit : fundSetupProfits) {
			JSONObject object = new JSONObject();
			object.put("date", setupProfit.getDate());
			object.put("profit", setupProfit.getProfitRatio());
			result.put(object);
		}
		
		return result;
	}
}
