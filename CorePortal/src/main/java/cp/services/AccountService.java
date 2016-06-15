package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.Account;
import cp.models.Commision;
import cp.models.Rate;
import cp.utils.AccountUtils;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;
import cp.utils.enums.AccountType;
import cp.utils.enums.Currencies;

@WebService
public class AccountService {
	
	public Map<String, Object> getAccountList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			List<Account> accountList =	DataBase.getAccountList();
			response.put("accountList", accountList);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't get accounts from database.");
		}
	}
	
	public Map<String, Object> addAccount(String type, String currency, Double balance, Long id_com, Long id_rate) throws Exception{
		AccountType accType = AccountType.valueOf(type);
		Currencies currencyType = Currencies.valueOf(currency);
		Commision comm = DataBase.getCommisionById(id_com);
		Rate rate = DataBase.getRateById(id_rate);
		String IBAN = AccountUtils.generateIBAN(accType, currencyType);
		
		Account acc = new Account(IBAN, accType, currencyType, comm, rate, balance);
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			DataBase.persistAccount(acc);
			response.put("account", acc);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't add new account in database.");
		}
	}
	
	public Map<String, Object> deleteAccount(Long id) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			Account acc = DataBase.deleteAccount(id);
			response.put("account", acc);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't delete account from database.");
		}
	}
	
	public Map<String, Object> editAccount(Long id_account, Long id_com, Long id_rate, Double balance) throws Exception{
		Commision comm = DataBase.getCommisionById(id_com);
		Rate rate = DataBase.getRateById(id_rate);
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			Account acc = DataBase.updateAccount(id_account, comm, rate, balance);
			response.put("account", acc);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't update account from database.");
		}
	}
}
