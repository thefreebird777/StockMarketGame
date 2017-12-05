package io.swagger.api;

import app.api.factories.LeagueApiServiceFactory;
import app.api.swagger.LeagueApiService;
import app.exceptions.APIException;
import io.swagger.model.*;
import io.swagger.annotations.ApiParam;
import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/league")
@io.swagger.annotations.Api(description = "the league API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-05T00:20:05.744Z")
public class LeagueApi  {
   private final LeagueApiService delegate;

   public LeagueApi(@Context ServletConfig servletContext) {
      LeagueApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("LeagueApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (LeagueApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = LeagueApiServiceFactory.getLeagueApi();
      }

      this.delegate = delegate;
   }

    @DELETE
    @Path("/action")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "League admin deletes league", notes = "", response = Response.class, tags={ "league", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request - invalid email entered", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Response.class) })
    public Response leagueActionDelete(@ApiParam(value = "Specified league ID",required=true) @QueryParam("leagueID") String leagueID
,@ApiParam(value = "Specified user email",required=true) @QueryParam("email") String email
,@Context SecurityContext securityContext)
    throws APIException {
        return delegate.leagueActionDelete(leagueID,email,securityContext);
    }
    @GET
    @Path("/action")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Fetch league by league ID for sreen rendering", notes = "", response = Response.class, tags={ "league", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request - invalid league ID entered", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Response.class) })
    public Response leagueActionGet(@ApiParam(value = "Specified league ID",required=true) @QueryParam("leagueID") String leagueID
,@Context SecurityContext securityContext)
    throws APIException {
        return delegate.leagueActionGet(leagueID,securityContext);
    }
    @POST
    @Path("/action")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Adds a user to a given league", notes = "", response = Response.class, tags={ "league", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request - invalid query param entered", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "League and/or user not found", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Response.class) })
    public Response leagueActionPost(@ApiParam(value = "Specified league ID",required=true) @QueryParam("leagueID") String leagueID
,@ApiParam(value = "Specified user email",required=true) @QueryParam("email") String email
,@Context SecurityContext securityContext)
    throws APIException {
        return delegate.leagueActionPost(leagueID,email,securityContext);
    }
    @PUT
    @Path("/action")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update league info", notes = "", response = Response.class, tags={ "league", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request - invalid query params entered", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "League not found", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Response.class) })
    public Response leagueActionPut(@ApiParam(value = "League's ID",required=true) @QueryParam("leagueID") String leagueID
,@ApiParam(value = "JSON league object with updated info (i.e. name)" ,required=true) String body
,@Context SecurityContext securityContext)
    throws APIException {
        return delegate.leagueActionPut(leagueID,body,securityContext);
    }
    @POST
    @Path("/{email}")
    @Consumes({ "appliction/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creates a new league and assigns user as admin", notes = "", response = Response.class, tags={ "league", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request - invalid email entered", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Response.class) })
    public Response leagueEmailPost(@ApiParam(value = "User's email [ID]",required=true) @PathParam("email") String email
,@ApiParam(value = "order placed for purchasing the pet" ,required=true) String body
,@Context SecurityContext securityContext)
    throws APIException {
        return delegate.leagueEmailPost(email,body,securityContext);
    }
}
