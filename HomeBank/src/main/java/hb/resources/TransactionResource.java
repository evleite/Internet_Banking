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
import hb.models.HBUser;
import hb.models.Product;
import hb.models.Transaction;
import hb.services.ProductService;
import hb.services.TransactionService;
import hb.utils.JsonUtils;
import hb.utils.ResponseUtils;


@Path("/transactions")
@RequestScoped
public class TransactionResource {

	@Inject
	private TransactionService transactionService;
	@Inject
	private HttpSession httpSession;
	
	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getProductTransactions(@FormParam("token") String token,
											@FormParam("IBAN") String iban) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		List<ExchangeRates> exchangeRates = (List<ExchangeRates>) httpSession.getAttribute("exchangeRateList");
		
		response = transactionService.getProductTransactions(iban, exchangeRates);
		if ((boolean) response.get("success") == true) {
			List<Transaction> transactionList = (List<Transaction>) response.get("transactionList");

			JSONObject responseJson = new JSONObject();

			responseJson.put("success", true);
			responseJson.put("transactionList", JsonUtils.transactionListToJson(transactionList));

			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
}
