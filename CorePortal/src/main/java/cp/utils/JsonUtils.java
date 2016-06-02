package cp.utils;

import java.util.Map;
import java.util.Map.Entry;
import org.json.simple.JSONObject;

public class JsonUtils {
	
	@SuppressWarnings("unchecked")
	public static JSONObject mapToJson(Map<String, Object> map){
		JSONObject json = new JSONObject();
		for (Entry<String, Object> entry : map.entrySet()){
			json.put(entry.getKey(), entry.getValue());
		}
		return json;
	}
}
