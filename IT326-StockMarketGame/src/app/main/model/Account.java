package app.main.model;

import java.util.HashMap;

public class Account {
	
	private String acctId;
	private String ownerEmail;
	private String leagueID;
	private HashMap<String, Stock> stockList;
	private double value;
	
	public String getAcctId() { return acctId; }
	public void setAcctId(String acctId) { this.acctId = acctId; }
	public String getOwnerEmail() {	return ownerEmail; }
	public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }
	public String getLeagueID() { return leagueID; }
	public void setLeagueID(String leagueID) { this.leagueID = leagueID; }	
	public HashMap<String, Stock> getStockList() { return stockList; }
	public void setStockList(HashMap<String, Stock> stockList) { this.stockList = stockList; }
	public double getValue() { return value; }
	public void setValue(double value) { this.value = value; }	
}
