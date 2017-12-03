package app.api.factories;

import app.api.swagger.LeagueApiService;
import app.api.impl.LeagueApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-03T01:25:03.125Z")
public class LeagueApiServiceFactory {
    private final static LeagueApiService service = new LeagueApiServiceImpl();

    public static LeagueApiService getLeagueApi() {
        return service;
    }
}
