package app.main.services;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

public class StockAPIService {

    private String URL = "www.alphavantage.co";
    private String  KEY = "&interval=15min&apikey=T43MK3VWRNJ7TNH8";
    private String QUERY = "/query?function=TIME_SERIES_INTRADAY&symbol=";
    private StockService STOCK_SERVICE = new StockService();
    private ArrayList<String> tickerList;

    public HttpEntity getData(String symbol){
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpEntity entity = null;
        try {
            HttpHost target = new HttpHost(URL, 80, "http");
            HttpGet getRequest = new HttpGet(QUERY + symbol + KEY);

            HttpResponse httpResponse = httpclient.execute(target, getRequest);
            entity = httpResponse.getEntity();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

        return entity;
    }

    private int updateStocks(){

        return 0;
    }
}