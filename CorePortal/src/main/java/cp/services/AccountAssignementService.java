package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.Account;
import cp.models.AccountAssignement;
import cp.models.HBUser;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;

@WebService
public class AccountAssignementService {
	
	public Map<String, Object> getAccountAssignementList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			List<AccountAssignement> accountAssignementList =	DataBase.getAccountAssignementList();
			response.put("accountAssignementList", accountAssignementList);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't get accountAssignementList from database.");
		}
		
	}
	
	public Map<String, Object> addAccountAssignement(Long id_account, Long id_user) throws Exception{
		HBUser user = DataBase.getHBUserById(id_user);
		Account account = DataBase.getAccountById(id_account);
		AccountAssignement accAsing = new AccountAssignement(user, account);
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			DataBase.persistAccountAssignement(accAsing);
			response.put("accountAssignement", accAsing);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't add new accountAssignement to database.");
		}
	}
	
	public Map<String, Object> deleteAccountAssignement(Long id) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			AccountAssignement accAsign = DataBase.deleteAccountAssignement(id);
			response.put("accountAssignement", accAsign);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't delete accountAssignement from database.");
		}
	}

}
