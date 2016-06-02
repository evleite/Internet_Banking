package cp.services;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;
import cp.models.CPUser;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;
import cp.utils.TrippleDes;

@WebService
public class LoginFlowService {
	
	public Map<String, Object> logIn(String username, String pass) throws Exception{
		Map<String, Object> response = new HashMap<>();
		TrippleDes td = new TrippleDes();
		
		CPUser logedInUser =	DataBase.getUserByUserName(username);
		
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
