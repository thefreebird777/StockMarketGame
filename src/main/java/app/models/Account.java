package app.models;

import java.util.HashMap;

public class Account {
    private String acctID;
    private String email;
    private HashMap<String, Stock> stockMap;
    
    public Account(String acctID, String email) {
        this.acctID = acctID;
        this.email = email;
        this.stockMap = new HashMap<String, Stock>();
    }
    
    public String getAcctID() { return acctID; }
    public void setAcctID(String acctID) { this.acctID = acctID; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}