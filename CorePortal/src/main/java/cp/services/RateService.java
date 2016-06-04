package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.Account;
import cp.models.Rate;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;

@WebService
public class RateService {
	
	public Map<String, Object> getRateList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		List<Rate> rateList =	DataBase.getRateList();
		
		if(rateList != null){
			response.put("rateList", rateList);
			return ResponseUtils.respondWithSucces(response);
		} else {
			return ResponseUtils.respondWithError("Can't get accounts from database.");
		}
		
	}

}
