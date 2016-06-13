package hb.services;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;
import hb.models.HBUser;
import hb.utils.DataBase;
import hb.utils.ResponseUtils;
import hb.utils.TrippleDes;

@WebService
public class LoginFlowService {
	
	public Map<String, Object> logIn(String username, String pass) throws Exception{
		Map<String, Object> response = new HashMap<>();
		TrippleDes td = new TrippleDes();
		
		HBUser logedInUser =	DataBase.getHBUserByUserName(username);
		
		if (logedInUser != null){
			if (td.decrypt(logedInUser.getPassword()).equals(pass)){
				response.put("user", logedInUser);
				return ResponseUtils.respondWithSucces(response);
			} else {
				return ResponseUtils.respondWithError("Invalid password");
			}
		} else {
			return ResponseUtils.respondWithError("Invalid user");
		}
	}

}
