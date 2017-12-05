package org.hibernate.models;

import app.models.*;
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
    public void addStock(String ticker, Stock stock) { this.stockMap.put(ticker, stock); }
    public void removeStock(String ticker) { this.stockMap.remove(ticker); }
    public void updateStock(String ticker, Stock stock) { this.stockMap.replace(ticker, stock); }
}