package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.AccountAssignement;
import cp.models.Rate;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;

@WebService
public class AccountAssignementService {
	
	public Map<String, Object> getAccountAssignementList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		List<AccountAssignement> accountAssignementList =	DataBase.getAccountAssignementList();
		
		if(accountAssignementList != null){
			response.put("accountAssignementList", accountAssignementList);
			return ResponseUtils.respondWithSucces(response);
		} else {
			return ResponseUtils.respondWithError("Can't get accountAssignementList from database.");
		}
		
	}

}
