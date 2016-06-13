package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.CPUser;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;
import cp.utils.TrippleDes;
import cp.utils.UserUtils;

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
	
	public Map<String, Object> addUser(String username, String firstname, String lastname, String CNP, String email, String address, String telephone) throws Exception{
		String pass = UserUtils.generatePassword();
		TrippleDes td = new TrippleDes();
		String password = td.encrypt(pass);
		CPUser user = new CPUser(username, password, firstname, lastname, CNP, email, address, telephone);
		
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
			CPUser user = DataBase.deleteCPUser(id);
			response.put("user", user);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't delete user from database.");
		}
	}
	
	public Map<String, Object> editUser(Long id_user, String firstname, String lastname, String CNP, String email, String address, String telephone) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			CPUser user = DataBase.updateCPUser(id_user, firstname, lastname, CNP, email, address, telephone);
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
