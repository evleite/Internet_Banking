package cp.utils;

import java.util.Random;

import cp.utils.enums.AccountType;
import cp.utils.enums.Currencies;

public class AccountUtils {
	
	public static String generateIBAN(AccountType type, Currencies currency){
		StringBuilder iban = new StringBuilder();
		
		iban.append("RO");
		
		switch (currency) {
		case RON:
			iban.append("01");
			break;
		case EUR:
			iban.append("02");
			break;
		case USD:
			iban.append("03");
			break;

		default:
			iban.append("00");
			break;
		}
		
		iban.append("TEST");
		
		Random rand = new Random();
		Long longRand = rand.nextLong() % 10000000000000000L;
		if (longRand < 0){
			longRand *= -1;
		}
				
		iban.append(longRand.toString());
		
		return iban.toString();
	}

}
