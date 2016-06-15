package hb.utils.enums;

public enum TransactionStatus {
	WAITING,
	PROCESSED,
	CANCELED,
	REJECTED;
	
	public String toString(){
        switch(this){
        case WAITING :
            return "WAITING";
        case PROCESSED :
            return "PROCESSED";
        case CANCELED :
            return "CANCELED";
        case REJECTED :
        	return "REJECTED";
        }
        return null;
    }
}
