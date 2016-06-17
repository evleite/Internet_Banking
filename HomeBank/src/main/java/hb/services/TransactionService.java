package hb.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import hb.models.Account;
import hb.models.ExchangeRates;
import hb.models.Transaction;
import hb.utils.DataBase;
import hb.utils.ResponseUtils;
import hb.utils.enums.Currencies;

@WebService
public class TransactionService {
	
	public Map<String, Object> getProductTransactions(String iban, List<ExchangeRates> exchangeRates) throws Exception{
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
		for (Transaction transaction : transactionsToIban){
			Double amountToInValute = getAmountForBeneficiaryIban(transaction.getPayer_IBAN(), transaction.getBeneficiary_IBAN(), transaction.getAmount(), exchangeRates);
			Currencies currencyToInValute =  DataBase.getAccountByIBAN(transaction.getBeneficiary_IBAN()).getCurrency();
			Transaction cloneTrans = new Transaction(
					transaction.getPayer_IBAN(), 
					transaction.getBeneficiary_IBAN(), 
					transaction.getTrans_type(), 
					amountToInValute, 
					transaction.getId_trans_comm(), 
					transaction.getDetails(), 
					transaction.getStatus(),
					currencyToInValute);
			cloneTrans.setId(transaction.getId());
			cloneTrans.setDate(transaction.getDate());
			transactionList.add(cloneTrans);
		}
				
		Collections.sort(transactionList, new Comparator<Transaction>() {
			@Override
		    public int compare(Transaction a, Transaction b) {
		        return b.getDate().compareTo(a.getDate());
		    }
		});
		
		response.put("transactionList", transactionList);
		return ResponseUtils.respondWithSucces(response);
	}
	
	private Double getAmountForBeneficiaryIban(String payer_IBAN, String beneficiary_IBAN, Double amount, List<ExchangeRates> exchangeRates){
		Account from = DataBase.getAccountByIBAN(payer_IBAN);
		Account to = DataBase.getAccountByIBAN(beneficiary_IBAN);
		
		if (from.getCurrency().toString().equals(to.getCurrency().toString())){
			return amount;
		}
		
		Double amountFromInRON = null;
		if (from.getCurrency().equals(Currencies.RON)){
			amountFromInRON = amount;
		} else {
			ExchangeRates exchangeRate = null;
			for (ExchangeRates er : exchangeRates){
				if (er.getCurrency().toString().equals(from.getCurrency().toString())){
					exchangeRate = er;
				}
			}
			amountFromInRON = amount * exchangeRate.getBuy();
		}
		
		Double amountToInValute = null;
		if (to.getCurrency().equals(Currencies.RON)){
			amountToInValute = amountFromInRON;
		} else {
			ExchangeRates exchangeRate = null;
			for (ExchangeRates er : exchangeRates){
				if (er.getCurrency().toString().equals(to.getCurrency().toString())){
					exchangeRate = er;
				}
			}
			amountToInValute = amountFromInRON / exchangeRate.getSell();
		}
		
		return amountToInValute;
	}
}
