package cp.resources;

import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import cp.models.AccountAssignement;
import cp.models.Rate;
import cp.services.AccountAssignementService;
import cp.services.RateService;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;


@Path("/accountAssignements")
@RequestScoped
public class AccountAssignementResource {

	@Inject
	private AccountAssignementService accountAssignementService;
	@Inject
	private HttpSession httpSession;
	
	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getList(@FormParam("token") String token) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		if (httpSession.getAttribute("accountAssignementList") == null) {
			response = accountAssignementService.getAccountAssignementList();
			if ((boolean) response.get("success") == true) {
				List<AccountAssignement> accountAssignementList = (List<AccountAssignement>) response.get("accountAssignementList");
				
				httpSession.setAttribute("accountAssignementList", accountAssignementList);
				
				JSONObject responseJson = new JSONObject();
				responseJson.put("success", true);
				responseJson.put("accountAssignementList", JsonUtils.accountAssignementListToJson(accountAssignementList));
				
				return Response.status(200).entity(responseJson).build();
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
			}
		} else {
			List<AccountAssignement> accountAssignementList = (List<AccountAssignement>) httpSession.getAttribute("accountAssignementList");
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			responseJson.put("accountAssignementList", JsonUtils.accountAssignementListToJson(accountAssignementList));
			
			return Response.status(200).entity(responseJson).build();
		}
	}
}
