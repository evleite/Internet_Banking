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

import cp.models.ExchangeRates;
import cp.services.ExchangeRateService;
import cp.utils.DataBase;
import cp.utils.EnumUtils;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;


@Path("/exchangeRates")
@RequestScoped
public class ExchangeRateResource {

	@Inject
	private ExchangeRateService exchangeRateService;
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
		
		if (httpSession.getAttribute("exchangeRateList") == null) {
			response = exchangeRateService.getExchangeRateList();
			if ((boolean) response.get("success") == true) {
				List<ExchangeRates> exchangeRateList = (List<ExchangeRates>) response.get("exchangeRateList");
				
				httpSession.setAttribute("exchangeRateList", exchangeRateList);
				
				JSONObject responseJson = new JSONObject();
				responseJson.put("success", true);
				responseJson.put("exchangeRateList", JsonUtils.exchangeRateListToJson(exchangeRateList));
				responseJson.put("currenciesList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getCurrenciesList()));
				
				return Response.status(200).entity(responseJson).build();
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
			}
		} else {
			List<ExchangeRates> exchangeRateList = (List<ExchangeRates>) httpSession.getAttribute("exchangeRateList");
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			responseJson.put("exchangeRateList", JsonUtils.exchangeRateListToJson(exchangeRateList));
			responseJson.put("currenciesList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getCurrenciesList()));
			
			return Response.status(200).entity(responseJson).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addExchangeRate(@FormParam("token") String token,
							     @FormParam("currency") String currency,
							     @FormParam("buy") Double buy,
							     @FormParam("sell") Double sell) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = exchangeRateService.addExchangeRate(currency, buy, sell);
		if ((boolean) response.get("success") == true) {
			ExchangeRates exchangeRate = (ExchangeRates) response.get("exchangeRate");
			
			/* Update exchangeRate list in cache */
			List<ExchangeRates> exchangeRateList = (List<ExchangeRates>) httpSession.getAttribute("exchangeRateList");
			exchangeRateList.add(exchangeRate);
			httpSession.setAttribute("exchangeRateList", exchangeRateList);
			
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
	public Response deleteExchangeRate(@FormParam("token") String token,
							      @FormParam("id") Long id) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = exchangeRateService.deleteExchangeRate(id);
		if ((boolean) response.get("success") == true) {
			ExchangeRates exchangeRate = (ExchangeRates) response.get("exchangeRate");
			
			/* Update exchangeRate list in cache */
			List<ExchangeRates> exchangeRateList = (List<ExchangeRates>) httpSession.getAttribute("exchangeRateList");
			exchangeRateList.remove(exchangeRate);			
			httpSession.setAttribute("exchangeRateList", exchangeRateList);
			
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
	public Response editExchangeRate(@FormParam("token") String token,
							   @FormParam("id_exchangeRate") Long id_exchangeRate,
							   @FormParam("buy") Double buy,
							   @FormParam("sell") Double sell) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		ExchangeRates oldExchangeRate = DataBase.getExchangeRateById(id_exchangeRate);
		response = exchangeRateService.editExchangeRate(id_exchangeRate, buy, sell);
		if ((boolean) response.get("success") == true) {
			ExchangeRates exchangeRate = (ExchangeRates) response.get("exchangeRate");
			
			/* Update exchangeRate list in cache */
			List<ExchangeRates> exchangeRateList = (List<ExchangeRates>) httpSession.getAttribute("exchangeRateList");
			exchangeRateList.remove(oldExchangeRate);
			exchangeRateList.add(exchangeRate);
			httpSession.setAttribute("exchangeRateList", exchangeRateList);
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
}
