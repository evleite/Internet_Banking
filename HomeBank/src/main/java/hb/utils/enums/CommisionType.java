package hb.utils.enums;

public enum CommisionType {
	INTERN_SAME_CURRENCY,
	INTERN_DIFF_CURRENCY,
	CURR_SAVINGS_SAME_CURRENCY,
	CURR_SAVINGS_DIFF_CURRENCY,
	SAVINGS_CURR_SAME_CURRENCY,
	SAVINGS_CURR_DIFF_CURRENCY,
	CURR_CREDIT_SAME_CURRENCY,
	CURR_CREDIT_DIFF_CURRENCY,
	CURRENT_ACOUNT,
	SAVING_ACCOUNT,
	CREDIT_ACCOUNT;
	
	public String toString(){
        switch(this){
        case CREDIT_ACCOUNT :
            return "CREDIT_ACCOUNT";
        case  CURRENT_ACOUNT:
            return "CURRENT_ACOUNT";
        case SAVING_ACCOUNT :
            return "SAVING_ACCOUNT";
        case CURR_CREDIT_DIFF_CURRENCY:
        	return "CURR_CREDIT_DIFF_CURRENCY";
        case CURR_CREDIT_SAME_CURRENCY:
        	return "CURR_CREDIT_SAME_CURRENCY";
        case CURR_SAVINGS_DIFF_CURRENCY:
        	return "CURR_SAVINGS_DIFF_CURRENCY";
        case CURR_SAVINGS_SAME_CURRENCY:
        	return "CURR_SAVINGS_SAME_CURRENCY";
        case INTERN_DIFF_CURRENCY:
        	return "INTERN_DIFF_CURRENCY";
        case INTERN_SAME_CURRENCY:
        	return "INTERN_SAME_CURRENCY";
        case SAVINGS_CURR_DIFF_CURRENCY:
        	return "SAVINGS_CURR_DIFF_CURRENCY";
        case SAVINGS_CURR_SAME_CURRENCY:
        	return "SAVINGS_CURR_SAME_CURRENCY";
        }
        return null;
    }
}
