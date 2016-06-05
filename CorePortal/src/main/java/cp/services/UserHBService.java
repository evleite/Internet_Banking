package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.HBUser;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;

@WebService
public class UserHBService {
	
	public Map<String, Object> getHBUserList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		List<HBUser> userHBList =	DataBase.getHBUserList();
		
		if(userHBList != null){
			response.put("userHBList", userHBList);
			return ResponseUtils.respondWithSucces(response);
		} else {
			return ResponseUtils.respondWithError("Can't get CP users from database.");
		}
		
	}

}
