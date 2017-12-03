package app.api.swagger;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-03T01:25:03.125Z")
public abstract class StockApiService {
    public abstract Response stockActionGet( @NotNull String ticker,SecurityContext securityContext) throws NotFoundException;
    public abstract Response stockActionPost( @NotNull String ticker, @NotNull Integer shares, @NotNull String email,SecurityContext securityContext) throws NotFoundException;
}
