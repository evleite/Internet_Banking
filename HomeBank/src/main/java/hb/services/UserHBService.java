package hb.services;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import hb.models.HBUser;
import hb.utils.DataBase;
import hb.utils.ResponseUtils;
import hb.utils.TrippleDes;

@WebService
public class UserHBService {
	
	public Map<String, Object> editUser(Long id_user, String firstname, String lastname, String CNP, String email, String address, String telephone) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			HBUser user = DataBase.updateHBUser(id_user, firstname, lastname, CNP, email, address, telephone);
			response.put("user", user);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't update user from database.");
		}
	}
	
	public Map<String, Object> changePassword(Long id_user, String new_password) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		TrippleDes td = new TrippleDes();
		String password = td.encrypt(new_password);
		
		try {
			DataBase.changePassword(id_user, password);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't change password.");
		}
	}

}
