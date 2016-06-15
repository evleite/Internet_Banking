package hb.resources;

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

import hb.models.HBUser;
import hb.services.LoginFlowService;
import hb.utils.JsonUtils;
import hb.utils.ResponseUtils;


@Path("/login")
@RequestScoped
public class LoginFlowResource {

	@Inject
	private LoginFlowService loginFlowService;
	@Inject
	private HttpSession httpSession;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response logIn(
			@FormParam("username") String user,
            @FormParam("password") String pass) throws Exception {
		
		if (httpSession.getAttribute("token") != null){
			httpSession.invalidate();
		}
		
		Map<String, Object> response = loginFlowService.logIn(user, pass);
		
		if ((boolean) response.get("success") == true){
			HBUser logedInUser = (HBUser) response.get("user");
			
			Random rand = new Random();
			String token = Long.toString(rand.nextLong());
			
			httpSession.setAttribute("user", logedInUser);
			httpSession.setAttribute("token", token);
			
			response.put("username", logedInUser.getUsername());
			response.remove("user");
			response.put("token", token);
			
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
