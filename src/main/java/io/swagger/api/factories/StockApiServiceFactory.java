package io.swagger.api.factories;

import io.swagger.api.StockApiService;
import io.swagger.api.impl.StockApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-05T00:20:05.744Z")
public class StockApiServiceFactory {
    private final static StockApiService service = new StockApiServiceImpl();

    public static StockApiService getStockApi() {
        return service;
    }
}
