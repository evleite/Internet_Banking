package cp.utils.enums;

public enum CardType {
	CREDIT_CARD,
	DEBIT_CARD;
	
	public String toString(){
        switch(this){
        case CREDIT_CARD :
            return "CREDIT_CARD";
        case DEBIT_CARD :
            return "DEBIT_CARD";
        }
        return null;
    }
}
