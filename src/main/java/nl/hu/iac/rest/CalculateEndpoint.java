package nl.hu.iac.rest;

import nl.hu.iac.service.CalculateService;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by njvan on 22-Jun-17.
 */

@Path("")
public class CalculateEndpoint {

    @Path("/test")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public int test() throws JSONException {
        return 50;
    }

    @Path("/checkNormalDistribution")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject checkNormalDistribution(String body) throws JSONException {
        JSONArray array = new JSONArray(body);
        return new JSONObject("{ result: " + CalculateService.checkIfNormalDistribution(array) + "}");
    }

    @Path("/calculateMean")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject calculateMean(String body) throws JSONException {
        JSONArray inputArray = new JSONArray(body);
        return new JSONObject("{ result: " + CalculateService.calculateMean(inputArray) + "}");
    }

    @Path("/calculateStandardDeviation")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject calculateStandardDeviation(String body) throws JSONException {
        JSONArray inputArray = new JSONArray(body);
        return new JSONObject("{ result: " + CalculateService.calculateStandardDeviation(inputArray) + "}");
    }
}
