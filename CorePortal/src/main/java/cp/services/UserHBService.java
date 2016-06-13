package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.HBUser;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;
import cp.utils.TrippleDes;
import cp.utils.UserUtils;
import cp.utils.enums.AuthenticationType;

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
	
	public Map<String, Object> addUser(String username, String firstname, String lastname, String CNP, String email, String address, String telephone, String auth_type) throws Exception{
		AuthenticationType authentication_type = AuthenticationType.valueOf(auth_type);
		String pass = UserUtils.generatePassword();
		TrippleDes td = new TrippleDes();
		String password = td.encrypt(pass);
		HBUser user = new HBUser(username, password, firstname, lastname, CNP, email, address, telephone, authentication_type);
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			DataBase.persistUser(user);;
			response.put("user", user);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't add new user in database.");
		}
	}
	
	public Map<String, Object> deleteUser(Long id) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			HBUser user = DataBase.deleteHBUser(id);
			response.put("user", user);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't delete user from database.");
		}
	}
	
	public Map<String, Object> editUser(Long id_user, String firstname, String lastname, String CNP, String email, String address, String telephone, String auth_type) throws Exception{
		AuthenticationType authentication_type = AuthenticationType.valueOf(auth_type);
		Map<String, Object> response = new HashMap<>();
		
		try {
			HBUser user = DataBase.updateHBUser(id_user, firstname, lastname, CNP, email, address, telephone, authentication_type);
			response.put("user", user);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't update user from database.");
		}
	}
	
	public Map<String, Object> generateNewPassword(Long id_user) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		String pass = UserUtils.generatePassword();
		TrippleDes td = new TrippleDes();
		String password = td.encrypt(pass);
		
		try {
			HBUser user = DataBase.generateNewPassword(id_user, password);
			response.put("user", user);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't generate new password.");
		}
	}

}
