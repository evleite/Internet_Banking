package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.Account;
import cp.models.Card;
import cp.models.Commision;
import cp.models.Rate;
import cp.utils.AccountUtils;
import cp.utils.CardUtils;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;
import cp.utils.enums.AccountType;
import cp.utils.enums.CardType;
import cp.utils.enums.Currencies;

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
	
	public Map<String, Object> addCard(String type, Double daily_limit, String validity) throws Exception{
		CardType cardType = CardType.valueOf(type);
		
		String cardNo = CardUtils.generateCardNo();
		
		Card card = new Card(cardNo, cardType, validity, daily_limit);
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			DataBase.persistCard(card);;
			response.put("card", card);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't add new card in database.");
		}
	}
	
	public Map<String, Object> deleteCard(Long id) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			Card card = DataBase.deleteCard(id);
			response.put("card", card);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't delete card from database.");
		}
	}
	
	public Map<String, Object> editCard(Long id_card, Double daily_limit, String validity) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			Card card = DataBase.updateCard(id_card, daily_limit, validity);
			response.put("card", card);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't update card from database.");
		}
	}
}
