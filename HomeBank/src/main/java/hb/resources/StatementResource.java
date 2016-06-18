package hb.resources;

import java.io.File;
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
import javax.ws.rs.core.Response.ResponseBuilder;

import hb.models.ExchangeRates;
import hb.models.Transaction;
import hb.services.StatementService;
import hb.services.TransactionService;
import hb.utils.JsonUtils;
import hb.utils.ResponseUtils;


@Path("/statement")
@RequestScoped
public class StatementResource {
	
	@Inject
	private TransactionService transactionService;
	@Inject
	private StatementService statementService;
	@Inject
	private HttpSession httpSession;
	
	@SuppressWarnings("unchecked")
	@POST
	@Produces("application/pdf")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response internalPayment(@FormParam("token") String token,
									@FormParam("iban") String iban) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		List<Transaction> transactionList = null;
		
		List<ExchangeRates> exchangeRates = (List<ExchangeRates>) httpSession.getAttribute("exchangeRateList");
		
		response = transactionService.getProductTransactions(iban, exchangeRates);
		if ((boolean) response.get("success") == true) {
			transactionList = (List<Transaction>) response.get("transactionList");
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
		
		response = statementService.printStatement(iban, transactionList);
		if ((boolean) response.get("success") == true) {
			File file = (File) response.get("file");
			ResponseBuilder responseB = Response.ok(file);
			responseB.header("Content-Disposition", "attachment; filename=statement.pdf");
			return responseB.build();
		} else {
			return Response.serverError().build();
		}	
	}
}
