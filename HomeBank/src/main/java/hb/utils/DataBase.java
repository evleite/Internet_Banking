package hb.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hb.models.Account;
import hb.models.AccountAssignement;
import hb.models.Card;
import hb.models.CardAssignement;
import hb.models.Commision;
import hb.models.ExchangeRates;
import hb.models.HBUser;
import hb.models.Rate;
import hb.models.Transaction;
import hb.utils.enums.AuthenticationType;
import hb.utils.enums.CommisionType;

public class DataBase {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");
	private static EntityManager entity = factory.createEntityManager();
	
	public static HBUser getHBUserByUserName(String username) {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from users_hb where username= :username", HBUser.class)
				.setParameter("username", username);
		@SuppressWarnings("unchecked")
		List<HBUser> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList.get(0);
		} else {
			return null;
		}
	}
	public static HBUser getHBUserByCNP(String CNP) {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from users_hb where CNP= :CNP", HBUser.class)
				.setParameter("CNP", CNP);
		@SuppressWarnings("unchecked")
		List<HBUser> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList.get(0);
		} else {
			return null;
		}
	}
	public static HBUser getHBUserById(Long id) {
		HBUser obj = entity.find(HBUser.class, id);
		return obj;
	}
	public static HBUser updateHBUser(Long id, String firstname, String lastname, String CNP, String email, String address, String telephone) {
		HBUser oldObj = entity.find(HBUser.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setAddress(address);
		oldObj.setCNP(CNP);
		oldObj.setEmail(email);
		oldObj.setFirstname(firstname);
		oldObj.setLastname(lastname);
		oldObj.setTelephone(telephone);
		
		transaction.commit();
		
		return oldObj;
	}
	public static HBUser changePassword(Long id, String new_password) {
		HBUser oldObj = entity.find(HBUser.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setPassword(new_password);
				
		transaction.commit();
		
		return oldObj;
	}
	
	public static List<CardAssignement> getCardAssignementList(Long userID) {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from card_assignements where id_user= :id_user", CardAssignement.class)
				.setParameter("id_user", userID);
		@SuppressWarnings("unchecked")
		List<CardAssignement> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return new ArrayList<CardAssignement>();
		}
	}
	
	public static List<AccountAssignement> getAccountAssignementList(Long userID) {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from acc_assignements where id_user= :id_user", AccountAssignement.class)
				.setParameter("id_user", userID);
		@SuppressWarnings("unchecked")
		List<AccountAssignement> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return new ArrayList<AccountAssignement>();
		}
	}
	
	public static Account getAccountByIBAN(String IBAN) {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from accounts where IBAN= :IBAN", Account.class)
				.setParameter("IBAN", IBAN);
		@SuppressWarnings("unchecked")
		List<Account> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList.get(0);
		} else {
			return null;
		}
	}
	
	public static Card getCardByCardNo(String card_no) {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from cards where card_no= :card_no", Card.class)
				.setParameter("card_no", card_no);
		@SuppressWarnings("unchecked")
		List<Card> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList.get(0);
		} else {
			return null;
		}
	}
	
	public static Commision getFalseCommision(){
		int comm_type = CommisionType.N_A.ordinal();
		
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from commisions where comm_type= :comm_type", Commision.class)
				.setParameter("comm_type", comm_type);
		@SuppressWarnings("unchecked")
		List<Commision> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList.get(0);
		} else {
			return null;
		}
	}
	
	public static void persistTransaction(Transaction trans){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(trans);

		transaction.commit();
	}
	
	public static List<Transaction> getTransactionsFromIban(String IBAN) {
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from transactions where payer_IBAN= :payer_IBAN", Transaction.class)
				.setParameter("payer_IBAN", IBAN);
		@SuppressWarnings("unchecked")
		List<Transaction> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return new ArrayList<Transaction>();
		}
	}
	
	public static List<Transaction> getTransactionsToIban(String IBAN) {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from transactions where beneficiary_IBAN= :IBAN", Transaction.class)
				.setParameter("IBAN", IBAN);
		@SuppressWarnings("unchecked")
		List<Transaction> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return new ArrayList<Transaction>();
		}
	}
	
	public static List<ExchangeRates> getExchangeRateList() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from exchange_rates", ExchangeRates.class);
		@SuppressWarnings("unchecked")
		List<ExchangeRates> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return new ArrayList<ExchangeRates>();
		}
	}
}
