package app.main.bo;

import app.main.exceptions.APIException;

public interface DataAccessOperations {
	
	public int addUser(String json) throws APIException;
	public int addUserToLeague(String json, String email) throws APIException;	
	public int addLeague(String json) throws APIException;	
	public int addStock(String json) throws APIException;
	public int addStockToAccount(String json, String ticker) throws APIException;	
	public int update(String json) throws APIException;
	public int remove(String json) throws APIException;	
	public String getUser(String email) throws APIException;
	public String getLeague(String leagueID) throws APIException;
	public String getAccount(String acctID) throws APIException;
	public String getStock(String ticker) throws APIException;
}
