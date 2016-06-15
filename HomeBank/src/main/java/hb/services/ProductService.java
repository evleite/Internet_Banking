package hb.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import hb.models.Account;
import hb.models.AccountAssignement;
import hb.models.Card;
import hb.models.CardAssignement;
import hb.models.HBUser;
import hb.models.Product;
import hb.utils.DataBase;
import hb.utils.ResponseUtils;

@WebService
public class ProductService {
	
	public Map<String, Object> getAllProducts(HBUser user) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		List<AccountAssignement> accountAssignementList = null;
		try {
			accountAssignementList = DataBase.getAccountAssignementList(user.getId());
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't get accountAssignements from database.");
		}
		
		List<CardAssignement> cardAssignementList = null;
		try {
			cardAssignementList = DataBase.getCardAssignementList(user.getId());
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't get cardAssignements from database.");
		}
		
		List<Product> productList = new ArrayList<>();
		for (AccountAssignement accAssing : accountAssignementList){
			Account account = accAssing.getAccount();
			Card card = getCardForAccount(cardAssignementList, account);
			productList.add(new Product(account, card));
		}
		
		response.put("productList", productList);
		return ResponseUtils.respondWithSucces(response);
	}
	
	private Card getCardForAccount(List<CardAssignement> cardAssigns, Account acc){
		for (CardAssignement cardAssign : cardAssigns){
			if (cardAssign.getAccount().getIBAN().equals(acc.getIBAN())){
				return cardAssign.getCard();
			}
		}
		
		return null;
	}
}
