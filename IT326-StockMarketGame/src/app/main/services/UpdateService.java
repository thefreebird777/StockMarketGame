package app.main.services;

import java.util.HashMap;

public class UpdateService {

	private static HashMap<String, Double> marketTable;
	
	public UpdateService() { 
		marketTable = new HashMap<String, Double>(); 
		initMarketTable();
	}
	
	public void updateTable() {
		
	}
	
	public void updateStock(String ticker, double value) {
		marketTable.replace(ticker, value);
	}
	
	private void initMarketTable() {
		
	}
}