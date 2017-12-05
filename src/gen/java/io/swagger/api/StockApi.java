package io.swagger.api;

import app.exceptions.APIException;
import io.swagger.api.factories.StockApiServiceFactory;
import io.swagger.annotations.ApiParam;
import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/stock")
@io.swagger.annotations.Api(description = "the stock API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-05T00:20:05.744Z")
public class StockApi  {
   private final StockApiService delegate;

   public StockApi(@Context ServletConfig servletContext) {
      StockApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("StockApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (StockApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = StockApiServiceFactory.getStockApi();
      }

      this.delegate = delegate;
   }

    @GET
    @Path("/action")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets a specified stock object", notes = "", response = Response.class, tags={ "stocks", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request - invalid ticker entered", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Stock not found", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Response.class) })
    public Response stockActionGet(@ApiParam(value = "Specified ticker name [ID]",required=true) @QueryParam("ticker") String ticker
,@Context SecurityContext securityContext)
    throws APIException {
        return delegate.stockActionGet(ticker,securityContext);
    }
    @POST
    @Path("/action")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Adds stock(s) to a given use's portfolio", notes = "", response = Response.class, tags={ "stocks", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request - invalid query param(s) entered", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Stock and/or user not found", response = Response.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Response.class) })
    public Response stockActionPost(@ApiParam(value = "Specified ticker name [ID]",required=true) @QueryParam("ticker") String ticker
,@ApiParam(value = "Number of shares to be added",required=true, defaultValue="1") @DefaultValue("1") @QueryParam("shares") Integer shares
,@ApiParam(value = "User's email",required=true) @QueryParam("email") String email
,@Context SecurityContext securityContext)
    throws APIException {
        return delegate.stockActionPost(ticker,shares,email,securityContext);
    }
}
