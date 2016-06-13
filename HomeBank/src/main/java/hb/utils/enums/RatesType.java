package hb.utils.enums;

public enum RatesType {
	SAVING_ACC_LOCAL_CURR,
	SAVING_ACC_EURO,
	SAVING_ACC_USD,
	CREDIT_CARD_LOCAL_CURR,
	CREDIT_CARD_EURO,
	CREDIT_CARD_USD,
	N_A;
	
	public String toString(){
        switch(this){
        case CREDIT_CARD_EURO :
            return "CREDIT_CARD_EURO";
        case CREDIT_CARD_LOCAL_CURR :
            return "CREDIT_CARD_LOCAL_CURR";
        case CREDIT_CARD_USD :
            return "CREDIT_CARD_USD";
        case N_A :
        	return "N_A";
        case SAVING_ACC_EURO :
        	return "SAVING_ACC_EURO";
        case SAVING_ACC_LOCAL_CURR :
        	return "SAVING_ACC_LOCAL_CURR";
        case SAVING_ACC_USD :
        	return "SAVING_ACC_USD";
        }
        return null;
    }
}
