package app.main.services;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StockAPIService {

    private String URL = "www.alphavantage.co";
    private String  KEY = "&interval=15min&apikey=T43MK3VWRNJ7TNH8";
    private String QUERY = "/query?function=TIME_SERIES_INTRADAY&symbol=";
    private StockService STOCK_SERVICE = new StockService();
    private ArrayList<String> tickerList;
    private ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

    /**
     *  calls runnable every 15 mintues
     */
    public void running(){
        exec.scheduleAtFixedRate(runnable , 0, 15, TimeUnit.MINUTES);
    }

    /**
     * calls external API to get stock information
     * @param symbol
     * @return JSON object from API call
     */
    public JSONObject getData(String symbol){
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpEntity entity = null;
        String s = "";
        JSONObject jsonObj = null;
        try {
            HttpHost target = new HttpHost(URL, 80, "http");
            HttpGet getRequest = new HttpGet(QUERY + symbol + KEY);

            HttpResponse httpResponse = httpclient.execute(target, getRequest);
            entity = httpResponse.getEntity(); //api returns HTTP Entity
            s = EntityUtils.toString(entity); //parse entity to String
            jsonObj = new JSONObject(s.toString()); //parse String to JSON

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return jsonObj;
    }

    /**
     * calls updateStocks method
     */
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateStocks();
        }
    };

    /**
     * loops through tickerlist and updates the price of each stock
     * @return
     */
    private int updateStocks(){
        for (int i = 0; i < tickerList.size(); i++)
        {
            getData(tickerList.get(i));
            //TODO
        }
        return 0;
    }
}