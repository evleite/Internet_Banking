package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import cp.models.CPUser;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;
import cp.utils.TrippleDes;
import cp.models.Account;
import cp.models.Card;
import cp.models.Commision;
import cp.models.ExchangeRates;
import cp.models.Transaction;
import cp.utils.enums.AccountType;
import cp.utils.enums.Currencies;
import cp.utils.enums.TransactionStatus;
import cp.utils.enums.TransactionType;

@WebService
public class LoginFlowService {
	
	public Map<String, Object> logIn(String username, String pass) throws Exception{
		Map<String, Object> response = new HashMap<>();
		TrippleDes td = new TrippleDes();
		
		CPUser logedInUser =	DataBase.getUserByUserName(username);
		
		if (logedInUser != null){
			if (td.decrypt(logedInUser.getPassword()).equals(pass)){
				response.put("user", logedInUser);
				return ResponseUtils.respondWithSucces(response);
			} else {
				return ResponseUtils.respondWithError("Invalid password");
			}
		} else {
			return ResponseUtils.respondWithError("Invalid user");
		}
	}
	
	public void proccessPaiddingTransactions(List<ExchangeRates> exchangeRates){
		List<Transaction> transactionList = DataBase.getTransactionToBeProcceseed();
		
		for (Transaction trans : transactionList){
			proccessTransaction(trans, exchangeRates);
		}		
	}
	
	private void proccessTransaction(Transaction trans, List<ExchangeRates> exchangeRates) {
		Account from = DataBase.getAccountByIBAN(trans.getPayer_IBAN());
		Account to = DataBase.getAccountByIBAN(trans.getBeneficiary_IBAN());
		Double amount = trans.getAmount();
		
		TransactionStatus newStatus = null;
		
		if (from.getAcc_type().toString().equals(AccountType.CREDIT_ACCOUNT.toString())){
			Card creditCard = DataBase.getCreditCardForAccount(from.getId());
			if (amount <= creditCard.getDaily_limit()){
				newStatus = TransactionStatus.PROCESSED;
				if (trans.getTrans_type() == TransactionType.INTERN_SAME_CURRENCY){
					transferWithSameCurrency(from, to, amount);
				} else {
					transferWithDiffCurrency(from, to, amount, exchangeRates);
				}
				trans.setStatus(newStatus);
			} else {
				newStatus = TransactionStatus.REJECTED;
				trans.setDetails(trans.getDetails() + rejectionReason("transfer exceed card daily limit"));
				trans.setStatus(newStatus);
			}
		} else {
			if (amount <= from.getBalance()){
				newStatus = TransactionStatus.PROCESSED;
				if (trans.getTrans_type() == TransactionType.INTERN_SAME_CURRENCY){
					transferWithSameCurrency(from, to, amount);
				} else {
					transferWithDiffCurrency(from, to, amount, exchangeRates);
				}
				trans.setStatus(newStatus);
			} else {
				newStatus = TransactionStatus.REJECTED;
				trans.setDetails(trans.getDetails() + rejectionReason("insufficient resources"));
				trans.setStatus(newStatus);
			}
		}
		
		DataBase.commit();
				
		/*try {
			DataBase.updateTransaction(trans);
			return ResponseUtils.respondWithSucces();
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Failed to update transaction status in database.");
		}*/
	}
	
	private String rejectionReason(String reason){
		return "(Rejection reason: " + reason + ")";
	}
	
	private void transferWithSameCurrency(Account from, Account to, Double amount){
		from.setBalance(from.getBalance() - amount);
		to.setBalance(to.getBalance() + amount);
	}
	
	private void transferWithDiffCurrency(Account from, Account to, Double amount, List<ExchangeRates> exchangeRates){
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
		
		from.setBalance(from.getBalance() - amount);
		to.setBalance(to.getBalance() + amountToInValute);
	}

}
