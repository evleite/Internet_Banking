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

import hb.models.HBUser;
import hb.models.Product;
import hb.services.ProductService;
import hb.utils.JsonUtils;
import hb.utils.ResponseUtils;


@Path("/products")
@RequestScoped
public class ProductResource {

	@Inject
	private ProductService productService;
	@Inject
	private HttpSession httpSession;
	
	@SuppressWarnings("unchecked")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getAllProducts(@FormParam("token") String token) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		if (httpSession.getAttribute("productList") == null) {
			HBUser user = (HBUser) httpSession.getAttribute("user");
			response = productService.getAllProducts(user);
			if ((boolean) response.get("success") == true) {
				List<Product> productList = (List<Product>) response.get("productList");
				
				httpSession.setAttribute("productList", productList);
				
				JSONObject responseJson = new JSONObject();
				
				responseJson.put("success", true);
				responseJson.put("productList", JsonUtils.productListToJson(productList));
				
				return Response.status(200).entity(responseJson).build();
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
			}
		} else {
			List<Product> productList = (List<Product>) httpSession.getAttribute("productList");
			
			JSONObject responseJson = new JSONObject();
			
			responseJson.put("success", true);
			responseJson.put("productList", JsonUtils.productListToJson(productList));
			
			return Response.status(200).entity(responseJson).build();
		}
	}
}
