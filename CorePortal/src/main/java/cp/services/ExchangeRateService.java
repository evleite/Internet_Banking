package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.ExchangeRates;
import cp.models.Rate;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;

@WebService
public class ExchangeRateService {
	
	public Map<String, Object> getExchangeRateList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		List<ExchangeRates> exchangeRateList =	DataBase.getExchangeRateList();
		
		if(exchangeRateList != null){
			response.put("exchangeRateList", exchangeRateList);
			return ResponseUtils.respondWithSucces(response);
		} else {
			return ResponseUtils.respondWithError("Can't get accounts from database.");
		}
		
	}

}
