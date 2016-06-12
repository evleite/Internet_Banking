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

import cp.models.Commision;
import cp.models.Rate;
import cp.services.RateService;
import cp.utils.DataBase;
import cp.utils.EnumUtils;
import cp.utils.JsonUtils;
import cp.utils.ResponseUtils;


@Path("/rates")
@RequestScoped
public class RateResource {

	@Inject
	private RateService rateService;
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
		
		if (httpSession.getAttribute("rateList") == null) {
			response = rateService.getRateList();
			if ((boolean) response.get("success") == true) {
				List<Rate> rateList = (List<Rate>) response.get("rateList");
				
				httpSession.setAttribute("rateList", rateList);
				
				JSONObject responseJson = new JSONObject();
				responseJson.put("success", true);
				responseJson.put("rateList", JsonUtils.rateListToJson(rateList));
				responseJson.put("rateTypeList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getRateTypeList()));
				
				return Response.status(200).entity(responseJson).build();
			} else {
				return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
			}
		} else {
			List<Rate> rateList = (List<Rate>) httpSession.getAttribute("rateList");
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			responseJson.put("rateList", JsonUtils.rateListToJson(rateList));
			responseJson.put("rateTypeList", JsonUtils.listOfPrimitivesToJsonAray(EnumUtils.getRateTypeList()));
			
			return Response.status(200).entity(responseJson).build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("/new")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addRate(@FormParam("token") String token,
							     @FormParam("type") String type,
							     @FormParam("year_percentage") Double year_percentage,
							     @FormParam("details") String details) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = rateService.addRate(type, year_percentage, details);
		if ((boolean) response.get("success") == true) {
			Rate rate = (Rate) response.get("rate");
			
			/* Update rate list in cache */
			List<Rate> rateList = (List<Rate>) httpSession.getAttribute("rateList");
			rateList.add(rate);
			httpSession.setAttribute("rateList", rateList);
			
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
	public Response deleteRate(@FormParam("token") String token,
							      @FormParam("id") Long id) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		response = rateService.deleteRate(id);
		if ((boolean) response.get("success") == true) {
			Rate rate = (Rate) response.get("rate");
			
			/* Update rate list in cache */
			List<Rate> rateList = (List<Rate>) httpSession.getAttribute("rateList");
			rateList.remove(rate);			
			httpSession.setAttribute("rateList", rateList);
			
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
	public Response editRate(@FormParam("token") String token,
							   @FormParam("id_rate") Long id_rate,
							   @FormParam("year_percentage") Double year_percentage,
							   @FormParam("details") String details) throws Exception {
		
		if (token == null || httpSession.getAttribute("token") == null || !token.equals(httpSession.getAttribute("token"))){
			httpSession.invalidate();
			return Response.serverError()
					.entity(JsonUtils.mapToJson(ResponseUtils.respondWithError("Integrity violation!", 666)))
					.build();
		}
		
		Map<String, Object> response = null;
		
		Rate oldRate = DataBase.getRateById(id_rate);
		response = rateService.editRate(id_rate, year_percentage, details);
		if ((boolean) response.get("success") == true) {
			Rate rate = (Rate) response.get("rate");
			
			/* Update rate list in cache */
			List<Rate> rateList = (List<Rate>) httpSession.getAttribute("rateList");
			rateList.remove(oldRate);
			rateList.add(rate);
			httpSession.setAttribute("rateList", rateList);
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("success", true);
			return Response.status(200).entity(responseJson).build();
		} else {
			return Response.serverError().entity(JsonUtils.mapToJson(response)).build();
		}
	}
}
