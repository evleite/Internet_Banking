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
import cp.models.CPUser;
import cp.models.HBUser;
import cp.models.Rate;
import cp.services.AccountService;
import cp.services.RateService;
import cp.services.UserCPService;
import cp.services.UserHBService;
import cp.utils.DataBase;
import cp.utils.EnumUtils;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;
import cp.utils.TrippleDes;


@Path("/usersHB")
@RequestScoped
public class UserHBResource {

	@Inject
	private UserHBService userHBService;
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
		
		if (httpSession.getAttribute("userHBList") == null) {
			response = userHBService.getHBUserList();
			if ((boolean) response.get("success") == true) {
				List<HBUser> userHBList = (List<HBUser>) response.get("userHBList");
				
				httpSession.setAttribute("userHBList", userHBList);
				
				JSONObject responseJson = new JSONObject();
				responseJson.put("success", true);
				responseJson.put("userHBList", JsonUtils.userHbListToJson(userHBList));
				responseJson.put("authenticationTypeList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getAuthenticationTypeList()));
				
				return Response.status(200).entity(responseJson).build();
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
			}
		} else {
			List<HBUser> userHBList = (List<HBUser>) httpSession.getAttribute("userHBList");
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			responseJson.put("userHBList", JsonUtils.userHbListToJson(userHBList));
			responseJson.put("authenticationTypeList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getAuthenticationTypeList()));
			
			return Response.status(200).entity(responseJson).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addUser(@FormParam("token") String token,
							     @FormParam("username") String username,
							     @FormParam("firstname") String firstname,
							     @FormParam("lastname") String lastname,
							     @FormParam("CNP") String CNP,
							     @FormParam("email") String email,
							     @FormParam("address") String address,
							     @FormParam("telephone") String telephone,
							     @FormParam("auth_type") String auth_type) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		/* Check if username already exist */
		HBUser user = DataBase.getHBUserByUserName(username);
		if (user != null){
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Username exist!", 600))).build();
		}
		/* Check if CNP already exist */
		user = DataBase.getHBUserByCNP(CNP);
		if (user != null){
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Invalid CNP!", 600))).build();
		}
		
		Map<String, Object> response = null;
		
		response = userHBService.addUser(username, firstname, lastname, CNP, email, address, telephone, auth_type);
		if ((boolean) response.get("success") == true) {
			user = (HBUser) response.get("user");
			
			/* Update user list in cache */
			List<HBUser> userHBList = (List<HBUser>) httpSession.getAttribute("userHBList");
			userHBList.add(user);
			httpSession.setAttribute("userHBList", userHBList);
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			
			TrippleDes td = new TrippleDes();
			responseJson.put("password", td.decrypt(user.getPassword()));
			
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
	public Response deleteUser(@FormParam("token") String token,
							      @FormParam("id") Long id) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = userHBService.deleteUser(id);
		if ((boolean) response.get("success") == true) {
			HBUser user = (HBUser) response.get("user");
			
			/* Update user list in cache */
			List<HBUser> userHBList = (List<HBUser>) httpSession.getAttribute("userHBList");
			userHBList.remove(user);			
			httpSession.setAttribute("userHBList", userHBList);
			
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
	public Response editUser(@FormParam("token") String token,
								@FormParam("id_user") Long id_user,
								@FormParam("firstname") String firstname,
								@FormParam("lastname") String lastname,
								@FormParam("CNP") String CNP,
								@FormParam("email") String email,
								@FormParam("address") String address,
								@FormParam("telephone") String telephone,
								@FormParam("auth_type") String auth_type) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		/* Check if CNP already exist */
		HBUser user = DataBase.getHBUserByCNP(CNP);
		if (user != null && !user.getId().equals(id_user)){
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Invalid CNP!", 600))).build();
		}
		
		Map<String, Object> response = null;
		
		HBUser oldUser = DataBase.getHBUserById(id_user);
		response = userHBService.editUser(id_user, firstname, lastname, CNP, email, address, telephone, auth_type);
		if ((boolean) response.get("success") == true) {
			user = (HBUser) response.get("user");
			
			/* Update user list in cache */
			List<HBUser> userHBList = (List<HBUser>) httpSession.getAttribute("userHBList");
			userHBList.remove(oldUser);
			userHBList.add(user);
			httpSession.setAttribute("userHBList", userHBList);
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/generateNewPassword")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response generateNewPassword(@FormParam("token") String token,
								@FormParam("id_user") Long id_user) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = userHBService.generateNewPassword(id_user);
		if ((boolean) response.get("success") == true) {
			HBUser user = (HBUser) response.get("user");
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			
			TrippleDes td = new TrippleDes();
			responseJson.put("password", td.decrypt(user.getPassword()));
			
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
}
