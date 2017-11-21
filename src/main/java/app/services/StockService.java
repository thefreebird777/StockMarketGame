package app.services;

import app.models.Stock;
import java.util.HashMap;

public class StockService {
    
    private static HashMap<String, Double> stockTable;
    
    public StockService() { 
        stockTable = new HashMap<String, Double>();
        initTable();
    }
    
    public static Stock getStock(String ticker) {
        return new Stock(ticker, stockTable.get(ticker));
    }
    
    public static synchronized void updateStock(String ticker, double price) {
        //to do
    }
    
    private void initTable() {
        //to do
    }
}
