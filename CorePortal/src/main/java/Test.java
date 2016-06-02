import cp.models.Account;
import cp.models.Commision;
import cp.models.Rate;
import cp.models.User;
import cp.utils.DataBase;
import cp.utils.TrippleDes;
import cp.utils.enums.AccountType;
import cp.utils.enums.CommisionType;
import cp.utils.enums.Currencies;
import cp.utils.enums.RatesType;

public class Test {

	public static void main(String[] args) throws Exception {
		/*// TODO Auto-generated method stub
		Commision comm1 = new Commision(CommisionType.CURRENT_ACOUNT, 5.0, "Comm for admin curr acc");
		Commision comm2 = new Commision(CommisionType.SAVING_ACCOUNT, 1.0, "Comm for admin saving acc");
		
		DataBase.persistCommision(comm1);
		DataBase.persistCommision(comm2);
		
		Rate rate1 = new Rate(RatesType.N_A, 0.0);
		Rate rate2 = new Rate(RatesType.SAVING_ACC_LOCAL_CURR, 0.7, "Saving acc in RON");
		
		DataBase.persistRate(rate1);
		DataBase.persistRate(rate2);
		
		Account acc1 = new Account(
				"HBCP00000000000000001000", 
				AccountType.CURRENT_ACOUNT, 
				Currencies.RON, 
				comm1, rate1, 100.0);
		
		Account acc2 = new Account(
				"HBCP00000000000000001001", 
				AccountType.SAVING_ACCOUNT, 
				Currencies.RON, 
				comm2, rate2, 0.0);
		
		DataBase.persistAccount(acc1);
		DataBase.persistAccount(acc2);*/
		
		
		/*TrippleDes td = new TrippleDes();
		
		User admin = new User("admin", td.encrypt("admin"), "N/A", "N/A", "N/A0000000000", "domentiimaxim@yahoo.com", "N/A", "N/A");
		DataBase.persistUser(admin);*/
		
		/*System.out.println(DataBase.getUserByUserName("admin").getPassword());*/
		
		
		
	}

}
