package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.Account;
import cp.utils.DataBase;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;

@WebService
public class AccountService {
	
	public Map<String, Object> getAccountList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		//try {
			List<Account> accountList =	DataBase.getAccountList();
			response.put("success", true);
			response.put("accountList", accountList);
		/*} catch (Exception e){
			System.out.println(e);
			return ResponseUtils.respondWithError("Can't get accounts from database.");
		}*/
		return response;
	}

}
