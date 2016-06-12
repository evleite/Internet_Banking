package cp.utils.enums;

public enum AuthenticationType {
	PASSWORD,
	E_TOKEN,
	HARD_TOKEN;
	
	public String toString(){
        switch(this){
        case E_TOKEN :
            return "E_TOKEN";
        case HARD_TOKEN :
            return "HARD_TOKEN";
        case PASSWORD :
            return "PASSWORD";
        }
        return null;
    }
}
