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

import hb.models.ExchangeRates;
import hb.services.ExchangeRateService;
import hb.utils.EnumUtils;
import hb.utils.JsonUtils;
import hb.utils.ResponseUtils;


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
}
