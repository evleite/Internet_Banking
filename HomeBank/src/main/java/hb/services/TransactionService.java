package hb.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import hb.models.Transaction;
import hb.utils.DataBase;
import hb.utils.ResponseUtils;

@WebService
public class TransactionService {
	
	public Map<String, Object> getProductTransactions(String iban) throws Exception{
		Map<String, Object> response = new HashMap<>();
		List<Transaction> transactionList = new ArrayList<>();
		
		List<Transaction> transactionsFromIban = null;
		try {
			transactionsFromIban = DataBase.getTransactionsFromIban(iban);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't get transactions from database.");
		}
		for (Transaction transaction : transactionsFromIban){
			Transaction cloneTrans = new Transaction(
					transaction.getPayer_IBAN(), 
					transaction.getBeneficiary_IBAN(), 
					transaction.getTrans_type(), 
					transaction.getAmount() * (-1), 
					transaction.getId_trans_comm(), 
					transaction.getDetails(), 
					transaction.getStatus(),
					transaction.getCurrency());
			cloneTrans.setId(transaction.getId());
			cloneTrans.setDate(transaction.getDate());
			transactionList.add(cloneTrans);
		}
		
		List<Transaction> transactionsToIban = null;
		try {
			transactionsToIban = DataBase.getTransactionsToIban(iban);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't get transactions from database.");
		}
		
		transactionList.addAll(transactionsToIban);
		
		Collections.sort(transactionList, new Comparator<Transaction>() {
			@Override
		    public int compare(Transaction a, Transaction b) {
		        return b.getDate().compareTo(a.getDate());
		    }
		});
		
		response.put("transactionList", transactionList);
		return ResponseUtils.respondWithSucces(response);
	}
}
