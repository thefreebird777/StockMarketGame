package app.api.swagger;

import app.models.User;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-03T01:25:03.125Z")
public abstract class UserApiService {
    public abstract Response userEmailDelete(String email,SecurityContext securityContext) throws NotFoundException;
    public abstract Response userEmailGet(String email,SecurityContext securityContext) throws NotFoundException;
    public abstract Response userEmailPut(String email,User body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response userLoginGet( @NotNull String email, @NotNull String password,SecurityContext securityContext) throws NotFoundException;
    public abstract Response userPost(User body,SecurityContext securityContext) throws NotFoundException;
}
