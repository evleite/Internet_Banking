import cp.models.Account;
import cp.models.AccountAssignement;
import cp.models.Commision;
import cp.models.ExchangeRates;
import cp.models.HBUser;
import cp.models.Rate;
import cp.models.CPUser;
import cp.models.Card;
import cp.models.CardAssignement;
import cp.utils.AccountUtils;
import cp.utils.DataBase;
import cp.utils.TrippleDes;
import cp.utils.UserUtils;
import cp.utils.enums.AccountType;
import cp.utils.enums.AuthenticationType;
import cp.utils.enums.CardType;
import cp.utils.enums.CommisionType;
import cp.utils.enums.Currencies;
import cp.utils.enums.RatesType;

public class Test {

	public static void main(String[] args) throws Exception {
		/*Commision comm1 = new Commision(CommisionType.CURRENT_ACOUNT, 5.0, "Comm for admin curr acc");
		Commision comm2 = new Commision(CommisionType.SAVING_ACCOUNT, 1.0, "Comm for admin saving acc");
		Commision comm3 = new Commision(CommisionType.CREDIT_ACCOUNT, 10.0, "Comm for admin credit acc");
		
		DataBase.persistCommision(comm1);
		DataBase.persistCommision(comm2);
		DataBase.persistCommision(comm3);
		
		Rate rate1 = new Rate(RatesType.N_A, 0.0, "False rate");
		Rate rate2 = new Rate(RatesType.SAVING_ACC_LOCAL_CURR, 0.7, "Saving acc in RON");
		Rate rate3 = new Rate(RatesType.CREDIT_CARD_LOCAL_CURR, 0.7, "Credit acc in RON");
		
		DataBase.persistRate(rate1);
		DataBase.persistRate(rate2);
		DataBase.persistRate(rate3);
		
		Account acc1 = new Account(
				"HBCP00000000000000001000", 
				AccountType.CURRENT_ACOUNT, 
				Currencies.RON, 
				comm1, 100.0);
		
		Account acc2 = new Account(
				"HBCP00000000000000001001", 
				AccountType.SAVING_ACCOUNT, 
				Currencies.RON, 
				comm2, rate2, 0.0);
		
		Account acc3 = new Account(
				"HBCP00000000000000002001", 
				AccountType.CREDIT_ACCOUNT, 
				Currencies.RON, 
				comm3, rate3, 0.0);
		
		DataBase.persistAccount(acc1);
		DataBase.persistAccount(acc2);
		DataBase.persistAccount(acc3);
		
		
		TrippleDes td = new TrippleDes();
		
		CPUser admin = new CPUser("admin", td.encrypt("admin"), "N/A", "N/A", "N/A0000000000", "domentiimaxim@yahoo.com", "N/A", "N/A");
		DataBase.persistUser(admin);
		
		HBUser client1 = new HBUser("client1", td.encrypt("client1"), "Maxim", "Domentii", "1921029000000", "maxim.domentii@test.com", "Romania, Bucuresti", "0760000000", AuthenticationType.PASSWORD);
		DataBase.persistUser(client1);
		
		HBUser client2 = new HBUser("client2", td.encrypt("client2"), "Max", "Dom", "1921029111111", "max.dom@test.com", "Moldova, Chisinau", "0761111111", AuthenticationType.PASSWORD);
		DataBase.persistUser(client2);
		
		AccountAssignement accAsing1 = new AccountAssignement(client1, acc1);
		DataBase.persistAccountAssignement(accAsing1);
		
		AccountAssignement accAsing2 = new AccountAssignement(client1, acc2);
		DataBase.persistAccountAssignement(accAsing2);
		
		AccountAssignement accAsing3 = new AccountAssignement(client1, acc3);
		DataBase.persistAccountAssignement(accAsing3);
		
		Card card1 = new Card("1111222233334444", CardType.DEBIT_CARD, "12/2020", 3000.0);
		DataBase.persistCard(card1);
		
		Card card2 = new Card("1111222233335555", CardType.CREDIT_CARD, "12/2030", 1000.0);
		DataBase.persistCard(card2);
		
		CardAssignement cradAssing1 = new CardAssignement(client1, card1, acc1);
		DataBase.persistCardAssignement(cradAssing1);
		
		CardAssignement cradAssing2 = new CardAssignement(client1, card2, acc3);
		DataBase.persistCardAssignement(cradAssing2);
		
		ExchangeRates er1 = new ExchangeRates(Currencies.EUR, 4.6, 4.4);
		DataBase.persistExchangeRate(er1);
		
		ExchangeRates er2 = new ExchangeRates(Currencies.USD, 4.1, 3.9);
		DataBase.persistExchangeRate(er2);*/
		
	}

}
