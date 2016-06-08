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

import cp.models.Account;
import cp.models.CPUser;
import cp.models.Commision;
import cp.models.HBUser;
import cp.models.Rate;
import cp.services.AccountService;
import cp.services.CommisionService;
import cp.services.LoginFlowService;
import cp.services.RateService;
import cp.services.UserHBService;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;


@Path("/login")
@RequestScoped
public class LoginFlowResource {

	@Inject
	private LoginFlowService loginFlowService;
	@Inject
	private UserHBService userHBService;
	@Inject
	private CommisionService commisionService;
	@Inject
	private AccountService accountService;
	@Inject
	private RateService rateService;
	@Inject
	private HttpSession httpSession;
	
	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response logIn(
			@FormParam("username") String user,
            @FormParam("password") String pass) throws Exception {
		
		Map<String, Object> response = loginFlowService.logIn(user, pass);
		
		if ((boolean) response.get("success") == true){
			CPUser logedInUser = (CPUser) response.get("user");
			
			Random rand = new Random();
			String token = Long.toString(rand.nextLong());
			
			httpSession.setAttribute("user", logedInUser);
			httpSession.setAttribute("token", token);
			
			response.put("username", logedInUser.getUsername());
			response.remove("user");
			response.put("token", token);
			
			/* Cache commisions on session*/
			Map<String, Object> commisions = commisionService.getCommisionList();
			if ((boolean) commisions.get("success") == true) {
				List<Commision> commisionList = (List<Commision>) commisions.get("commisionList");
				httpSession.setAttribute("commisionList", commisionList);
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(commisions)).build();
			}
			
			/* Cache rates on session*/
			Map<String, Object> rates = rateService.getRateList();
			if ((boolean) rates.get("success") == true) {
				List<Rate> rateList = (List<Rate>) rates.get("rateList");
				httpSession.setAttribute("rateList", rateList);
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(rates)).build();
			}
			
			/* Cache accounts on session*/
			Map<String, Object> accounts = accountService.getAccountList();
			if ((boolean) accounts.get("success") == true) {
				List<Account> accountList = (List<Account>) accounts.get("accountList");
				httpSession.setAttribute("accountList", accountList);
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(accounts)).build();
			}
			
			/* Cache hbUsers on session*/
			Map<String, Object> hbUsers = userHBService.getHBUserList();
			if ((boolean) hbUsers.get("success") == true) {
				List<HBUser> userHBList = (List<HBUser>) hbUsers.get("userHBList");
				httpSession.setAttribute("userHBList", userHBList);
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(hbUsers)).build();
			}
			
			return Response.status(200).entity(JsonUtils.mapToJson(response)).build();
		}
		else {
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Invalid Credentials. Login failed!")))
					.build();
		}
	}
}
