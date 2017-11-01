package app.main.dao;

import app.main.exceptions.APIException;
import app.main.model.Account;
import app.main.model.League;
import app.main.model.Stock;
import app.main.model.User;

public interface SQLSelect {
	public User fetchUser(String email) throws APIException;
	public League fetchLeague(String leagueID) throws APIException;
	public Account fetchAccount(String acctID) throws APIException;
	public Stock fetchStock(String tickerName) throws APIException;
	public void add(String[] var, String table) throws APIException;
	public void remove(String[] var, String table) throws APIException;
	public void update(String[] var, String table) throws APIException;
}
