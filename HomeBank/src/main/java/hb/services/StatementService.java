package hb.services;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import hb.models.Account;
import hb.models.Card;
import hb.models.PDFModel;
import hb.models.Transaction;
import hb.utils.DataBase;
import hb.utils.ResponseUtils;
import hb.utils.Statement;

@WebService
public class StatementService {
	
	public Map<String, Object> printStatement(String iban, List<Transaction> transactionList){
		Map<String, Object> response = new HashMap<>();
		
		Account account = DataBase.getAccountByIBAN(iban);
		Card card = DataBase.getCardForAccount(account.getId());
		PDFModel model = new PDFModel(account, card, transactionList);
		
		try {
		    File file = Statement.generateStatement(model);
		    response.put("file", file);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Failed to generate statement.");
		}
	}
}
