package cp.resources;

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

import cp.models.User;
import cp.services.LoginFlowService;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;
import cp.utils.TrippleDes;


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
		
		TrippleDes td = new TrippleDes();
		Map<String, Object> response = loginFlowService.logIn(user, pass);
		
		if ((boolean) response.get("succes") == true){
			User logedInUser = (User) response.get("user");
			
			httpSession.setAttribute("user", logedInUser);
			
			response.put("username", logedInUser.getUsername());
			response.remove("user");
			response.put("token", td.encrypt(logedInUser.getCNP()));
			
			return Response.status(200).entity(JsonUtils.mapToJson(response)).build();
		}
		else {
			//httpSession.removeAttribute("user");
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Invalid Credentials. Login failed!")))
					.build();
		}
	}
}
