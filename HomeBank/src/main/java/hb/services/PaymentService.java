package hb.services;

import java.util.Map;

import javax.jws.WebService;

import hb.models.Account;
import hb.models.Commision;
import hb.models.Transaction;
import hb.utils.DataBase;
import hb.utils.ResponseUtils;
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
		
		Transaction trans = new Transaction(payer_IBAN, beneficiary_IBAN, type, amount, comm, details, status);
		try {
			DataBase.persistTransaction(trans);
			return ResponseUtils.respondWithSucces();
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Failed to store transaction in database.");
		}
	}
	
}
