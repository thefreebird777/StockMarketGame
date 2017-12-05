package org.hibernate;

import org.hibernate.models.Account;
import org.hibernate.models.League;
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
        
        switch(classType) {
            case("User"):
                userMap.replace(id, (User)obj);
            case("League"):
                leagueMap.replace(id, (League)obj);
            case("Account"):
                accountMap.replace(id, (Account)obj);
            default:
                return;
        }
    }
    
    public void remove(Object obj, String id) {
        String classType = obj.getClass().toString();
        
        switch(classType) {
            case("User"):
                userMap.remove(id);
            case("League"):
                leagueMap.remove(id);
            case("Account"):
                accountMap.remove(id);
            default:
                return;
        }
    }
    
    public void add(Object obj, String id) {
        String classType = obj.getClass().toString();
        
        switch(classType) {
            case("User"):
                userMap.put(id, (User)obj);
            case("League"):
                leagueMap.put(id, (League)obj);
            case("Account"):
                accountMap.put(id, (Account)obj);
            default:
                return;
        }
    }
    
    private static void initSession() {
        Account account = new Account("1", "rdevitt@ilstu.edu");
        User user = new User("rdevitt@ilstu.edu", "test", "Randy", "DeVitto", account);
        userMap.put(user.getEmail(), user);
    }
    
}