package com.fund.service;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fund.domain.KliaFund;

public interface KliaService {

	KliaFund findKliaFund(String id);
	
	JSONObject setAsJson(KliaFund kliaFund);

	JSONArray getSetupProfitListByKliaFund(KliaFund kliaFund);

}
