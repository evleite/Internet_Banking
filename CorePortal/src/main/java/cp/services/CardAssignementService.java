package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.CardAssignement;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;

@WebService
public class CardAssignementService {
	
	public Map<String, Object> getCardAssignementList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		List<CardAssignement> cardAssignementList =	DataBase.getCardAssignementList();
		
		if(cardAssignementList != null){
			response.put("cardAssignementList", cardAssignementList);
			return ResponseUtils.respondWithSucces(response);
		} else {
			return ResponseUtils.respondWithError("Can't get cardAssignementList from database.");
		}
		
	}

}
