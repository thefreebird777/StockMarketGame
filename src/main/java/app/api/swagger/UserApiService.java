package app.api.swagger;

import app.exceptions.APIException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-03T01:25:03.125Z")
public abstract class UserApiService {
    public abstract Response userEmailDelete(String email,SecurityContext securityContext) throws APIException;
    public abstract Response userEmailGet(String email,SecurityContext securityContext) throws APIException;
    public abstract Response userEmailPut(String email,String json,SecurityContext securityContext) throws APIException;
    public abstract Response userLoginGet( @NotNull String email, @NotNull String password,SecurityContext securityContext) throws APIException;
    public abstract Response userPost(String json,SecurityContext securityContext) throws APIException;
}
