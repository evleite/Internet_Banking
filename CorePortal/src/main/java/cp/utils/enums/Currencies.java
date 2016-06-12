package cp.utils.enums;

public enum Currencies {
	RON,
	EUR,
	USD,
	CHF,
	GBP;
	
	public String toString(){
        switch(this){
        case RON :
            return "RON";
        case EUR :
            return "EUR";
        case USD :
            return "USD";
        case CHF :
        	return "CHF";
        case GBP :
        	return "GBP";
        }
        return null;
    }
}
