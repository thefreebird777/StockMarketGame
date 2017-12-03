package app.api.factories;

import app.api.swagger.StockApiService;
import app.api.impl.StockApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-12-03T01:25:03.125Z")
public class StockApiServiceFactory {
    private final static StockApiService service = new StockApiServiceImpl();

    public static StockApiService getStockApi() {
        return service;
    }
}
