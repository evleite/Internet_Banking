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
	/*public static void updateCardAssignement(Long id, CardAssignement newObj) {
		CardAssignement oldObj = entity.find(CardAssignement.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setAccount(newObj.getAccount());
		oldObj.setCard(newObj.getCard());
		oldObj.setUser(newObj.getUser());
		
		transaction.commit();
	}*/
	/*public static void deleteCardAssignement(Long id){
		CardAssignement obj = entity.find(CardAssignement.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}*/
	
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
	/*public static void updateCommision(Long id, Commision newObj) {
		Commision oldObj = entity.find(Commision.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setAmount(newObj.getAmount());
		oldObj.setComm_type(newObj.getComm_type());
		oldObj.setDetails(newObj.getDetails());
		
		transaction.commit();
	}
	public static void deleteCommision(Long id){
		Commision obj = entity.find(Commision.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}*/
	
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
	/*public static List<ExchangeRates> getExchangeRatesFromDB() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createQuery("from exchange_rates");
		List qList = queryResult.getResultList();
		
		transaction.commit();
		
		List<ExchangeRates> result = new ArrayList<ExchangeRates>();
		for (int i = 0; i < qList.size(); i++) {
			result.add((ExchangeRates) qList.get(i));
		}
		
		return result;
	}
	public static void updateExchangeRate(Long id, ExchangeRates newObj) {
		ExchangeRates oldObj = entity.find(ExchangeRates.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setBuy(newObj.getBuy());
		oldObj.setSell(newObj.getSell());
		oldObj.setCurrency(newObj.getCurrency());
		
		transaction.commit();
	}
	public static void deleteExchangeRate(Long id){
		ExchangeRates obj = entity.find(ExchangeRates.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}*/
	
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
	/*public static Rate getFalseRate() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from rates where rate_type= :rate_type", Rate.class)
				.setParameter("rate_type", 6);
		@SuppressWarnings("unchecked")
		List<Rate> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList.get(0);
		} else {
			return null;
		}
	}
	*/public static Rate getRateById(Long id) {
		Rate obj = entity.find(Rate.class, id);
		return obj;
	}
	/*public static void updateRate(Long id, Rate newObj) {
		Rate oldObj = entity.find(Rate.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setDetails(newObj.getDetails());
		oldObj.setRate_type(newObj.getRate_type());
		oldObj.setYear_percentage(newObj.getYear_percentage());
		
		transaction.commit();
	}
	public static void deleteRate(Long id){
		Rate obj = entity.find(Rate.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}*/
	
	/* Token model */
	/*public static void persistToken(Token token){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(token);

		transaction.commit();
	}
	public static List<Token> getTokenList() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from tokens", Token.class);
		@SuppressWarnings("unchecked")
		List<Token> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return null;
		}
	}*/
	/*public static void updateToken(Long id, Token newObj) {
		Token oldObj = entity.find(Token.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setSerial(newObj.getSerial());
		oldObj.setToken_type(newObj.getToken_type());
		
		transaction.commit();
	}*/
	/*public static void deleteToken(Long id){
		Token obj = entity.find(Token.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}*/
	
	/* TokenAssignement model */
	/*public static void persistTokenAssignement(TokenAssignement tokenAsig){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(tokenAsig);

		transaction.commit();
	}
	public static List<TokenAssignement> getTokenAssignementList() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createNativeQuery("select * from tokens_assignements", TokenAssignement.class);
		@SuppressWarnings("unchecked")
		List<TokenAssignement> qList = queryResult.getResultList();
		transaction.commit();
		
		if (qList.size() > 0){
			return qList;
		} else {
			return null;
		}
	}*/
	/*public static void updateTokenAssignement(Long id, TokenAssignement newObj) {
		TokenAssignement oldObj = entity.find(TokenAssignement.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setToken(newObj.getToken());
		oldObj.setUser(newObj.getUser());
		
		transaction.commit();
	}*/
	/*public static void deleteTokenAssignement(Long id){
		TokenAssignement obj = entity.find(TokenAssignement.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}*/
	
	/* Transaction model */
	/*public static void proccesTransactions() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createQuery("from transactions");
		List qList = queryResult.getResultList();
		
		transaction.commit();
		
		List<Transaction> result = new ArrayList<Transaction>();
		for (int i = 0; i < qList.size(); i++) {
			result.add((Transaction) qList.get(i));
		}
		
		for (Transaction trans : result){
			Transaction obj = entity.find(Transaction.class, trans.getId());
			transaction.begin();
			
			obj.setStatus(TransactionStatus.PROCESSED);
			
			transaction.commit();
		}
	}*/
	
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
	/*public static void updateUser(Long id, CPUser newObj) {
		CPUser oldObj = entity.find(CPUser.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setAddress(newObj.getAddress());
		oldObj.setCNP(newObj.getCNP());
		oldObj.setEmail(newObj.getEmail());
		oldObj.setFirstname(newObj.getFirstname());
		oldObj.setLastname(newObj.getLastname());
		oldObj.setPassword(newObj.getPassword());
		oldObj.setTelephone(newObj.getTelephone());
		oldObj.setUsername(newObj.getUsername());
		
		transaction.commit();
	}
	public static void deleteUser(Long id){
		CPUser obj = entity.find(CPUser.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}*/
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
	/*public static void updateUser(Long id, CPUser newObj) {
		CPUser oldObj = entity.find(CPUser.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setAddress(newObj.getAddress());
		oldObj.setCNP(newObj.getCNP());
		oldObj.setEmail(newObj.getEmail());
		oldObj.setFirstname(newObj.getFirstname());
		oldObj.setLastname(newObj.getLastname());
		oldObj.setPassword(newObj.getPassword());
		oldObj.setTelephone(newObj.getTelephone());
		oldObj.setUsername(newObj.getUsername());
		
		transaction.commit();
	}
	public static void deleteUser(Long id){
		CPUser obj = entity.find(CPUser.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}*/
	/*public static HBUser getUserByUserName(String username) {
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
	}*/

}
