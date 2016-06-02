package hb.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {
	
	public static Map<String, Object> respondWithError(String error){
		Map<String, Object> response = new HashMap<>();
		
		response.put("succes", false);
		response.put("error", error);
		
		return response;
	}
	
	public static Map<String, Object> respondWithSucces(Map<String, Object> model){
		model.put("succes", true);
		return model;
	}
	

}
