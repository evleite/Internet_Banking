package cp.utils.enums;

public enum AccountType {
	CURRENT_ACOUNT,
	SAVING_ACCOUNT,
	CREDIT_ACCOUNT;
	
	public String toString(){
        switch(this){
        case CURRENT_ACOUNT :
            return "CURRENT_ACOUNT";
        case SAVING_ACCOUNT :
            return "SAVING_ACCOUNT";
        case CREDIT_ACCOUNT :
            return "CREDIT_ACCOUNT";
        }
        return null;
    }
	
}
