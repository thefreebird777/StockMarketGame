package app.services;

import app.exceptions.APIException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.google.gson.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StockAPIService {

    private static final String URL = "www.alphavantage.co";
    private static final String  KEY = "&interval=1min&apikey=T43MK3VWRNJ7TNH8";
    private static final String QUERY = "/query?function=TIME_SERIES_INTRADAY&symbol=";
    private static final InternalStockManagementService STOCK_SERVICE = new InternalStockManagementService();
    private static final ScheduledExecutorService EXEC = Executors.newScheduledThreadPool(1);    
    private static final String[] tickerList = {"MSFT","AAPL","C","GE","GOOGL",
        "BAC","INTC","MU","F","AXP","BA","CAT","CVX","CSCO","KO","DIS","XOM","GS",
        "HD","IBM","JNJ","JPM","MCD", "MRK","NKE","PFE","PG","TRV","UTX","UNH",
        "VZ","V","WMT","AMZN","FB","BABA","BRK-A","WFC", "RDS-A","BUD","TSM",
        "ORCL","T","CHL","HSBC","TM","CMCSA","PM","UL","MA","PEP","ABBV","TOT",
        "MMM","SAP","BP","AMGN","NVDA"};
    
    /**
     * Default constructor that initializes update service
     * @throws APIException 
     */
    public StockAPIService() throws APIException { running(); }

    /**
     * Calls external API to get stock information
     * @param symbol - ticker symbol
     * @return JSON object from API call
     */
    public JsonObject getData(String symbol){
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpEntity entity = null;
        String s = "";
        JsonElement jElement = null;
        JsonObject  jsonObj = null;
        try {
            HttpHost target = new HttpHost(URL, 80, "http");
            HttpGet getRequest = new HttpGet(QUERY + symbol + KEY);

            HttpResponse httpResponse = httpclient.execute(target, getRequest);
            entity = httpResponse.getEntity(); //api returns HTTP Entity
            s = EntityUtils.toString(entity); //parse entity to String
            jElement = new JsonParser().parse(s.toString()); //parse String to JSON
            jsonObj = jElement.getAsJsonObject();

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

    /**
     * loops through tickerlist and updates the price of each stock
     * @return
     */
    private int updateStocks() {
            for (int i = 0; i < tickerList.length; i++) {
                double value = 0;
                String s = "";

                JsonObject jsonObj = getData(tickerList[i]);

                //need to get most recent timestamp from meta data
                JsonObject metaData = jsonObj.getAsJsonObject("Meta Data");
                JsonPrimitive prim = metaData.getAsJsonPrimitive("3. Last Refreshed");
                s = prim.getAsString();

                jsonObj = jsonObj.getAsJsonObject("Time Series (1min)");//interval of api, irrelevant since we call it every 15 mins
                jsonObj = jsonObj.getAsJsonObject(s); //string s is the timestamp
                prim = jsonObj.getAsJsonPrimitive("4. close"); //"close" is the price at the end of the most recent update

                s = prim.getAsString();

                value = Double.parseDouble(s);

                try {
                    STOCK_SERVICE.updateStock(tickerList[i], value);
                } catch(APIException apiEx) {
                    continue;
                }
            }
            return 200;
    }
    
    /**
     * Executes an automated API calling service. Called every 15 min
     */
    private void running() throws APIException { 
        try {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    updateStocks();
                }
            };
            
            EXEC.scheduleAtFixedRate(runnable , 0, 15, TimeUnit.MINUTES); 
        } catch(Exception e) {
            throw new APIException(500, e.getMessage(), "");
        }
    }
}