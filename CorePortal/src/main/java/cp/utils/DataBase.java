package cp.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import cp.models.Account;
import cp.models.AccountAssignement;
import cp.models.Card;
import cp.models.CardAssignement;
import cp.models.Commision;
import cp.models.ExchangeRates;
import cp.models.HBUser;
import cp.models.Rate;
import cp.models.Token;
import cp.models.TokenAssignement;
import cp.models.CPUser;
import cp.utils.enums.AuthenticationType;
import cp.utils.enums.RatesType;
import cp.utils.enums.TransactionStatus;
import cp.models.Transaction;

public class DataBase {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");
	private static EntityManager entity = factory.createEntityManager();
	
	/* Account model */
	public static void persistAccount(Account acc){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(acc);

		transaction.commit();
	}
	public static List<Account> getAccountList() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from accounts", Account.class);
		@SuppressWarnings("unchecked")
		List<Account> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return null;
		}
	}
	public static Account updateAccount(Long id, Commision comm, Rate rate) {
		Account oldObj = entity.find(Account.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setId_comm_admin(comm);
		oldObj.setId_rate(rate);
		
		transaction.commit();
		
		return oldObj;
	}
	public static Account deleteAccount(Long id){
		Account obj = entity.find(Account.class, id);
		
		if (obj != null) {
			EntityTransaction transaction = entity.getTransaction();

			transaction.begin();
			entity.remove(obj);
			transaction.commit();
		}
		
		return obj;
	}
	public static Account getAccountById(Long id) {
		Account obj = entity.find(Account.class, id);
		return obj;
	}
	
	/* AccountAssignement model */
	public static void persistAccountAssignement(AccountAssignement accAsig){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(accAsig);

		transaction.commit();
	}
	public static List<AccountAssignement> getAccountAssignementList() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from acc_assignements", AccountAssignement.class);
		@SuppressWarnings("unchecked")
		List<AccountAssignement> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return null;
		}
	}
	public static AccountAssignement deleteAccountAssignement(Long id){
		AccountAssignement obj = entity.find(AccountAssignement.class, id);
		
		if (obj != null) {
			EntityTransaction transaction = entity.getTransaction();

			transaction.begin();
			entity.remove(obj);
			transaction.commit();
		}
		
		return obj;
	}
	
	/* Card model */
	public static void persistCard(Card card){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(card);

		transaction.commit();
	}
	public static List<Card> getCardList() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from cards", Card.class);
		@SuppressWarnings("unchecked")
		List<Card> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return null;
		}
	}
	public static Card updateCard(Long id, Double daily_limit, String validity) {
		Card oldObj = entity.find(Card.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setValidity(validity);
		oldObj.setDaily_limit(daily_limit);
		
		transaction.commit();
		
		return oldObj;
	}
	public static Card deleteCard(Long id){
		Card obj = entity.find(Card.class, id);
		
		if (obj != null) {
			EntityTransaction transaction = entity.getTransaction();

			transaction.begin();
			entity.remove(obj);
			transaction.commit();
		}
		
		return obj;
	}
	public static Card getCardById(Long id) {
		Card obj = entity.find(Card.class, id);
		return obj;
	}
	
	/* CardAssignement model */
	public static void persistCardAssignement(CardAssignement cardAsig){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(cardAsig);

		transaction.commit();
	}
	public static List<CardAssignement> getCardAssignementList() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from card_assignements", CardAssignement.class);
		@SuppressWarnings("unchecked")
		List<CardAssignement> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return null;
		}
	}
	public static CardAssignement deleteCardAssignement(Long id){
		CardAssignement obj = entity.find(CardAssignement.class, id);
		
		if (obj != null) {
			EntityTransaction transaction = entity.getTransaction();

			transaction.begin();
			entity.remove(obj);
			transaction.commit();
		}
		
		return obj;
	}
	
	/* Commision model*/
	public static void persistCommision(Commision comm){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(comm);

		transaction.commit();
	}
	public static List<Commision> getCommisionList() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from commisions", Commision.class);
		@SuppressWarnings("unchecked")
		List<Commision> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return null;
		}
	}
	public static Commision getCommisionById(Long id) {
		Commision obj = entity.find(Commision.class, id);
		return obj;
	}
	public static Commision updateCommision(Long id, Double amount, String details) {
		Commision oldObj = entity.find(Commision.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setAmount(amount);;
		oldObj.setDetails(details);
		
		transaction.commit();
		
		return oldObj;
	}
	public static Commision deleteCommision(Long id){
		Commision obj = entity.find(Commision.class, id);
		
		if (obj != null) {
			EntityTransaction transaction = entity.getTransaction();

			transaction.begin();
			entity.remove(obj);
			transaction.commit();
		}
		
		return obj;
	}
	
	/* ExchangeRates model */
	public static void persistExchangeRate(ExchangeRates exgRate){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(exgRate);

		transaction.commit();
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
			return null;
		}
	}
	public static ExchangeRates getExchangeRateById(Long id) {
		ExchangeRates obj = entity.find(ExchangeRates.class, id);
		return obj;
	}
	public static ExchangeRates updateExchangeRate(Long id, Double buy, Double sell) {
		ExchangeRates oldObj = entity.find(ExchangeRates.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setBuy(buy);
		oldObj.setSell(sell);
		
		transaction.commit();
		
		return oldObj;
	}
	public static ExchangeRates deleteExchangeRate(Long id){
		ExchangeRates obj = entity.find(ExchangeRates.class, id);
		
		if (obj != null) {
			EntityTransaction transaction = entity.getTransaction();

			transaction.begin();
			entity.remove(obj);
			transaction.commit();
		}
		
		return obj;
	}
	
	/* Rate model */
	public static void persistRate(Rate rate){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(rate);

		transaction.commit();
	}
	public static List<Rate> getRateList() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from rates", Rate.class);
		@SuppressWarnings("unchecked")
		List<Rate> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return null;
		}
	}
	public static Rate getRateById(Long id) {
		Rate obj = entity.find(Rate.class, id);
		return obj;
	}
	public static Rate updateRate(Long id, Double year_percentage, String details) {
		Rate oldObj = entity.find(Rate.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setYear_percentage(year_percentage);
		oldObj.setDetails(details);
		
		transaction.commit();
		
		return oldObj;
	}
	public static Rate deleteRate(Long id){
		Rate obj = entity.find(Rate.class, id);
		
		if (obj != null) {
			EntityTransaction transaction = entity.getTransaction();

			transaction.begin();
			entity.remove(obj);
			transaction.commit();
		}
		
		return obj;
	}
	
	/* CPUser model */
	public static void persistUser(CPUser user){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(user);

		transaction.commit();
	}
	public static List<CPUser> getCPUserList() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from users_cp", CPUser.class);
		@SuppressWarnings("unchecked")
		List<CPUser> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return null;
		}
	}
	public static CPUser getUserByUserName(String username) {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from users_cp where username= :username", CPUser.class)
				.setParameter("username", username);
		@SuppressWarnings("unchecked")
		List<CPUser> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList.get(0);
		} else {
			return null;
		}
	}
	public static CPUser getUserByCNP(String CNP) {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from users_cp where CNP= :CNP", CPUser.class)
				.setParameter("CNP", CNP);
		@SuppressWarnings("unchecked")
		List<CPUser> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList.get(0);
		} else {
			return null;
		}
	}
	public static CPUser getCPUserById(Long id) {
		CPUser obj = entity.find(CPUser.class, id);
		return obj;
	}
	public static CPUser deleteCPUser(Long id){
		CPUser obj = entity.find(CPUser.class, id);
		
		if (obj != null) {
			EntityTransaction transaction = entity.getTransaction();

			transaction.begin();
			entity.remove(obj);
			transaction.commit();
		}
		
		return obj;
	}
	public static CPUser updateCPUser(Long id, String firstname, String lastname, String CNP, String email, String address, String telephone) {
		CPUser oldObj = entity.find(CPUser.class, id);
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
	public static CPUser changePassword(Long id, String new_password) {
		CPUser oldObj = entity.find(CPUser.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setPassword(new_password);
				
		transaction.commit();
		
		return oldObj;
	}
	
	/* HBUser model */
	public static void persistUser(HBUser user){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(user);

		transaction.commit();
	}
	public static List<HBUser> getHBUserList() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from users_hb", HBUser.class);
		@SuppressWarnings("unchecked")
		List<HBUser> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return null;
		}
	}
	public static HBUser getHBUserById(Long id) {
		HBUser obj = entity.find(HBUser.class, id);
		return obj;
	}
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
	public static HBUser deleteHBUser(Long id){
		HBUser obj = entity.find(HBUser.class, id);
		
		if (obj != null) {
			EntityTransaction transaction = entity.getTransaction();

			transaction.begin();
			entity.remove(obj);
			transaction.commit();
		}
		
		return obj;
	}
	public static HBUser updateHBUser(Long id, String firstname, String lastname, String CNP, String email, String address, String telephone, AuthenticationType authentication_type) {
		HBUser oldObj = entity.find(HBUser.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setAddress(address);
		oldObj.setCNP(CNP);
		oldObj.setEmail(email);
		oldObj.setFirstname(firstname);
		oldObj.setLastname(lastname);
		oldObj.setTelephone(telephone);
		oldObj.setAuth_type(authentication_type);
		
		transaction.commit();
		
		return oldObj;
	}
	public static HBUser generateNewPassword(Long id, String new_password) {
		HBUser oldObj = entity.find(HBUser.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setPassword(new_password);
				
		transaction.commit();
		
		return oldObj;
	}

}
