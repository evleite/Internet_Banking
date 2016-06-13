package hb.utils;

import java.util.ArrayList;
import java.util.List;

import hb.utils.enums.AccountType;
import hb.utils.enums.AuthenticationType;
import hb.utils.enums.CardType;
import hb.utils.enums.CommisionType;
import hb.utils.enums.Currencies;
import hb.utils.enums.RatesType;

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
		currenciesList.add(Currencies.CHF.toString());
		currenciesList.add(Currencies.GBP.toString());
		
		return currenciesList;
	}
	
	public static List<String> getCardTypeList(){
		List<String> cardTypeList = new ArrayList<>();
		cardTypeList.add(CardType.CREDIT_CARD.toString());
		cardTypeList.add(CardType.DEBIT_CARD.toString());
		
		return cardTypeList;
	}
	
	public static List<String> getCommisionTypeList(){
		List<String> commisionTypeList = new ArrayList<>();
		commisionTypeList.add(CommisionType.CREDIT_ACCOUNT.toString());
		commisionTypeList.add(CommisionType.CURR_CREDIT_DIFF_CURRENCY.toString());
		commisionTypeList.add(CommisionType.CURR_CREDIT_SAME_CURRENCY.toString());
		commisionTypeList.add(CommisionType.CURR_SAVINGS_DIFF_CURRENCY.toString());
		commisionTypeList.add(CommisionType.CURR_SAVINGS_SAME_CURRENCY.toString());
		commisionTypeList.add(CommisionType.CURRENT_ACOUNT.toString());
		commisionTypeList.add(CommisionType.INTERN_DIFF_CURRENCY.toString());
		commisionTypeList.add(CommisionType.INTERN_SAME_CURRENCY.toString());
		commisionTypeList.add(CommisionType.SAVING_ACCOUNT.toString());
		commisionTypeList.add(CommisionType.SAVINGS_CURR_DIFF_CURRENCY.toString());
		commisionTypeList.add(CommisionType.SAVINGS_CURR_SAME_CURRENCY.toString());
		
		return commisionTypeList;
	}

	public static List<String> getRateTypeList(){
		List<String> rateTypeList = new ArrayList<>();
		rateTypeList.add(RatesType.CREDIT_CARD_EURO.toString());
		rateTypeList.add(RatesType.CREDIT_CARD_LOCAL_CURR.toString());
		rateTypeList.add(RatesType.CREDIT_CARD_USD.toString());
		rateTypeList.add(RatesType.N_A.toString());
		rateTypeList.add(RatesType.SAVING_ACC_EURO.toString());
		rateTypeList.add(RatesType.SAVING_ACC_LOCAL_CURR.toString());
		rateTypeList.add(RatesType.SAVING_ACC_USD.toString());
		
		return rateTypeList;
	}
	
	public static List<String> getAuthenticationTypeList(){
		List<String> authenticationTypeList = new ArrayList<>();
		authenticationTypeList.add(AuthenticationType.E_TOKEN.toString());
		authenticationTypeList.add(AuthenticationType.HARD_TOKEN.toString());
		authenticationTypeList.add(AuthenticationType.PASSWORD.toString());
		
		return authenticationTypeList;
	}
}
