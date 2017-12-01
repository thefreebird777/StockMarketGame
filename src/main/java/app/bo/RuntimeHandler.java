package app.bo;

import app.dao.DataAccess;
import app.exceptions.APIException;
import app.models.User;
import app.services.LoginService;
import app.services.StockAPIService;
import app.services.StockService;
import com.google.gson.Gson;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class RuntimeHandler implements DataAccessOperations {
    private static final StockAPIService API_SERVICE = new StockAPIService();
    private static final Gson GSON = new Gson();
    
    public RuntimeHandler() { 
        updateStocks();
    }

    @Override
    public synchronized String select(String json, String objClass, int id) throws APIException {
        try {
            String returnJSON = GSON.toJson(DataAccess.select(json, id));
            return returnJSON;
        } catch(APIException apiEx) {
            throw apiEx;
        }
    }

    @Override
    public synchronized int update(String json, String objClass) throws APIException {
        try {
            //convert object
            return DataAccess.update(json);
        } catch(APIException apiEx) {
            throw apiEx;
        }
    }

    @Override
    public synchronized int remove(String json, String objClass) throws APIException {
        return 200;
    }

    @Override
    public synchronized int put(String json, String objClass) throws APIException {
        return 200;
    }
    
    public synchronized String getStock(String ticker) {
        String json = GSON.toJson(StockService.getStock(ticker));
        return json;
    }
    
    /**
     * Verifies a user's login and returns a JWT token
     * @return - JWT token
     * @throws APIException 
     */
    public synchronized String login() throws APIException {
        
        return LoginService.generateJWT();
    }
    
    /**
     * Verifies a user can afford a given stock and updates their account
     * @param email - user email ID
     * @param ticker - stock ticker name
     * @param quantity - number of shares to buy
     * @return - HTTP status
     * @throws APIException 
     */
    public synchronized int buyStock(String email, String ticker, int quantity) throws APIException {
        User user = (User)DataAccess.select(email, 0);
        double price = StockService.getStock(ticker).price;
        
        try {
            //checks if a user has sufficient funds AND the market is open
            if(checkFunds(user.getFunds(), price, quantity) && verifyMarketOpen()) {
                double totalCost = (quantity * price);
                return DataAccess.update(user); //updates user's account w/ new stock
            } else {
                return 401; //not allowed to buy (un-authorized)
            }  
        } catch(APIException apiEx) {
            throw apiEx;
        }          
    }
    
    /**
     * Removes 
     * @param email
     * @param ticker
     * @param quantity - number of shares to remove
     * @return
     * @throws APIException 
     */
    public synchronized int removeStock(String email, String ticker, int quantity) throws APIException {
        
        return 0;
    }
    
    /**
     * Automatically updates the internal stock table every 10 min
     */
    private static void updateStocks() {
        long count = 0;
        while(true) {
            if(count == 600000) {
                if(verifyMarketOpen()) {
            
                }
                count = 0; //resets internal ms count
            } else {
                count++;
            }
        }
    }
    
    /**
     * Helper method that checks if a user can afford a specified stock
     * @param userFunds - current user funds
     * @param price - desired stock price
     * @param quantity - number of shares to buy
     * @return - True/False
     */
    private synchronized boolean checkFunds(double userFunds, double price, int quantity) {
        return ((quantity * price) > userFunds);
    }
    
    /**
     * Helper method that calculates if the stock market is open
     * @return - True/False
     */
    private static boolean verifyMarketOpen() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        return (hour > 9 || (hour == 9 && min >= 30)) && (hour < 16);
    }
}