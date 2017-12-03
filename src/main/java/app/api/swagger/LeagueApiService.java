package app.api.swagger;

import app.models.League;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-03T01:25:03.125Z")
public abstract class LeagueApiService {
    public abstract Response leagueActionGet( @NotNull String leagueID,SecurityContext securityContext) throws NotFoundException;
    public abstract Response leagueActionPost( @NotNull String leagueID, @NotNull String email,SecurityContext securityContext) throws NotFoundException;
    public abstract Response leagueActionPut( @NotNull String leagueID,League body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response leagueEmailDelete(String email,SecurityContext securityContext) throws NotFoundException;
    public abstract Response leagueEmailPost(String email,League body,SecurityContext securityContext) throws NotFoundException;
}
