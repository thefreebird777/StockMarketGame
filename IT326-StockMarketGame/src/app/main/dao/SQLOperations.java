package app.main.dao;

import java.util.ArrayList;

import app.main.exceptions.APIException;
import app.main.model.Account;
import app.main.model.League;
import app.main.model.Stock;
import app.main.model.User;

public interface SQLOperations {
	public User fetchUser(User user) throws APIException;
	public League fetchLeague(League league) throws APIException;
	public Account fetchAccount(Account account) throws APIException;
	public Stock fetchStock(Stock stock) throws APIException;
	public int insert(String table, ArrayList<String> vars) throws APIException;
	public int update(String table, String valColumn, String val, String column, String location) throws APIException;
	public int updateAdvanced(String table, String valColumn, String val, String column1, String location1, String column2, String location2) throws APIException;
	public int remove(String table, String column, String location) throws APIException;
}
