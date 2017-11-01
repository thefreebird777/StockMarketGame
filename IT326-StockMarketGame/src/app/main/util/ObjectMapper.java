package app.main.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.main.exceptions.APIException;
import app.main.model.Account;
import app.main.model.League;
import app.main.model.Stock;
import app.main.model.User;

public class ObjectMapper {

	/**
	 * Maps a user object from the database
	 * @param rs - ResultSet mapping
	 * @return - User object
	 * @throws - APIException
	 */
	public static synchronized User mapUser(ResultSet rs) throws APIException {		
		try {
			User user = new User();	
			user.setEmail(rs.getString("EMAIL"));
			user.setFirstName(rs.getString("FNAME"));
			user.setLastName(rs.getString("LNAME"));
			user.setAdmin(rs.getBoolean("ADMIN"));
			List<String> accountList = new ArrayList<String>(Arrays.asList(rs.getString("ACCOUNTS").split(",")));
			List<String> leagueList = new ArrayList<String>(Arrays.asList(rs.getString("LEAGUES").split(",")));			
			//set league list
			//set account list
			
			return user;
		} catch (SQLException sqlEx) {
			throw new APIException(500, sqlEx.getMessage());
		} catch(Exception e) {
			throw new APIException(500, e.getMessage());
		}
	}
	
	/**
	 * Maps a league object from the database
	 * @param rs - ResultSet mapping
	 * @return - League object
	 * @throws - APIException
	 */
	public static synchronized League mapLeague(ResultSet rs) throws APIException {
		try {
			League league = new League();	
			league.setLeagueID(rs.getString("LEAGUE_ID"));
			league.setName(rs.getString("NAME"));									
			List<String> userList = new ArrayList<String>(Arrays.asList(rs.getString("USERS").split(",")));
			List<String> adminList = new ArrayList<String>(Arrays.asList(rs.getString("ADMINS").split(",")));
			//set user list
			//set admin list
			
			return league;
		} catch (SQLException sqlEx) {
			throw new APIException(500, sqlEx.getMessage());
		} catch(Exception e) {
			throw new APIException(500, e.getMessage());
		}
	}
	
	/**
	 * Maps an Account object from the database
	 * @param rs - ResultSet mapping
	 * @return - Account object
	 * @throws - APIException
	 */
	public static synchronized Account mapAccount(ResultSet rs) throws APIException {
		try {
			Account account = new Account();	
			account.setAcctId(rs.getString("ACCT_ID"));
			account.setOwnerEmail(rs.getString("EMAIL"));
			account.setLeagueID(rs.getString("LEAGUE_ATTACHED"));
			account.setValue(Double.parseDouble(rs.getString("VALUE")));
			List<String> portfolio = new ArrayList<String>(Arrays.asList(rs.getString("STOCKS").split(",")));			
			//set account list			
			
			
			return account;
		} catch (SQLException sqlEx) {
			throw new APIException(500, sqlEx.getMessage());
		} catch(Exception e) {
			throw new APIException(500, e.getMessage());
		}
	}
	
	/**
	 * Maps a Stock object from the database
	 * @param rs - ResultSet mapping
	 * @return - Stock object
	 * @throws - APIException
	 */
	public static synchronized Stock mapStock(ResultSet rs) throws APIException {
		try {
			Stock stock = new Stock();	
			stock.setName(rs.getString("TICKER"));
			stock.setValue(Double.parseDouble(rs.getString("VALUE")));
			return stock;
		} catch (SQLException sqlEx) {
			throw new APIException(500, sqlEx.getMessage());
		} catch(Exception e) {
			throw new APIException(500, e.getMessage());
		}
	}
}