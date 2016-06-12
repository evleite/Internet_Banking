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
import cp.models.Card;
import cp.services.CardService;
import cp.utils.DataBase;
import cp.utils.EnumUtils;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;


@Path("/cards")
@RequestScoped
public class CardResource {

	@Inject
	private CardService cardService;
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
		
		if (httpSession.getAttribute("cardList") == null) {
			response = cardService.getCardList();
			if ((boolean) response.get("success") == true) {
				List<Card> cardList = (List<Card>) response.get("cardList");
				
				httpSession.setAttribute("cardList", cardList);
				
				JSONObject responseJson = new JSONObject();
				responseJson.put("success", true);
				responseJson.put("cardList", JsonUtils.cardListToJson(cardList));
				responseJson.put("cardTypeList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getCardTypeList()));
				
				return Response.status(200).entity(responseJson).build();
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
			}
		} else {
			List<Card> cardList = (List<Card>) httpSession.getAttribute("cardList");
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			responseJson.put("cardList", JsonUtils.cardListToJson(cardList));
			responseJson.put("cardTypeList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getCardTypeList()));
			
			return Response.status(200).entity(responseJson).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addCard(@FormParam("token") String token,
							@FormParam("type") String type,
							@FormParam("daily_limit") Double daily_limit,
							@FormParam("validity") String validity) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = cardService.addCard(type, daily_limit, validity);
		if ((boolean) response.get("success") == true) {
			Card card = (Card) response.get("card");
			
			/* Update cards list in cache */
			List<Card> cardList = (List<Card>) httpSession.getAttribute("cardList");
			cardList.add(card);
			httpSession.setAttribute("cardList", cardList);
			
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
		
		response = cardService.deleteCard(id);
		if ((boolean) response.get("success") == true) {
			Card card = (Card) response.get("card");
			
			/* Update account list in cache */
			List<Card> cardList = (List<Card>) httpSession.getAttribute("cardList");
			cardList.remove(card);			
			httpSession.setAttribute("cardList", cardList);
			
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
							   @FormParam("id_card") Long id_card,
							   @FormParam("daily_limit") Double daily_limit,
							   @FormParam("validity") String validity) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		Card oldCard = DataBase.getCardById(id_card);
		response = cardService.editCard(id_card, daily_limit, validity);
		if ((boolean) response.get("success") == true) {
			Card card = (Card) response.get("card");
			
			/* Update cards list in cache */
			List<Card> cardList = (List<Card>) httpSession.getAttribute("cardList");
			cardList.remove(oldCard);
			cardList.add(card);
			httpSession.setAttribute("cardList", cardList);
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
}
