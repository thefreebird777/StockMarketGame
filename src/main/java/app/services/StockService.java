package app.services;

import app.exceptions.APIException;
import java.util.HashMap;
import org.hibernate.models.Stock;

public class StockService {

	private static HashMap<String, Double> marketTable;

	public StockService() {
            marketTable = new HashMap<String, Double>();
            initMarketTable();
	}
        
        public static Stock getStock(String ticker) throws APIException {
            try {
                return new Stock(ticker, marketTable.get(ticker));
            } catch(Exception e) {
                throw new APIException(404, e.getMessage(), "");
            }
        }

	public void updateStock(String ticker, double value) throws APIException {
            try {
                marketTable.replace(ticker, value);
            } catch(Exception e) {
                throw new APIException(404, e.getMessage(), "");
            } 
	}

	private void initMarketTable() {
            marketTable.put("MSFT", 0.00);    //Microsoft
            marketTable.put("AAPL", 0.00);    //Apple
            marketTable.put("C", 0.00);       //Citigroup
            marketTable.put("GE", 0.00);      //General Electric
            marketTable.put("GOOGL", 0.00);   //Google
            marketTable.put("BAC", 0.00);     //Bank of America
            marketTable.put("INTC", 0.00);    //Intel
            marketTable.put("MU", 0.00);      //Micron Technology Inc
            marketTable.put("F", 0.00);       //Ford
            marketTable.put("AXP", 0.00);     //American Express
            marketTable.put("BA", 0.00);      //Boeing
            marketTable.put("CAT", 0.00);     //Caterpillar
            marketTable.put("CVX", 0.00);     //Chevron
            marketTable.put("CSCO", 0.00);    //Cisco
            marketTable.put("KO", 0.00);      //Coca-Cola
            marketTable.put("DIS", 0.00);     //Disney
            marketTable.put("XOM", 0.00);     //Exxon Mobil
            marketTable.put("GS", 0.00);      //Goldman Sachs
            marketTable.put("HD", 0.00);      //Home Depot
            marketTable.put("IBM", 0.00);     //IBM
            marketTable.put("JNJ", 0.00);     //Johnson & Johnson
            marketTable.put("JPM", 0.00);     //JPMorgan Chase
            marketTable.put("MCD", 0.00);     //McDonald's
            marketTable.put("MRK", 0.00);     //Merck
            marketTable.put("NKE", 0.00);     //Nike
            marketTable.put("PFE", 0.00);     //Pfizer
            marketTable.put("PG", 0.00);      //Procter & Gamble
            marketTable.put("TRV", 0.00);     //Travelers Companies Inc
            marketTable.put("UTX", 0.00);     //United Technologies
            marketTable.put("UNH", 0.00);     //UnitedHealth
            marketTable.put("VZ", 0.00);      //Verizon
            marketTable.put("V", 0.00);       //Visa
            marketTable.put("WMT", 0.00);     //Wal-Mart
            marketTable.put("AMZN", 0.00);    //Amazon
            marketTable.put("FB", 0.00);      //Facebook
            marketTable.put("BABA", 0.00);    //Alibaba Group
            marketTable.put("BRK-A", 0.00);   //Berkshire Hathaway
            marketTable.put("WFC", 0.00);     //Wells Fargo
            marketTable.put("RDS-A", 0.00);   //Roal Dutch Shell
            marketTable.put("BUD", 0.00);     //Anheuser-Busch InBev
            marketTable.put("TSM", 0.00);     //Taiwan Semiconductor Manufacturing
            marketTable.put("ORCL", 0.00);    //Oracle
            marketTable.put("T", 0.00);       //AT&T
            marketTable.put("CHL", 0.00);     //China Mobile
            marketTable.put("HSBC", 0.00);    //HSBC Holdings
            marketTable.put("TM", 0.00);      //Toyota
            marketTable.put("CMCSA", 0.00);   //Comcast
            marketTable.put("PM", 0.00);      //Philip Morris
            marketTable.put("UL", 0.00);      //Unilever
            marketTable.put("MA", 0.00);      //MasterCard
            marketTable.put("PEP", 0.00);     //Pepsico
            marketTable.put("ABBV", 0.00);    //AbbVie
            marketTable.put("TOT", 0.00);     //Total
            marketTable.put("MMM", 0.00);     //3M
            marketTable.put("SAP", 0.00);     //SAP AG
            marketTable.put("BP", 0.00);      //BP
            marketTable.put("AMGN", 0.00);    //Amgen
            marketTable.put("NVDA", 0.00);    //NVIDIA
	}
}