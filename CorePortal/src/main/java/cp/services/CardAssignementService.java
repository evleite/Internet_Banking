package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.Account;
import cp.models.AccountAssignement;
import cp.models.Card;
import cp.models.CardAssignement;
import cp.models.HBUser;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;

@WebService
public class CardAssignementService {
	
	public Map<String, Object> getCardAssignementList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		List<CardAssignement> cardAssignementList =	DataBase.getCardAssignementList();
		
		if(cardAssignementList != null){
			response.put("cardAssignementList", cardAssignementList);
			return ResponseUtils.respondWithSucces(response);
		} else {
			return ResponseUtils.respondWithError("Can't get cardAssignementList from database.");
		}
		
	}
	
	public Map<String, Object> addCardAssignement(Long id_account, Long id_user, Long id_card) throws Exception{
		HBUser user = DataBase.getHBUserById(id_user);
		Account account = DataBase.getAccountById(id_account);
		Card card = DataBase.getCardById(id_card);
		CardAssignement cardAsing = new CardAssignement(user, card, account);
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			DataBase.persistCardAssignement(cardAsing);
			response.put("cardAssignement", cardAsing);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't add new cardAssignement to database.");
		}
	}
	
	public Map<String, Object> deleteCardAssignement(Long id) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			CardAssignement cardAsing = DataBase.deleteCardAssignement(id);
			response.put("cardAssignement", cardAsing);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't delete cardAssignement from database.");
		}
	}

}
