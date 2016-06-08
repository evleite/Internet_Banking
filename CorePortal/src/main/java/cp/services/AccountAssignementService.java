package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.Account;
import cp.models.AccountAssignement;
import cp.models.Commision;
import cp.models.HBUser;
import cp.models.Rate;
import cp.utils.AccountUtils;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;
import cp.utils.enums.AccountType;
import cp.utils.enums.Currencies;

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
