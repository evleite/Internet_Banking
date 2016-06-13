package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.Rate;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;
import cp.utils.enums.RatesType;

@WebService
public class RateService {
	
	public Map<String, Object> getRateList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		List<Rate> rateList =	DataBase.getRateList();
		
		if(rateList != null){
			response.put("rateList", rateList);
			return ResponseUtils.respondWithSucces(response);
		} else {
			return ResponseUtils.respondWithError("Can't get rates from database.");
		}
		
	}
	
	public Map<String, Object> addRate(String type, Double year_percentage, String details) throws Exception{
		RatesType rateType = RatesType.valueOf(type);
		Rate rate = new Rate(rateType, year_percentage, details);
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			DataBase.persistRate(rate);
			response.put("rate", rate);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't add new rate in database.");
		}
	}
	
	public Map<String, Object> deleteRate(Long id) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			Rate rate = DataBase.deleteRate(id);
			response.put("rate", rate);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't delete rate from database.");
		}
	}
	
	public Map<String, Object> editRate(Long id_rate, Double year_percentage, String details) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			Rate rate = DataBase.updateRate(id_rate, year_percentage, details);
			response.put("rate", rate);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't update rate from database.");
		}
	}

}
