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
import hb.utils.enums.AuthenticationType;

public class DataBase {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");
	private static EntityManager entity = factory.createEntityManager();
	
	/* Account model */
	/*public static void persistAccount(Account acc){
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
	}*/
	
	
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
}
