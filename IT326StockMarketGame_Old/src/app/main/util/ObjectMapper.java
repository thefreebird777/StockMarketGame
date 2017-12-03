package app.main.util;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		User user = null;
		
		try {
			user = new User();	
			user.setEmail(rs.getString("EMAIL"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setFirstName(rs.getString("FNAME"));
			user.setLastName(rs.getString("LNAME"));
			user.setLeagueID(rs.getString("LEAGUE_ID"));
			user.setAccountID(rs.getString("ACCT_ID"));
			user.setFunds(Double.parseDouble(rs.getString("FUNDS")));
			rs.close();
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
		League league = null;
		String userEmail, adminEmail;
		
		try {
			league = new League();	
			league.setLeagueID(rs.getString("LEAGUE_ID"));
			league.setName(rs.getString("NAME"));									

			while(rs.next()) {
				userEmail = rs.getString("USER_EMAIL");
				adminEmail = rs.getString("ADMIN_EMAIL");
				
				if(!userEmail.equals(null) && !userEmail.equals("")) 
					league.getUserList().add(userEmail);
				
				if(!adminEmail.equals(null) && !adminEmail.equals(""))
					league.getAdminList().add(adminEmail);					
			}
			rs.close();
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
		Account account;
		String ticker, value;
		
		try {
			account = new Account();	
			account.setAcctId(rs.getString("ACCT_ID"));

			while(rs.next()) {
				ticker = rs.getString("TICKER");
				value = rs.getString("VALUE");
				
				if(!ticker.equals(null) && !ticker.equals("")) 
					account.getTickerList().add(ticker);
				
				if(!value.equals(null) && !value.equals(""))
					account.getValueList().add(Double.parseDouble(value));					
			}			
			rs.close();
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
			rs.close();
			return stock;
		} catch (SQLException sqlEx) {
			throw new APIException(500, sqlEx.getMessage());
		} catch(Exception e) {
			throw new APIException(500, e.getMessage());
		}
	}
}