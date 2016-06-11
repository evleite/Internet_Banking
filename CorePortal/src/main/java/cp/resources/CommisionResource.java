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
import cp.models.Commision;
import cp.services.CommisionService;
import cp.utils.DataBase;
import cp.utils.EnumUtils;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;


@Path("/commisions")
@RequestScoped
public class CommisionResource {

	@Inject
	private CommisionService commisionService;
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
		
		if (httpSession.getAttribute("commisionList") == null) {
			response = commisionService.getCommisionList();
			if ((boolean) response.get("success") == true) {
				List<Commision> commisionList = (List<Commision>) response.get("commisionList");
				
				httpSession.setAttribute("commisionList", commisionList);
				
				JSONObject responseJson = new JSONObject();
				responseJson.put("success", true);
				responseJson.put("commisionList", JsonUtils.commisionListToJson(commisionList));
				responseJson.put("commisionTypeList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getCommisionTypeList()));
				
				return Response.status(200).entity(responseJson).build();
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
			}
		} else {
			List<Commision> commisionList = (List<Commision>) httpSession.getAttribute("commisionList");
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			responseJson.put("commisionList", JsonUtils.commisionListToJson(commisionList));
			responseJson.put("commisionTypeList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getCommisionTypeList()));
			
			return Response.status(200).entity(responseJson).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addCommision(@FormParam("token") String token,
							     @FormParam("type") String type,
							     @FormParam("amount") Double amount,
							     @FormParam("details") String details) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = commisionService.addCommision(type, amount, details);
		if ((boolean) response.get("success") == true) {
			Commision commision = (Commision) response.get("commision");
			
			/* Update commision list in cache */
			List<Commision> commisionList = (List<Commision>) httpSession.getAttribute("commisionList");
			commisionList.add(commision);
			httpSession.setAttribute("commisionList", commisionList);
			
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
	public Response deleteCommision(@FormParam("token") String token,
							      @FormParam("id") Long id) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = commisionService.deleteCommision(id);
		if ((boolean) response.get("success") == true) {
			Commision commision = (Commision) response.get("commision");
			
			/* Update commision list in cache */
			List<Commision> commisionList = (List<Commision>) httpSession.getAttribute("commisionList");
			commisionList.remove(commision);			
			httpSession.setAttribute("commisionList", commisionList);
			
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
	public Response editCommision(@FormParam("token") String token,
							   @FormParam("id_commision") Long id_commision,
							   @FormParam("amount") Double amount,
							   @FormParam("details") String details) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = commisionService.editCommision(id_commision, amount, details);
		if ((boolean) response.get("success") == true) {
			Commision commision = (Commision) response.get("commision");
			Commision oldCommision = DataBase.getCommisionById(id_commision);
			
			/* Update commision list in cache */
			List<Commision> commisionList = (List<Commision>) httpSession.getAttribute("commisionList");
			commisionList.remove(oldCommision);
			commisionList.add(commision);
			httpSession.setAttribute("commisionList", commisionList);
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
}
