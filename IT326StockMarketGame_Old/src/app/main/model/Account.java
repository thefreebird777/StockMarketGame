package app.main.model;

import java.util.ArrayList;

public class Account {
	
	private String acctId;
	private String ticker;
	private ArrayList<String> tickerList;
	private ArrayList<Double> valueList;
	
	public String getAcctId() { return acctId; }
	public void setAcctId(String acctId) { this.acctId = acctId; }
	public String getTicker() { return ticker; }
	public void setTicker(String ticker) { this.ticker = ticker; }
	public ArrayList<String> getTickerList() { return tickerList; }
	public void setTickerList(ArrayList<String> tickerList) { this.tickerList = tickerList; }
	public ArrayList<Double> getValueList() { return valueList; }
	public void setValueList(ArrayList<Double> valueList) {	this.valueList = valueList;	}
}