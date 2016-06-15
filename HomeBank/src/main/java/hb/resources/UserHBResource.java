package hb.resources;

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

import hb.utils.TrippleDes;
import hb.models.HBUser;
import hb.services.UserHBService;
import hb.utils.DataBase;
import hb.utils.JsonUtils;
import hb.utils.ResponseUtils;

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
	public Response getUser(@FormParam("token") String token) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		if (httpSession.getAttribute("user") == null) {
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		} else {
			HBUser user = (HBUser) httpSession.getAttribute("user");
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			responseJson.put("user", JsonUtils.userHbToJson(user));
			
			return Response.status(200).entity(responseJson).build();
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
								@FormParam("telephone") String telephone) throws Exception {
		
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
		
		response = userHBService.editUser(id_user, firstname, lastname, CNP, email, address, telephone);
		if ((boolean) response.get("success") == true) {
			user = (HBUser) response.get("user");
			
			/* Update user in cache */
			httpSession.setAttribute("user", user);
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			responseJson.put("user", JsonUtils.userHbToJson(user));
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/changePassword")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response changePassword(@FormParam("token") String token,
								@FormParam("id_user") Long id_user,
								@FormParam("old_password") String old_password,
								@FormParam("new_password") String new_password,
								@FormParam("confirm_password") String confirm_password) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		/* Check new and confirm pass */
		if (!new_password.equals(confirm_password)){
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("New and confirm password have to be the same!", 600))).build();
		}
		
		/* Check old pass */
		TrippleDes td = new TrippleDes();
		HBUser user = DataBase.getHBUserById(id_user);
		if (!td.decrypt(user.getPassword()).equals(old_password)){
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Invalid old password!", 600))).build();
		}
		
		Map<String, Object> response = null;
		
		response = userHBService.changePassword(id_user, new_password);
		if ((boolean) response.get("success") == true) {
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
}
