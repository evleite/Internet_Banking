package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.CPUser;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;

@WebService
public class UserCPService {
	
	public Map<String, Object> getCPUserList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		List<CPUser> usersCPList =	DataBase.getCPUserList();
		
		if(usersCPList != null){
			response.put("userCPList", usersCPList);
			return ResponseUtils.respondWithSucces(response);
		} else {
			return ResponseUtils.respondWithError("Can't get CP users from database.");
		}
		
	}

}
