package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.ExchangeRates;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;
import cp.utils.enums.Currencies;

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
	
	public Map<String, Object> addExchangeRate(String currency, Double buy, Double sell) throws Exception{
		Currencies currencyType = Currencies.valueOf(currency);
		ExchangeRates exchangeRate = new ExchangeRates(currencyType, buy, sell);
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			DataBase.persistExchangeRate(exchangeRate);
			response.put("exchangeRate", exchangeRate);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't add new exchangeRate in database.");
		}
	}
	
	public Map<String, Object> deleteExchangeRate(Long id) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			ExchangeRates exchangeRate = DataBase.deleteExchangeRate(id);
			response.put("exchangeRate", exchangeRate);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't delete exchangeRate from database.");
		}
	}
	
	public Map<String, Object> editExchangeRate(Long id_exchangeRate, Double buy, Double sell) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			ExchangeRates exchangeRate = DataBase.updateExchangeRate(id_exchangeRate, buy, sell);
			response.put("exchangeRate", exchangeRate);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't update exchangeRate from database.");
		}
	}

}
