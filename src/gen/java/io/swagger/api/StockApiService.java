package io.swagger.api;

import app.exceptions.APIException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-05T00:20:05.744Z")
public abstract class StockApiService {
    public abstract Response stockActionGet( @NotNull String ticker,SecurityContext securityContext) throws APIException;
    public abstract Response stockActionPost( @NotNull String ticker, @NotNull Integer shares, @NotNull String email,SecurityContext securityContext) throws APIException;
}
