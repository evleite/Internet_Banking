package cp.utils;

import java.util.Random;

public class CardUtils {
	public static String generateCardNo(){
		StringBuilder iban = new StringBuilder();
		
		Random rand = new Random();
		Long longRand = rand.nextLong() % 10000000000000000L;
		if (longRand < 0){
			longRand *= -1;
		}
				
		iban.append(longRand.toString());
		
		return iban.toString();
	}

}
