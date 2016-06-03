package cp.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import cp.models.Account;
import cp.models.Commision;
import cp.models.Rate;

public class JsonUtils {
	
	@SuppressWarnings("unchecked")
	public static JSONObject mapToJson(Map<String, Object> map){
		JSONObject json = new JSONObject();
		for (Entry<String, Object> entry : map.entrySet()){
			json.put(entry.getKey(), entry.getValue());
		}
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> JSONArray listOfPrimitivesToJsonAray(List<T> list){
		JSONArray json = new JSONArray();
		for (T obj : list){
			json.add(obj);
		}
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject accountToJson(Account acc){
		JSONObject json = new JSONObject();
		
		json.put("acc_type", acc.getAcc_type());
		json.put("balance", acc.getBalance());
		json.put("currency", acc.getCurrency());
		json.put("IBAN", acc.getIBAN());
		json.put("id", acc.getId());
		json.put("comm", commisionToJson(acc.getId_comm_admin()));
		json.put("rate", rateToJson(acc.getId_rate()));
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject commisionToJson(Commision comm){
		JSONObject json = new JSONObject();
		
		json.put("amount", comm.getAmount());
		json.put("type", comm.getComm_type());
		json.put("details", comm.getDetails());
		json.put("id", comm.getId());
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject rateToJson(Rate rate){
		JSONObject json = new JSONObject();
		
		json.put("details", rate.getDetails());
		json.put("id", rate.getId());
		json.put("type", rate.getRate_type());
		json.put("year_percentage", rate.getYear_percentage());
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray accountListToJson(List<Account> list){
		JSONArray json = new JSONArray();
		for (Account obj : list){
			json.add(accountToJson(obj));
		}
		return json;
	}
}
