package cp.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cp.models.User;
import cp.services.UserService;

@Path("/login")
@RequestScoped
public class UserResource {

	@Inject
	private UserService userService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response logIn(User user) {
		boolean response = userService.logIn(user.getUsername(), user.getPassword());
		if (response){
			return Response.ok().build();
		}
		else {
			return Response.serverError().build();
		}
	}
}
