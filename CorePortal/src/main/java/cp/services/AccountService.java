package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.Account;
import cp.models.Commision;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;
import cp.utils.enums.AccountType;
import cp.utils.enums.CommisionType;
import cp.utils.enums.Currencies;

@WebService
public class AccountService {
	
	public Map<String, Object> getAccountList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		List<Account> accountList =	DataBase.getAccountList();
		if(accountList != null){
			response.put("accountList", accountList);
			return ResponseUtils.respondWithSucces(response);
		} else {
			return ResponseUtils.respondWithError("Can't get accounts from database.");
		}
	}
	
	public Map<String, Object> addAccount(String type, String balance) throws Exception{
		AccountType accType = AccountType.valueOf(type);
		Double dBalance = Double.parseDouble(balance);
		
		Commision comm1 = new Commision(CommisionType.CURRENT_ACOUNT, 5.0, "Comm for admin curr acc");
		Account acc1 = new Account("HBCP00000000000000001111", accType, Currencies.RON, comm1, dBalance);
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			DataBase.persistAccount(acc1);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't save new account in database.");
		}
	}
}
