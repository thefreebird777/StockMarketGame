package app.api.impl;

import app.api.swagger.StockApiService;
import app.bo.RuntimeHandler;
import app.exceptions.APIException;
import app.models.Stock;
import com.google.gson.Gson;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-03T01:25:03.125Z")
public class StockApiServiceImpl extends StockApiService {
    private static final Gson GSON = new Gson();
    private static final RuntimeHandler HANDLER = new RuntimeHandler();
    
    /**
     * Fetches a specified stock object
     * @param ticker - stock ticker name [ID]
     * @param securityContext
     * @return - JSON stock object
     * @throws APIException 
     */
    @Override
    public Response stockActionGet( @NotNull String ticker, SecurityContext securityContext) throws APIException {
        try {
            Stock stock = HANDLER.getStock(ticker);
            return Response.ok().entity(GSON.toJson(stock)).build();
        } catch(APIException apiEx) {
            return Response.status(apiEx.getCode()).entity(apiEx).build();
        } catch (Exception e) {
            return Response.status(500).entity(new APIException(500, e.getMessage(), "")).build();
        }
    }
    
    /**
     * Adds a stock(s) to a specified user's account
     * @param ticker - stock ticker name [ID}
     * @param shares - number of shares to purchase
     * @param email - user's email address
     * @param securityContext
     * @return - HTTP Response
     * @throws APIException 
     */
    @Override
    public Response stockActionPost( @NotNull String ticker,  @NotNull Integer shares,  @NotNull String email, SecurityContext securityContext) throws APIException {
        try {
            if(shares <= 0) 
                throw new APIException(400, "Bad Request", "");   
            
            HANDLER.buyStock(email, ticker, shares);
            return Response.ok().build();
        } catch(APIException apiEx) {
            return Response.status(apiEx.getCode()).entity(apiEx).build();
        } catch (Exception e) {
            return Response.status(500).entity(new APIException(500, e.getMessage(), "")).build();
        }
    }
}