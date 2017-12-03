package app.api.impl;

import app.api.swagger.ApiResponseMessage;
import app.api.swagger.NotFoundException;
import app.api.swagger.StockApiService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-03T01:25:03.125Z")
public class StockApiServiceImpl extends StockApiService {
    @Override
    public Response stockActionGet( @NotNull String ticker, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response stockActionPost( @NotNull String ticker,  @NotNull Integer shares,  @NotNull String email, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
