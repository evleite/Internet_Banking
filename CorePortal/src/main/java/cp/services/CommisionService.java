package cp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import cp.models.Commision;
import cp.utils.DataBase;
import cp.utils.ResponseUtils;
import cp.utils.enums.CommisionType;

@WebService
public class CommisionService {
	
	public Map<String, Object> getCommisionList() throws Exception{
		Map<String, Object> response = new HashMap<>();
		try {
			List<Commision> commisionList =	DataBase.getCommisionList();
			response.put("commisionList", commisionList);
			return ResponseUtils.respondWithSucces(response);
		} catch(Exception e) {
			return ResponseUtils.respondWithError("Can't get commisions from database.");
		}
	}
	
	public Map<String, Object> addCommision(String type, Double amount, String details) throws Exception{
		CommisionType commType = CommisionType.valueOf(type);
		Commision commision = new Commision(commType, amount, details);
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			DataBase.persistCommision(commision);
			response.put("commision", commision);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't add new commision in database.");
		}
	}
	
	public Map<String, Object> deleteCommision(Long id) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			Commision commision = DataBase.deleteCommision(id);
			response.put("commision", commision);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't delete commision from database.");
		}
	}
	
	public Map<String, Object> editCommision(Long id_commision, Double amount, String details) throws Exception{
		Map<String, Object> response = new HashMap<>();
		
		try {
			Commision commision = DataBase.updateCommision(id_commision, amount, details);
			response.put("commision", commision);
			return ResponseUtils.respondWithSucces(response);
		} catch (Exception e) {
			return ResponseUtils.respondWithError("Can't update commision from database.");
		}
	}

}
