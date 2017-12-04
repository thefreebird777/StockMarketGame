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
    
    @Override
    public Response leagueActionGet(@NotNull String leagueID, SecurityContext securityContext) throws APIException {

    }
    
    @Override
    public Response leagueActionPost(@NotNull String leagueID, @NotNull String email, SecurityContext securityContext) throws APIException {

    }
    
    @Override
    public Response leagueActionPut(@NotNull String leagueID, String json, SecurityContext securityContext) throws APIException {
        try {
            League league = GSON.fromJson(json, League.class);
            
            //saveOrUpdate leagueDAO
            
            return Response.ok().build();
        } catch(APIException apiEx) {
            return Response.status(apiEx.getCode()).entity(apiEx).build();
        } catch(Exception e) {
            return Response.status(500).entity(new APIException(500, e.getMessage(), "")).build();
        }
    }
    
    @Override
    public Response leagueEmailDelete(String email, SecurityContext securityContext) throws APIException {
        //remove leagueDAO
    }
    
    @Override
    public Response leagueEmailPost(String email, String json, SecurityContext securityContext) throws APIException {
        //put
    }
}
