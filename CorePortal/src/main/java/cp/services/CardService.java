package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.Account;
import cp.models.Card;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;

@WebService
public class CardService {
	
	public Map<String, Object> getCardList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		List<Card> cardList =	DataBase.getCardList();
		if(cardList != null){
			response.put("cardList", cardList);
			return ResponseUtils.respondWithSucces(response);
		} else {
			return ResponseUtils.respondWithError("Can't get cards from database.");
		}
	}
}
