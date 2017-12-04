package app.bo;

import app.dao.AccountDataAccess;
import app.dao.DataAccessOperations;
import app.dao.LeagueDataAccess;
import app.dao.UserDataAccess;
import app.exceptions.APIException;
import app.models.Stock;
import app.models.User;
import app.services.LoginService;
import app.services.StockAPIService;
import app.services.InternalStockManagementService;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class RuntimeHandler {
    
    private static final UserDataAccess USER_DAO = new UserDataAccess();
    private static final LeagueDataAccess LEAGUE_DAO = new LeagueDataAccess();
    private static final AccountDataAccess ACCT_DAO = new AccountDataAccess();
    private static StockAPIService updateService; //placeholder for auto-update service
    
    /**
     * Constructor method
     */
    public RuntimeHandler() {
        try {
            updateService = new StockAPIService();
        } catch(APIException apiEx) {
            System.out.println("Critical failure: failed to create StockAPIService");
            System.exit(0);
        }
    }

    /**
     * Generic SQL select method to retrieve DB objects
     * @param obj - converted JSON object to application model object
     * @param objClass - used to switch between DAO objects
     * @param id - 
     * @return - Database object
     * @throws APIException 
     */
    public synchronized Object select(Object obj, String objClass, int id) throws APIException {
        try {
            DataAccessOperations dao = selectDAO(objClass);
            Object fetched = dao.select(obj, id);
            return fetched;
        } catch(APIException apiEx) {
            throw apiEx;
        }
    }

    /**
     * Generic SQL update (put) method 
     * @param obj - converted JSON object to application model object
     * @param objClass - used to switch between DAO objects
     * @return - HTTP status
     * @throws APIException 
     */
    public synchronized int saveOrUpdate(Object obj, String objClass) throws APIException {
        try {
            DataAccessOperations dao = selectDAO(objClass);    
            return dao.saveOrUpdate(obj);
        } catch(APIException apiEx) {
            throw apiEx;
        } catch(Exception e) {
            throw new APIException(500, e.getMessage(), "");
        }
    }
    
    /**
     * Generic SQL add (insert) method
     * @param obj - converted JSON object to application model object
     * @param objClass - used to switch between DAO objects
     * @return - HTTP status
     * @throws APIException 
     */
    public synchronized int add(Object obj, String objClass) throws APIException {
        try {
            DataAccessOperations dao = selectDAO(objClass); 
            return dao.add(obj);
        } catch(APIException apiEx) {
            throw apiEx;
        } catch(Exception e) {
            throw new APIException(500, e.getMessage(), "");
        }
    }

    /**
     * Generic SQL remove (delete) method
     * @param obj - converted JSON object to application model object
     * @param objClass - used to switch between DAO objects
     * @return - HTTP status
     * @throws APIException 
     */
    public synchronized int remove(Object obj, String objClass) throws APIException {
        try {
            DataAccessOperations dao = selectDAO(objClass); 
            return dao.remove(obj);
        } catch(APIException apiEx) {
            throw apiEx;
        } catch(Exception e) {
            throw new APIException(500, e.getMessage(), "");
        }
    }
    
    /**
     * Verifies a user's login and returns a JWT token
     * @param email - user email
     * @param password - user password
     * @return - JWT token
     * @throws APIException 
     */
    public synchronized String login(String email, String password) throws APIException {
        try {
            User user = (User)USER_DAO.select(email, 0);
            if(user.getEmail().equals(email) && user.getPassword().equals(password))
                return LoginService.generateJWT();
            else
                throw new APIException(401, "Invalid login credentials", "");
        } catch(APIException apiEx) {
            throw apiEx;
        } catch(Exception e) {
            throw new APIException(500, e.getMessage(), "");
        }        
    }
    
    /**
     * Returns a stock object to the API
     * @param ticker - ticker name [ID]
     * @return - Stock object
     * @throws APIException
     */
    public synchronized Stock getStock(String ticker) throws APIException {
        try {
            return InternalStockManagementService.getStock(ticker);
        } catch(APIException apiEx) {
            throw apiEx;
        } catch(Exception e) {
            throw new APIException(500, e.getMessage(), "");
        }        
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
       try {
            User user = (User)USER_DAO.select(email, 0);
            double price = InternalStockManagementService.getStock(ticker).price;
            
            //checks if a user has sufficient funds AND the market is open
            if(checkFunds(user.getFunds(), price, quantity) && verifyMarketOpen()) {
                double totalCost = (quantity * price);
                user.setFunds((user.getFunds() - totalCost));
                
                // add stock + shares to account
                
                return USER_DAO.saveOrUpdate(user); //updates user's account with new stock(s)
            } else {
                if(checkFunds(user.getFunds(), price, quantity) == false)
                    throw new APIException(403, "Forbidden operation - insufficient funds", "");
                else
                    throw new APIException(403, "Forbidden operation - market closed", "");
            }  
        } catch(APIException apiEx) {
            throw apiEx;
        } catch(Exception e) {
            throw new APIException(500, e.getMessage(), "");
        }        
    }
    
    /**
     * Removes a stock from a user's account
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
    
    /**
     * Helper method that returns a DAO to be used
     * @param classType - indicator class
     * @return - DAO object
     */
    private static DataAccessOperations selectDAO(String classType) throws APIException {
        switch(classType) {
            case ("User"):
                return USER_DAO;
            case ("League"):
                return LEAGUE_DAO;
            case ("Account"):
                return ACCT_DAO;
            default:
                throw new APIException(500, "Internal Server Error", "selectDAO method");
        }
    }
}