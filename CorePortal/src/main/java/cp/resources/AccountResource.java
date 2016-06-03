package cp.resources;

import java.util.List;
import java.util.Map;
import java.util.Random;

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

import antlr.StringUtils;
import cp.models.Account;
import cp.models.CPUser;
import cp.services.AccountService;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;
import cp.utils.TrippleDes;


@Path("/acounts")
@RequestScoped
public class AccountResource {

	@Inject
	private AccountService accountService;
	@Inject
	private HttpSession httpSession;
	
	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getList(@FormParam("token") String token) throws Exception {
		
		if (!token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!")))
					.build();
		}
		
		Map<String, Object> response = accountService.getAccountList();
		
		if ((boolean) response.get("success") == true){
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", response.get("success"));
			responseJson.put("accountList", JsonUtils.accountListToJson((List<Account>) response.get("accountList")));
			return Response.status(200).entity(responseJson).build();
		}
		else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
}
