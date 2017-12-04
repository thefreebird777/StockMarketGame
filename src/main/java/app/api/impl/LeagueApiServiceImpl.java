package app.api.impl;

import app.api.swagger.LeagueApiService;
import app.bo.RuntimeHandler;
import app.exceptions.APIException;
import app.models.League;
import com.google.gson.Gson;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-03T01:25:03.125Z")
public class LeagueApiServiceImpl extends LeagueApiService {
    private static final Gson GSON = new Gson();
    private static final RuntimeHandler HANDLER = new RuntimeHandler();
    
    /**
     * Fetches a specified league
     * @param leagueID - specified league's ID
     * @param securityContext
     * @return - JSON league object
     * @throws APIException 
     */
    @Override
    public Response leagueActionGet(@NotNull String leagueID, SecurityContext securityContext) throws APIException {
        try {
            League league = (League)HANDLER.select(leagueID, "League", 0);
            return Response.ok().entity(GSON.toJson(league)).build();
        } catch(APIException apiEx) {
            throw apiEx;
        } catch(Exception e) {
            throw new APIException(500, e.getMessage(), "");
        }
    }
    
    /**
     * Adds a user to a league 
     * @param leagueID - specified leagues ID
     * @param email - specified user's email
     * @param securityContext
     * @return - HTTP status
     * @throws APIException 
     */
    @Override
    public Response leagueActionPost(@NotNull String leagueID, @NotNull String email, SecurityContext securityContext) throws APIException {
        try {
            //add league user
            HANDLER.saveOrUpdate(leagueID, "League");
            return Response.ok().build();
        } catch(APIException apiEx) {
            throw apiEx;
        } catch(Exception e) {
            throw new APIException(500, e.getMessage(), "");
        }
    }
    
    /**
     * Updates a league's info
     * @param leagueID - league's ID
     * @param json - JSON league object
     * @param securityContext
     * @return - HTTP status
     * @throws APIException 
     */
    @Override
    public Response leagueActionPut(@NotNull String leagueID, String json, SecurityContext securityContext) throws APIException {
        try {
            League league = GSON.fromJson(json, League.class);
            HANDLER.saveOrUpdate(league, "League");
            return Response.ok().build();
        } catch(APIException apiEx) {
            return Response.status(apiEx.getCode()).entity(apiEx).build();
        } catch(Exception e) {
            return Response.status(500).entity(new APIException(500, e.getMessage(), "")).build();
        }
    }
    
    /**
     * Removes a user from a league
     * @param email - user's email to remove
     * @param securityContext
     * @return - HTTP status
     * @throws APIException 
     */
    @Override
    public Response leagueEmailDelete(String email, SecurityContext securityContext) throws APIException {
        try {
            //get league -> delete user -> update league
            HANDLER.saveOrUpdate(email, "League");
            return Response.ok().build();
        } catch(APIException apiEx) {
            return Response.status(apiEx.getCode()).entity(apiEx).build();
        } catch(Exception e) {
            return Response.status(500).entity(new APIException(500, e.getMessage(), "")).build();
        }
    }
    
    /**
     * Adds a new league to the DB table w/ admin
     * @param email - admin's email
     * @param json - JSON League object
     * @param securityContext
     * @return - HTTP status
     * @throws APIException 
     */
    @Override
    public Response leagueEmailPost(String email, String json, SecurityContext securityContext) throws APIException {
        try {
            //add league admin to list
            League league = GSON.fromJson(json, League.class);
            HANDLER.add(league, "League");
            return Response.ok().build();
        } catch(APIException apiEx) {
            return Response.status(apiEx.getCode()).entity(apiEx).build();
        } catch(Exception e) {
            return Response.status(500).entity(new APIException(500, e.getMessage(), "")).build();
        }
    }
}