package cp.utils.enums;

public enum RatesType {
	SAVING_ACC,
	CREDIT_CARD,
	N_A;
	
	public String toString(){
        switch(this){
        case CREDIT_CARD :
            return "CREDIT_CARD";
        case N_A :
        	return "N_A";
        case SAVING_ACC :
        	return "SAVING_ACC";
        }
        return null;
    }
}
