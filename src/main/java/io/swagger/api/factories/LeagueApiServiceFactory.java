package io.swagger.api.factories;

import io.swagger.api.LeagueApiService;
import io.swagger.api.impl.LeagueApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-05T00:20:05.744Z")
public class LeagueApiServiceFactory {
    private final static LeagueApiService service = new LeagueApiServiceImpl();

    public static LeagueApiService getLeagueApi() {
        return service;
    }
}
