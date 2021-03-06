package io.swagger.api.impl;

import app.bo.RuntimeHandler;
import app.exceptions.APIException;
import com.google.gson.Gson;
import io.swagger.api.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
import org.hibernate.models.User;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-03T01:25:03.125Z")
public class UserApiServiceImpl extends UserApiService {    
    private static final Gson GSON = new Gson(); //used for JSON processing
    private static final RuntimeHandler HANDLER = new RuntimeHandler();
    
    /**
     * Removes a specified user
     * @param email - user email
     * @return HTTP response
     * @throws APIException
     */
    @Override
    public Response userEmailDelete(String email, SecurityContext securityContext) throws APIException {
        try {
            HANDLER.remove(new User(), "User", email);
            return Response.ok().build();
        } catch(APIException apiEx) {
            return Response.status(apiEx.getCode()).entity(apiEx).build();
        } catch (Exception e) {
            return Response.status(500).entity(new APIException(500, e.getMessage(), "")).build();
        }  
    }
    
    /**
     * Fetches a specified user
     * @param email - user email
     * @return HTTP response
     * @throws APIException
     */
    @Override
    public Response userEmailGet(String email, SecurityContext securityContext) throws APIException {    
        try {
            User user = (User)HANDLER.select(new User(), "User", email);
            return Response.ok().entity(user).build();
        } catch(APIException apiEx) {
            return Response.status(apiEx.getCode()).entity(apiEx).build();
        } catch (Exception e) {
            return Response.status(500).entity(new APIException(500, e.getMessage(), "")).build();
        }        
    }
    
    /**
     * Updates a specified user
     * @param email - user email
     * @param json - new user object (w/ info)
     * @return HTTP response
     * @throws APIException
     */
    @Override
    public Response userEmailPut(String email, String json, SecurityContext securityContext) throws APIException {
        try {
            User user = GSON.fromJson(json, User.class);
            HANDLER.saveOrUpdate(user, "User", user.getEmail());
            return Response.ok().build();
        } catch(APIException apiEx) {
            return Response.status(apiEx.getCode()).entity(apiEx).build();
        } catch(Exception e) {
            return Response.status(500).entity(new APIException(500, e.getMessage(), "")).build();
        } 
    }
    
    /**
     * Logs a user in and returns a JWT token
     * @param email - user email
     * @param password - user password
     * @return HTTP response
     * @throws APIException
     */
    @Override
    public Response userLoginGet( @NotNull String email,  @NotNull String password, SecurityContext securityContext) throws APIException {    
        try {
            String jwt = HANDLER.login(email, password);
            return Response.ok().entity(jwt).build();
        } catch(APIException apiEx) {
            return Response.status(apiEx.getCode()).entity(apiEx).build();
        } catch(Exception e) {
            return Response.status(500).entity(new APIException(500, e.getMessage(), "")).build();
        }      
    }
    
    /**
     * Creates a new user
     * @param json - user object (w/ info)
     * @return HTTP response
     * @throws APIException
     */
    @Override
    public Response userPost(String json, SecurityContext securityContext) throws APIException {
        try {
        	json = json.substring(json.indexOf("{"), json.lastIndexOf("}")+1);
            User newUser = GSON.fromJson(json, User.class);
            HANDLER.add(newUser, "User", newUser.getEmail());
            return Response.ok().build();
        } catch(APIException apiEx) {
            return Response.status(apiEx.getCode()).entity(apiEx).build();
        } catch(Exception e) {
            return Response.status(500).entity(new APIException(500, e.getMessage(), "")).build();
        }      
    }
}