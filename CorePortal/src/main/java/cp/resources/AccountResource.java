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

import cp.models.Account;
import cp.services.AccountService;
import cp.utils.DataBase;
import cp.utils.EnumUtils;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;


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
				responseJson.put("accountTypeList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getAccountTypeList()));
				responseJson.put("currenciesList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getCurrenciesList()));
				
				return Response.status(200).entity(responseJson).build();
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
			}
		} else {
			List<Account> accountList = (List<Account>) httpSession.getAttribute("accountList");
			
			JSONObject responseJson = new JSONObject();
			
			responseJson.put("success", true);
			responseJson.put("accountList", JsonUtils.accountListToJson(accountList));
			responseJson.put("accountTypeList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getAccountTypeList()));
			responseJson.put("currenciesList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getCurrenciesList()));
			
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
							   @FormParam("balance") Double balance,
							   @FormParam("id_comm") Long id_com,
							   @FormParam("id_rate") Long id_rate,
							   @FormParam("currency") String currency) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = accountService.addAccount(type, currency, balance, id_com, id_rate);
		if ((boolean) response.get("success") == true) {
			Account account = (Account) response.get("account");
			
			/* Update account list in cache */
			List<Account> accountList = (List<Account>) httpSession.getAttribute("accountList");
			accountList.add(account);
			httpSession.setAttribute("accountList", accountList);
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteAccount(@FormParam("token") String token,
							      @FormParam("id") Long id) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = accountService.deleteAccount(id);
		if ((boolean) response.get("success") == true) {
			Account account = (Account) response.get("account");
			
			/* Update account list in cache */
			List<Account> accountList = (List<Account>) httpSession.getAttribute("accountList");
			accountList.remove(account);			
			httpSession.setAttribute("accountList", accountList);
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/edit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response editAccount(@FormParam("token") String token,
							   @FormParam("id_account") Long id_account,
							   @FormParam("id_comm") Long id_com,
							   @FormParam("id_rate") Long id_rate,
							   @FormParam("balance") Double balance) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		Account oldAccount = DataBase.getAccountById(id_account);
		response = accountService.editAccount(id_account, id_com, id_rate, balance);
		if ((boolean) response.get("success") == true) {
			Account account = (Account) response.get("account");
			
			/* Update account list in cache */
			List<Account> accountList = (List<Account>) httpSession.getAttribute("accountList");
			accountList.remove(oldAccount);
			accountList.add(account);
			httpSession.setAttribute("accountList", accountList);
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
}
