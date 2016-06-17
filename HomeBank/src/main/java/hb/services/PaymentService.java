package hb.services;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.mysql.fabric.xmlrpc.base.Data;

import hb.models.Account;
import hb.models.Card;
import hb.models.Commision;
import hb.models.ExchangeRates;
import hb.models.Transaction;
import hb.utils.DataBase;
import hb.utils.ResponseUtils;
import hb.utils.enums.Currencies;
import hb.utils.enums.TransactionStatus;
import hb.utils.enums.TransactionType;

@WebService
public class PaymentService {
	
	public Map<String, Object> internalPayment(String payer_IBAN, String beneficiary_IBAN, Double amount, String details) throws Exception{
		TransactionStatus status = null;
		TransactionType type = null;
		Commision comm = DataBase.getFalseCommision();
		
		Account payer = DataBase.getAccountByIBAN(payer_IBAN);
		Account beneficiary = DataBase.getAccountByIBAN(beneficiary_IBAN);
		if (beneficiary == null){
			status = TransactionStatus.REJECTED;
		} else {
			status = TransactionStatus.WAITING;
		}
		
		if (payer.getCurrency().toString().equals(beneficiary.getCurrency().toString())){
			type = TransactionType.INTERN_SAME_CURRENCY;
		} else {
			type = TransactionType.INTERN_DIFF_CURRENCY;
		}
		
		Transaction trans = new Transaction(payer_IBAN, beneficiary_IBAN, type, amount, comm, details, status, payer.getCurrency());
		try {
			DataBase.persistTransaction(trans);
			return ResponseUtils.respondWithSucces();
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Failed to store transaction in database.");
		}
	}
	
	public Map<String, Object> currentToSavingPayment(String payer_IBAN, String beneficiary_IBAN, Double amount, String details, List<ExchangeRates> exchangeRates) throws Exception{
		TransactionStatus status = null;
		TransactionType type = null;
		Commision comm = DataBase.getFalseCommision();
				
		Account currentAcc = DataBase.getAccountByIBAN(payer_IBAN);
		Account savingAcc = DataBase.getAccountByIBAN(beneficiary_IBAN);
		
		if (currentAcc.getCurrency().toString().equals(savingAcc.getCurrency().toString())){
			type = TransactionType.CURR_SAVINGS_SAME_CURRENCY;
		} else {
			type = TransactionType.CURR_SAVINGS_DIFF_CURRENCY;
		}
		
		Transaction trans = null;
		if (amount <= currentAcc.getBalance()){
			status = TransactionStatus.PROCESSED;
			if (type == TransactionType.CURR_SAVINGS_SAME_CURRENCY){
				transferWithSameCurrency(currentAcc, savingAcc, amount);
			} else {
				transferWithDiffCurrency(currentAcc, savingAcc, amount, exchangeRates);
			}
			trans = new Transaction(payer_IBAN, beneficiary_IBAN, type, amount, comm, details, status, currentAcc.getCurrency());
		} else {
			status = TransactionStatus.REJECTED;
			trans = new Transaction(payer_IBAN, beneficiary_IBAN, type, amount, comm, details + rejectionReason("insufficient resources"), status, currentAcc.getCurrency());
		}
				
		try {
			DataBase.persistTransaction(trans);
			return ResponseUtils.respondWithSucces();
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Failed to store transaction in database.");
		}
	}
	
	public Map<String, Object> savingToCurrentPayment(String payer_IBAN, String beneficiary_IBAN, Double amount, String details, List<ExchangeRates> exchangeRates) throws Exception{
		TransactionStatus status = null;
		TransactionType type = null;
		Commision comm = DataBase.getFalseCommision();
				
		Account savingAcc = DataBase.getAccountByIBAN(payer_IBAN);
		Account currentAcc = DataBase.getAccountByIBAN(beneficiary_IBAN);
		
		if (savingAcc.getCurrency().toString().equals(currentAcc.getCurrency().toString())){
			type = TransactionType.SAVINGS_CURR_SAME_CURRENCY;
		} else {
			type = TransactionType.SAVINGS_CURR_DIFF_CURRENCY;
		}
		
		Transaction trans = null;
		if (amount <= savingAcc.getBalance()){
			status = TransactionStatus.PROCESSED;
			if (type == TransactionType.SAVINGS_CURR_SAME_CURRENCY){
				transferWithSameCurrency(savingAcc, currentAcc, amount);
			} else {
				transferWithDiffCurrency(savingAcc, currentAcc, amount, exchangeRates);
			}
			trans = new Transaction(payer_IBAN, beneficiary_IBAN, type, amount, comm, details, status, currentAcc.getCurrency());
		} else {
			status = TransactionStatus.REJECTED;
			trans = new Transaction(payer_IBAN, beneficiary_IBAN, type, amount, comm, details + rejectionReason("insufficient resources"), status, currentAcc.getCurrency());
		}
				
		try {
			DataBase.persistTransaction(trans);
			return ResponseUtils.respondWithSucces();
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Failed to store transaction in database.");
		}
	}
	
	public Map<String, Object> currentToCreditPayment(String payer_IBAN, String beneficiary_IBAN, Double amount, String details, List<ExchangeRates> exchangeRates) throws Exception{
		TransactionStatus status = null;
		TransactionType type = null;
		Commision comm = DataBase.getFalseCommision();
				
		Account currentAcc = DataBase.getAccountByIBAN(payer_IBAN);
		Account creditAcc = DataBase.getAccountByIBAN(beneficiary_IBAN);
		
		if (currentAcc.getCurrency().toString().equals(creditAcc.getCurrency().toString())){
			type = TransactionType.CURR_CREDIT_SAME_CURRENCY;
		} else {
			type = TransactionType.CURR_CREDIT_DIFF_CURRENCY;
		}
		
		Transaction trans = null;
		if (amount <= currentAcc.getBalance()){
			status = TransactionStatus.PROCESSED;
			if (type == TransactionType.CURR_CREDIT_SAME_CURRENCY){
				transferWithSameCurrency(currentAcc, creditAcc, amount);
			} else {
				transferWithDiffCurrency(currentAcc, creditAcc, amount, exchangeRates);
			}
			trans = new Transaction(payer_IBAN, beneficiary_IBAN, type, amount, comm, details, status, currentAcc.getCurrency());
		} else {
			status = TransactionStatus.REJECTED;
			trans = new Transaction(payer_IBAN, beneficiary_IBAN, type, amount, comm, details + rejectionReason("insufficient resources"), status, currentAcc.getCurrency());
		}
				
		try {
			DataBase.persistTransaction(trans);
			return ResponseUtils.respondWithSucces();
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Failed to store transaction in database.");
		}
	}
	
	public Map<String, Object> creditToCurrentPayment(String card_no, String payer_IBAN, String beneficiary_IBAN, Double amount, String details, List<ExchangeRates> exchangeRates) throws Exception{
		TransactionStatus status = null;
		TransactionType type = null;
		Commision comm = DataBase.getFalseCommision();
				
		Account creditAcc = DataBase.getAccountByIBAN(payer_IBAN);
		Account currentAcc = DataBase.getAccountByIBAN(beneficiary_IBAN);
		Card creditCard = DataBase.getCardByCardNo(card_no.replaceAll("\\s",""));
		
		if (creditAcc.getCurrency().toString().equals(currentAcc.getCurrency().toString())){
			type = TransactionType.CREDIT_CURR_SAME_CURRENCY;
		} else {
			type = TransactionType.CREDIT_CURR_DIFF_CURRENCY;
		}
		
		Transaction trans = null;
		if (amount <= creditCard.getDaily_limit()){
			status = TransactionStatus.PROCESSED;
			if (type == TransactionType.CREDIT_CURR_SAME_CURRENCY){
				transferWithSameCurrency(creditAcc, currentAcc, amount);
			} else {
				transferWithDiffCurrency(creditAcc, currentAcc, amount, exchangeRates);
			}
			trans = new Transaction(payer_IBAN, beneficiary_IBAN, type, amount, comm, details, status, currentAcc.getCurrency());
		} else {
			status = TransactionStatus.REJECTED;
			trans = new Transaction(payer_IBAN, beneficiary_IBAN, type, amount, comm, details + rejectionReason("transfer exceed card daily limit"), status, currentAcc.getCurrency());
		}
				
		try {
			DataBase.persistTransaction(trans);
			return ResponseUtils.respondWithSucces();
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Failed to store transaction in database.");
		}
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
