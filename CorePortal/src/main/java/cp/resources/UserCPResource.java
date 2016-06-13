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

import cp.models.CPUser;
import cp.services.UserCPService;
import cp.utils.DataBase;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;
import cp.utils.TrippleDes;


@Path("/usersCP")
@RequestScoped
public class UserCPResource {

	@Inject
	private UserCPService userCPService;
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
		
		if (httpSession.getAttribute("userCPList") == null) {
			response = userCPService.getCPUserList();
			if ((boolean) response.get("success") == true) {
				List<CPUser> userCPList = (List<CPUser>) response.get("userCPList");
				
				httpSession.setAttribute("userCPList", userCPList);
				
				JSONObject responseJson = new JSONObject();
				responseJson.put("success", true);
				responseJson.put("userCPList", JsonUtils.userCPListToJson(userCPList));
				
				return Response.status(200).entity(responseJson).build();
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
			}
		} else {
			List<CPUser> userCPList = (List<CPUser>) httpSession.getAttribute("userCPList");
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			responseJson.put("userCPList", JsonUtils.userCPListToJson(userCPList));
			
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
							     @FormParam("telephone") String telephone) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		/* Check if username already exist */
		CPUser user = DataBase.getUserByUserName(username);
		if (user != null){
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Username exist!", 600))).build();
		}
		/* Check if CNP already exist */
		user = DataBase.getUserByCNP(CNP);
		if (user != null){
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Invalid CNP!", 600))).build();
		}
		
		Map<String, Object> response = null;
		
		response = userCPService.addUser(username, firstname, lastname, CNP, email, address, telephone);
		if ((boolean) response.get("success") == true) {
			user = (CPUser) response.get("user");
			
			/* Update user list in cache */
			List<CPUser> userCPList = (List<CPUser>) httpSession.getAttribute("userCPList");
			userCPList.add(user);
			httpSession.setAttribute("userCPList", userCPList);
			
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
		
		/* Don't delete admin user*/
		CPUser admin = DataBase.getCPUserById(id);
		if (admin.getUsername().equals("admin")){
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Cannot delete admin user!")))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = userCPService.deleteUser(id);
		if ((boolean) response.get("success") == true) {
			CPUser user = (CPUser) response.get("user");
			
			/* Update user list in cache */
			List<CPUser> userCPList = (List<CPUser>) httpSession.getAttribute("userCPList");
			userCPList.remove(user);			
			httpSession.setAttribute("userCPList", userCPList);
			
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
								@FormParam("telephone") String telephone) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		/* Check if CNP already exist */
		CPUser user = DataBase.getUserByCNP(CNP);
		if (user != null && !user.getId().equals(id_user)){
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Invalid CNP!", 600))).build();
		}
		
		Map<String, Object> response = null;
		
		CPUser oldUser = DataBase.getCPUserById(id_user);
		response = userCPService.editUser(id_user, firstname, lastname, CNP, email, address, telephone);
		if ((boolean) response.get("success") == true) {
			user = (CPUser) response.get("user");
			
			/* Update user list in cache */
			List<CPUser> userCPList = (List<CPUser>) httpSession.getAttribute("userCPList");
			userCPList.remove(oldUser);
			userCPList.add(user);
			httpSession.setAttribute("userCPList", userCPList);
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
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
		CPUser user = DataBase.getCPUserById(id_user);
		if (!td.decrypt(user.getPassword()).equals(old_password)){
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Invalid old password!", 600))).build();
		}
		
		Map<String, Object> response = null;
		
		response = userCPService.changePassword(id_user, new_password);
		if ((boolean) response.get("success") == true) {
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
}
