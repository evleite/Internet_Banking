package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.Commision;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;

@WebService
public class CommisionService {
	
	public Map<String, Object> getCommisionList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		List<Commision> commisionList =	DataBase.getCommisionList();
		
		if(commisionList != null){
			response.put("commisionList", commisionList);
			return ResponseUtils.respondWithSucces(response);
		} else {
			return ResponseUtils.respondWithError("Can't get commisions from database.");
		}
		
	}

}
