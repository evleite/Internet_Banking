package hb.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import hb.models.ExchangeRates;
import hb.utils.DataBase;
import hb.utils.ResponseUtils;

@WebService
public class ExchangeRateService {
	
	public Map<String, Object> getExchangeRateList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			List<ExchangeRates> exchangeRateList =	DataBase.getExchangeRateList();
			response.put("exchangeRateList", exchangeRateList);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't get accounts from database.");
		}
		
	}
}
