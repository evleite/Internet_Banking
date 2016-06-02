package cp.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cp.models.Account;
import cp.models.AccountAssignement;
import cp.models.Card;
import cp.models.CardAssignement;
import cp.models.Commision;
import cp.models.ExchangeRates;
import cp.models.Rate;
import cp.models.Token;
import cp.models.TokenAssignement;
import cp.models.CPUser;
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
	public static List<Account> getAccountsFromDB() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createQuery("from accounts");
		List qList = queryResult.getResultList();
		
		transaction.commit();
		
		List<Account> result = new ArrayList<Account>();
		for (int i = 0; i < qList.size(); i++) {
			result.add((Account) qList.get(i));
		}
		
		return result;
	}
	public static void updateAccount(Long id, Account newObj) {
		Account oldObj = entity.find(Account.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setAcc_type(newObj.getAcc_type());
		oldObj.setCurrency(newObj.getCurrency());
		oldObj.setIBAN(newObj.getIBAN());
		oldObj.setId_comm_admin(newObj.getId_comm_admin());
		oldObj.setId_rate(newObj.getId_rate());
		oldObj.setBalance(newObj.getBalance());
		
		transaction.commit();
	}
	public static void deleteAccount(Long id){
		Account obj = entity.find(Account.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}
	
	/* AccountAssignement model */
	public static void persistAccountAssignement(AccountAssignement accAsig){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(accAsig);

		transaction.commit();
	}
	public static List<AccountAssignement> getAccountAssignementsFromDB() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createQuery("from acc_assignements");
		List qList = queryResult.getResultList();
		
		transaction.commit();
		
		List<AccountAssignement> result = new ArrayList<AccountAssignement>();
		for (int i = 0; i < qList.size(); i++) {
			result.add((AccountAssignement) qList.get(i));
		}
		
		return result;
	}
	/*public static void updateAccountAssignement(Long id, AccountAssignement newObj) {
		AccountAssignement oldObj = entity.find(AccountAssignement.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setAccount(newObj.getAccount());
		oldObj.setUser(newObj.getUser());
		
		transaction.commit();
	}*/
	public static void deleteAccountAssignement(Long id){
		AccountAssignement obj = entity.find(AccountAssignement.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}
	
	/* Card model */
	public static void persistCard(Card card){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(card);

		transaction.commit();
	}
	public static List<Card> getCardsFromDB() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createQuery("from cards");
		List qList = queryResult.getResultList();
		
		transaction.commit();
		
		List<Card> result = new ArrayList<Card>();
		for (int i = 0; i < qList.size(); i++) {
			result.add((Card) qList.get(i));
		}
		
		return result;
	}
	public static void updateCard(Long id, Card newObj) {
		Card oldObj = entity.find(Card.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setCard_no(newObj.getCard_no());
		oldObj.setCard_type(newObj.getCard_type());
		oldObj.setId_rate(newObj.getId_rate());
		oldObj.setValidity(newObj.getValidity());
		oldObj.setDaily_limit(newObj.getDaily_limit());
		
		transaction.commit();
	}
	public static void deleteCard(Long id){
		Card obj = entity.find(Card.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}
	
	/* CardAssignement model */
	public static void persistCardAssignement(CardAssignement cardAsig){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(cardAsig);

		transaction.commit();
	}
	public static List<CardAssignement> getCardAssignementsFromDB() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createQuery("from card_assignements");
		List qList = queryResult.getResultList();
		
		transaction.commit();
		
		List<CardAssignement> result = new ArrayList<CardAssignement>();
		for (int i = 0; i < qList.size(); i++) {
			result.add((CardAssignement) qList.get(i));
		}
		
		return result;
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
	public static void deleteCardAssignement(Long id){
		CardAssignement obj = entity.find(CardAssignement.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}
	
	/* Commision model*/
	public static void persistCommision(Commision comm){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(comm);

		transaction.commit();
	}
	public static List<Commision> getCommisionsFromDB() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createQuery("from commisions");
		List qList = queryResult.getResultList();
		
		transaction.commit();
		
		List<Commision> result = new ArrayList<Commision>();
		for (int i = 0; i < qList.size(); i++) {
			result.add((Commision) qList.get(i));
		}
		
		return result;
	}
	public static void updateCommision(Long id, Commision newObj) {
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
	}
	
	/* ExchangeRates model */
	public static void persistExchangeRate(ExchangeRates exgRate){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(exgRate);

		transaction.commit();
	}
	public static List<ExchangeRates> getExchangeRatesFromDB() {
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
	}
	
	/* Rate model */
	public static void persistRate(Rate rate){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(rate);

		transaction.commit();
	}
	public static List<Rate> getRatesFromDB() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createQuery("from rates");
		List qList = queryResult.getResultList();
		
		transaction.commit();
		
		List<Rate> result = new ArrayList<Rate>();
		for (int i = 0; i < qList.size(); i++) {
			result.add((Rate) qList.get(i));
		}
		
		return result;
	}
	public static void updateRate(Long id, Rate newObj) {
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
	}
	
	/* Token model */
	public static void persistToken(Token token){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(token);

		transaction.commit();
	}
	public static List<Token> getTokensFromDB() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createQuery("from tokens");
		List qList = queryResult.getResultList();
		
		transaction.commit();
		
		List<Token> result = new ArrayList<Token>();
		for (int i = 0; i < qList.size(); i++) {
			result.add((Token) qList.get(i));
		}
		
		return result;
	}
	/*public static void updateToken(Long id, Token newObj) {
		Token oldObj = entity.find(Token.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setSerial(newObj.getSerial());
		oldObj.setToken_type(newObj.getToken_type());
		
		transaction.commit();
	}*/
	public static void deleteToken(Long id){
		Token obj = entity.find(Token.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}
	
	/* TokenAssignement model */
	public static void persistTokenAssignement(TokenAssignement tokenAsig){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(tokenAsig);

		transaction.commit();
	}
	public static List<TokenAssignement> getTokenAssignementsFromDB() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createQuery("from token_assignements");
		List qList = queryResult.getResultList();
		
		transaction.commit();
		
		List<TokenAssignement> result = new ArrayList<TokenAssignement>();
		for (int i = 0; i < qList.size(); i++) {
			result.add((TokenAssignement) qList.get(i));
		}
		
		return result;
	}
	/*public static void updateTokenAssignement(Long id, TokenAssignement newObj) {
		TokenAssignement oldObj = entity.find(TokenAssignement.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		
		oldObj.setToken(newObj.getToken());
		oldObj.setUser(newObj.getUser());
		
		transaction.commit();
	}*/
	public static void deleteTokenAssignement(Long id){
		TokenAssignement obj = entity.find(TokenAssignement.class, id);
		EntityTransaction transaction = entity.getTransaction();
		
		transaction.begin();
		entity.remove(obj);
		transaction.commit();
	}
	
	/* Transaction model */
	public static void proccesTransactions() {
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
	}
	
	/* User model */
	public static void persistUser(CPUser user){
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();

		entity.persist(user);

		transaction.commit();
	}
	public static List<CPUser> getUsersFromDB() {
		EntityTransaction transaction = entity.getTransaction();
		transaction.begin();
		
		Query queryResult = entity.createQuery("from users_cp");
		List qList = queryResult.getResultList();
		
		transaction.commit();
		
		List<CPUser> result = new ArrayList<CPUser>();
		for (int i = 0; i < qList.size(); i++) {
			result.add((CPUser) qList.get(i));
		}
		
		return result;
	}
	public static void updateUser(Long id, CPUser newObj) {
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

}
