package hb.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import hb.models.Account;
import hb.models.AccountAssignement;
import hb.models.Card;
import hb.models.CardAssignement;
import hb.models.Commision;
import hb.models.ExchangeRates;
import hb.models.HBUser;
import hb.models.Product;
import hb.models.Rate;
import hb.models.Token;
import hb.models.TokenAssignement;
import hb.models.Transaction;

public class JsonUtils {
	
	@SuppressWarnings("unchecked")
	public static JSONObject mapToJson(Map<String, Object> map){
		JSONObject json = new JSONObject();
		for (Entry<String, Object> entry : map.entrySet()){
			json.put(entry.getKey(), entry.getValue());
		}
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> JSONArray listOfPrimitivesToJsonAray(List<T> list){
		JSONArray json = new JSONArray();
		for (T obj : list){
			json.add(obj);
		}
		return json;
	}
	
	/* Account model */
	@SuppressWarnings("unchecked")
	public static JSONObject accountToJson(Account acc){
		JSONObject json = new JSONObject();
		
		json.put("acc_type", acc.getAcc_type());
		json.put("balance", acc.getBalance());
		json.put("currency", acc.getCurrency());
		json.put("IBAN", acc.getIBAN());
		json.put("id", acc.getId());
		json.put("comm", commisionToJson(acc.getId_comm_admin()));
		json.put("rate", rateToJson(acc.getId_rate()));
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray accountListToJson(List<Account> list){
		JSONArray json = new JSONArray();
		for (Account obj : list){
			json.add(accountToJson(obj));
		}
		return json;
	}
	
	/* AccountAssignement model */
	@SuppressWarnings("unchecked")
	public static JSONObject accountAssignementToJson(AccountAssignement accasg){
		JSONObject json = new JSONObject();
		
		json.put("account", accountToJson(accasg.getAccount()));
		json.put("id", accasg.getId());
		json.put("user", userHbToJson(accasg.getUser()));
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray accountAssignementListToJson(List<AccountAssignement> list){
		JSONArray json = new JSONArray();
		for (AccountAssignement obj : list){
			json.add(accountAssignementToJson(obj));
		}
		return json;
	}
	
	/* Card model */
	@SuppressWarnings("unchecked")
	public static JSONObject cardToJson(Card card){
		JSONObject json = new JSONObject();
		
		json.put("card_no", card.getCard_no().substring(0, 4) + " " +
							card.getCard_no().substring(4, 8) + " " +
							card.getCard_no().substring(8, 12) + " " +
							card.getCard_no().substring(12));
		json.put("type", card.getCard_type());
		json.put("daily_limit", card.getDaily_limit());
		json.put("id", card.getId());
		json.put("card_validity", card.getValidity());
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray cardListToJson(List<Card> list){
		JSONArray json = new JSONArray();
		for (Card obj : list){
			json.add(cardToJson(obj));
		}
		return json;
	}

	/* CardAssignement model */
	@SuppressWarnings("unchecked")
	public static JSONObject cardAssignementToJson(CardAssignement cardasg){
		JSONObject json = new JSONObject();
		
		json.put("account", accountToJson(cardasg.getAccount()));
		json.put("card", cardToJson(cardasg.getCard()));
		json.put("user", userHbToJson(cardasg.getUser()));
		json.put("id", cardasg.getId());
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray cardAssignementListToJson(List<CardAssignement> list){
		JSONArray json = new JSONArray();
		for (CardAssignement obj : list){
			json.add(cardAssignementToJson(obj));
		}
		return json;
	}
	
	/* Commision model */
	@SuppressWarnings("unchecked")
	public static JSONObject commisionToJson(Commision comm){
		JSONObject json = new JSONObject();
		
		json.put("amount", comm.getAmount());
		json.put("type", comm.getComm_type());
		json.put("details", comm.getDetails());
		json.put("id", comm.getId());
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray commisionListToJson(List<Commision> list){
		JSONArray json = new JSONArray();
		for (Commision obj : list){
			json.add(commisionToJson(obj));
		}
		return json;
	}
	
	/* ExchangeRates model */
	@SuppressWarnings("unchecked")
	public static JSONObject exchangeRateToJson(ExchangeRates exgrate){
		JSONObject json = new JSONObject();
		
		json.put("buy", exgrate.getBuy());
		json.put("currency", exgrate.getCurrency());
		json.put("id", exgrate.getId());
		json.put("sell", exgrate.getSell());
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray exchangeRateListToJson(List<ExchangeRates> list){
		JSONArray json = new JSONArray();
		for (ExchangeRates obj : list){
			json.add(exchangeRateToJson(obj));
		}
		return json;
	}
	
	/* Rate model */
	@SuppressWarnings("unchecked")
	public static JSONObject rateToJson(Rate rate){
		JSONObject json = new JSONObject();
		
		json.put("details", rate.getDetails());
		json.put("id", rate.getId());
		json.put("type", rate.getRate_type());
		json.put("year_percentage", rate.getYear_percentage());
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray rateListToJson(List<Rate> list){
		JSONArray json = new JSONArray();
		for (Rate obj : list){
			json.add(rateToJson(obj));
		}
		return json;
	}
	
	/* Token model */
	@SuppressWarnings("unchecked")
	public static JSONObject tokenToJson(Token token){
		JSONObject json = new JSONObject();
		
		json.put("id", token.getId());
		json.put("serial", token.getSerial());
		json.put("type", token.getToken_type());
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray tokenListToJson(List<Token> list){
		JSONArray json = new JSONArray();
		for (Token obj : list){
			json.add(tokenToJson(obj));
		}
		return json;
	}
	
	/* TokenAssignement model */
	@SuppressWarnings("unchecked")
	public static JSONObject tokenAssignementToJson(TokenAssignement tokenasg){
		JSONObject json = new JSONObject();
		
		json.put("id", tokenasg.getId());
		json.put("token", tokenToJson(tokenasg.getToken()));
		json.put("user", userHbToJson(tokenasg.getUser()));
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray tokenAssignementListToJson(List<TokenAssignement> list){
		JSONArray json = new JSONArray();
		for (TokenAssignement obj : list){
			json.add(tokenAssignementToJson(obj));
		}
		return json;
	}
	
	/* Transaction model */
	@SuppressWarnings("unchecked")
	public static JSONObject transactionToJson(Transaction trans){
		JSONObject json = new JSONObject();
		
		json.put("amount", trans.getAmount());
		json.put("beneficiary_IBAN", trans.getBeneficiary_IBAN());
		json.put("datails", trans.getDetails());
		json.put("id", trans.getId());
		json.put("date", DateUtils.getHumanRedableDate(trans.getDate()));
		json.put("commision", commisionToJson(trans.getId_trans_comm()));
		json.put("payer_IBAN", trans.getPayer_IBAN());
		json.put("status", trans.getStatus());
		json.put("type", trans.getTrans_type());
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray transactionListToJson(List<Transaction> list){
		JSONArray json = new JSONArray();
		for (Transaction obj : list){
			json.add(transactionToJson(obj));
		}
		return json;
	}
	
	/* HBUser model*/
	@SuppressWarnings("unchecked")
	public static JSONObject userHbToJson(HBUser user){
		JSONObject json = new JSONObject();
		
		json.put("address", user.getAddress());
		json.put("CNP", user.getCNP());
		json.put("email", user.getEmail());
		json.put("firstname", user.getFirstname());
		json.put("lastname", user.getLastname());
		json.put("id", user.getId());
		json.put("telephone", user.getTelephone());
		json.put("username", user.getUsername());
		json.put("auth_type", user.getAuth_type());
		
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray userHbListToJson(List<HBUser> list){
		JSONArray json = new JSONArray();
		for (HBUser obj : list){
			json.add(userHbToJson(obj));
		}
		return json;
	}
	
	/* Product model */
	@SuppressWarnings("unchecked")
	public static JSONObject productToJson(Product prod){
		JSONObject json = new JSONObject();
		
		json.put("account", accountToJson(prod.getAccount()));
		json.put("card", prod.getCard() != null ? cardToJson(prod.getCard()) : null);		
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray productListToJson(List<Product> list){
		JSONArray json = new JSONArray();
		for (Product obj : list){
			json.add(productToJson(obj));
		}
		return json;
	}
}
