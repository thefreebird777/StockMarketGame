package org.hibernate;

import org.hibernate.models.Account;
import org.hibernate.models.League;
import org.hibernate.models.Stock;
import org.hibernate.models.User;
import java.util.HashMap;

public class Session {

    private static HashMap<String, User> userMap;
    private static HashMap<String, League> leagueMap;
    private static HashMap<String, Account> accountMap;
    
    public Session() {
        userMap = new HashMap<>();
        leagueMap = new HashMap<>();
        accountMap = new HashMap<>();
        initSession();
    }
    
    public static HashMap<String, User> getUserMap() { return userMap; }
    public static void setUserMap(HashMap<String, User> aUserMap) { userMap = aUserMap; }
    public static HashMap<String, League> getLeagueMap() { return leagueMap; }
    public static void setLeagueMap(HashMap<String, League> aLeagueMap) { leagueMap = aLeagueMap; }
    public static HashMap<String, Account> getAccountMap() { return accountMap; }
    public static void setAccountMap(HashMap<String, Account> aAccountMap) { accountMap = aAccountMap; }   
    
    public static void beginTransaction() { }
    
    public Object get(Object obj, String id) {                
        String classType = obj.getClass().toString();
        classType = classType.substring(classType.lastIndexOf(".")+1, classType.length());
        
        switch(classType) {
            case("User"):
                return userMap.get(id);
            case("League"):
                return leagueMap.get(id);
            case("Account"):
                return accountMap.get(id);
            default:
                return null;
        }
    }
    
    public void saveOrUpdate(Object obj, String id) {
        String classType = obj.getClass().toString();
        classType = classType.substring(classType.lastIndexOf(".")+1, classType.length());
        
        switch(classType) {
            case("User"):
                userMap.replace(id, (User)obj);
            	return;
            case("League"):
                leagueMap.replace(id, (League)obj);
            	return;
            case("Account"):
                accountMap.replace(id, (Account)obj);
            	return;
            default:
                return;
        }
    }
    
    public void remove(Object obj, String id) {
        String classType = obj.getClass().toString();
        classType = classType.substring(classType.lastIndexOf(".")+1, classType.length());
        
        switch(classType) {
            case("User"):
                userMap.remove(id);
            	return;
            case("League"):
                leagueMap.remove(id);
            	return;
            case("Account"):
                accountMap.remove(id);
            	return;
            default:
                return;
        }
    }
    
    public void add(Object obj, String id) {
        String classType = obj.getClass().toString();
        classType = classType.substring(classType.lastIndexOf(".")+1, classType.length());
        
        switch(classType) {
            case("User"):
                userMap.put(id, (User)obj);
            	return;
            case("League"):
                leagueMap.put(id, (League)obj);
            	return;
            case("Account"):
                accountMap.put(id, (Account)obj);
            	return;
            default:
                return;
        }
    }
    
    private static void initSession() {
        Account account = new Account("1", "rdevitt@ilstu.edu");
        account.addStock("AMZN", new Stock("AMZN", 945.21, 4));
        
        User user = new User("rdevitt@ilstu.edu", "test", "Randy", "DeVitto", null, account, 5500.00, 1);
        League league = new League("1", "Wolf of Dale Street", user, false, 1);
        league.setTotalValue((945.21 * 4));
        
        userMap.put(user.getEmail(), user);
        leagueMap.put("1", league);
        accountMap.put("1", account);
    }
}