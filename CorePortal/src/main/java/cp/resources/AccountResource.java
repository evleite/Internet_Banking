package cp.resources;

import java.util.ArrayList;
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

import cp.models.Account;
import cp.services.AccountService;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;
import cp.utils.enums.AccountType;


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
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		if (httpSession.getAttribute("accountList") == null) {
			response = accountService.getAccountList();
			if ((boolean) response.get("success") == true) {
				List<Account> accountList = (List<Account>) response.get("accountList");
				
				httpSession.setAttribute("accountList", accountList);
				
				JSONObject responseJson = new JSONObject();
				responseJson.put("success", true);
				responseJson.put("accountList", JsonUtils.accountListToJson(accountList));
				
				List<String> accountTypeList = new ArrayList<>();
				accountTypeList.add(AccountType.CREDIT_ACCOUNT.toString());
				accountTypeList.add(AccountType.CURRENT_ACOUNT.toString());
				accountTypeList.add(AccountType.SAVING_ACCOUNT.toString());
				responseJson.put("accountTypeList", JsonUtils.listOfPrimitivesToJsonAray(accountTypeList));
				
				return Response.status(200).entity(responseJson).build();
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
			}
		} else {
			List<Account> accountList = (List<Account>) httpSession.getAttribute("accountList");
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			responseJson.put("accountList", JsonUtils.accountListToJson(accountList));
			
			List<String> accountTypeList = new ArrayList<>();
			accountTypeList.add(AccountType.CREDIT_ACCOUNT.toString());
			accountTypeList.add(AccountType.CURRENT_ACOUNT.toString());
			accountTypeList.add(AccountType.SAVING_ACCOUNT.toString());
			responseJson.put("accountTypeList", JsonUtils.listOfPrimitivesToJsonAray(accountTypeList));
			
			return Response.status(200).entity(responseJson).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addAccount(@FormParam("token") String token,
							   @FormParam("type") String type,
							   @FormParam("balance") String balance) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = accountService.addAccount(type, balance);
		if ((boolean) response.get("success") == true) {
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
				
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
}
