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

import cp.models.AccountAssignement;
import cp.models.CardAssignement;
import cp.services.CardAssignementService;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;


@Path("/cardAssignements")
@RequestScoped
public class CardAssignementResource {

	@Inject
	private CardAssignementService cardAssignementService;
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
		
		if (httpSession.getAttribute("cardAssignementList") == null) {
			response = cardAssignementService.getCardAssignementList();
			if ((boolean) response.get("success") == true) {
				List<CardAssignement> cardAssignementList = (List<CardAssignement>) response.get("cardAssignementList");
				
				httpSession.setAttribute("cardAssignementList", cardAssignementList);
				
				JSONObject responseJson = new JSONObject();
				responseJson.put("success", true);
				responseJson.put("cardAssignementList", JsonUtils.cardAssignementListToJson(cardAssignementList));
				
				return Response.status(200).entity(responseJson).build();
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
			}
		} else {
			List<CardAssignement> cardAssignementList = (List<CardAssignement>) httpSession.getAttribute("cardAssignementList");
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			responseJson.put("cardAssignementList", JsonUtils.cardAssignementListToJson(cardAssignementList));
			
			return Response.status(200).entity(responseJson).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addCardAssignement(@FormParam("token") String token,
							   			  @FormParam("id_account") Long id_account,
							   			  @FormParam("id_user") Long id_user,
							   			  @FormParam("id_card") Long id_card) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = cardAssignementService.addCardAssignement(id_account, id_user, id_card);
		if ((boolean) response.get("success") == true) {
			CardAssignement cardAssignement = (CardAssignement) response.get("cardAssignement");
			
			/* Update accountAssignement list in cache */
			List<CardAssignement> cardAssignementList = (List<CardAssignement>) httpSession.getAttribute("cardAssignementList");
			cardAssignementList.add(cardAssignement);
			httpSession.setAttribute("cardAssignementList", cardAssignementList);
			
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
	public Response deleteAccountAssignement(@FormParam("token") String token,
							      			 @FormParam("id") Long id) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = cardAssignementService.deleteCardAssignement(id);
		if ((boolean) response.get("success") == true) {
			CardAssignement cardAssignement = (CardAssignement) response.get("cardAssignement");
			
			/* Update account list in cache */
			List<CardAssignement> cardAssignementList = (List<CardAssignement>) httpSession.getAttribute("cardAssignementList");
			cardAssignementList.remove(cardAssignement);			
			httpSession.setAttribute("cardAssignementList", cardAssignementList);
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
}
