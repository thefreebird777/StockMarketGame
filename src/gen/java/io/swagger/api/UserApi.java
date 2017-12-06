package io.swagger.api;

import app.exceptions.APIException;
import io.swagger.api.factories.UserApiServiceFactory;
import io.swagger.annotations.ApiParam;
import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/user")
@io.swagger.annotations.Api(description = "the user API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-05T00:20:05.744Z")
public class UserApi  {
   private final UserApiService delegate;

   public UserApi(@Context ServletConfig servletContext) {
      UserApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("UserApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (UserApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = UserApiServiceFactory.getUserApi();
      }

      this.delegate = delegate;
   }

    @DELETE
    @Path("/{email}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete a user", notes = "", response = Response.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request - invalid email entered", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Response.class) })
    public Response userEmailDelete(@ApiParam(value = "User's email [ID]",required=true) @PathParam("email") String email
,@Context SecurityContext securityContext)
    throws APIException {
        return delegate.userEmailDelete(email,securityContext);
    }
    @GET
    @Path("/{email}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Fetches a user by email", notes = "", response = Response.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request - invalid email entered", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Response.class) })
    public Response userEmailGet(@ApiParam(value = "User's email [ID]",required=true) @PathParam("email") String email
,@Context SecurityContext securityContext)
    throws APIException {
        return delegate.userEmailGet(email,securityContext);
    }
    @PUT
    @Path("/{email}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update user", notes = "", response = Response.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request - invalid email entered", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Response.class) })
    public Response userEmailPut(@ApiParam(value = "User's email [ID]",required=true) @PathParam("email") String email
,@ApiParam(value = "JSON user object with updated info" ,required=true) String body
,@Context SecurityContext securityContext)
    throws APIException {
        return delegate.userEmailPut(email,body,securityContext);
    }
    @GET
    @Path("/login")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Logs user into the system", notes = "", response = Response.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful login", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Invalid username/password", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Response.class) })
    public Response userLoginGet(@ApiParam(value = "User's email [ID]",required=true) @QueryParam("email") String email
,@ApiParam(value = "User password",required=true) @QueryParam("password") String password
,@Context SecurityContext securityContext)
    throws APIException {
        return delegate.userLoginGet(email,password,securityContext);
    }
    @POST    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create new user", notes = "", response = Response.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Bad request", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Response.class) })
    public Response userPost(@ApiParam(value = "Object with User information" ,required=true) String body
,@Context SecurityContext securityContext)
    throws APIException {
        return delegate.userPost(body,securityContext);
    }
}
