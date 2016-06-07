package cp.utils.enums;

public enum Currencies {
	RON,
	EUR,
	USD;
	
	public String toString(){
        switch(this){
        case RON :
            return "RON";
        case EUR :
            return "EUR";
        case USD :
            return "USD";
        }
        return null;
    }
}
