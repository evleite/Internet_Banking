package cp.utils;

import java.util.ArrayList;
import java.util.List;

import cp.utils.enums.AccountType;
import cp.utils.enums.CardType;
import cp.utils.enums.Currencies;

public class EnumUtils {
	
	public static List<String> getAccountTypeList(){
		List<String> accountTypeList = new ArrayList<>();
		accountTypeList.add(AccountType.CREDIT_ACCOUNT.toString());
		accountTypeList.add(AccountType.CURRENT_ACOUNT.toString());
		accountTypeList.add(AccountType.SAVING_ACCOUNT.toString());
		
		return accountTypeList;
	}
	
	public static List<String> getCurrenciesList(){
		List<String> currenciesList = new ArrayList<>();
		currenciesList.add(Currencies.EUR.toString());
		currenciesList.add(Currencies.RON.toString());
		currenciesList.add(Currencies.USD.toString());
		
		return currenciesList;
	}
	
	public static List<String> getCardTypeList(){
		List<String> cardTypeList = new ArrayList<>();
		cardTypeList.add(CardType.CREDIT_CARD.toString());
		cardTypeList.add(CardType.DEBIT_CARD.toString());
		
		return cardTypeList;
	}

}
