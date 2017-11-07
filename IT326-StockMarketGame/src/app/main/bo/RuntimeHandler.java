package app.main.bo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import app.main.dao.DataAccess;
import app.main.exceptions.APIException;
import app.main.model.Account;
import app.main.model.League;
import app.main.model.User;

public class RuntimeHandler implements DataAccessOperations {

	private static DataAccess dao;
	private static final Gson GSON = new Gson();
	private static final Logger LOGGER = Logger.getLogger(RuntimeHandler.class.getName());
	
	private static int globalLeagues;
	private static int globalAccounts;
	
	public RuntimeHandler() throws APIException { 
		dao = new DataAccess(); 
		globalLeagues = 1;
		globalAccounts = 1;
	}

	@Override
	public synchronized int addUser(String json) throws APIException {
		User user = GSON.fromJson(json, User.class);
		ArrayList<String> temp = new ArrayList<String>(5);
		temp.add(user.getEmail());
		temp.add(user.getFirstName());
		temp.add(user.getLastName());
		temp.add(null);
		
		try {			
			this.addAccount(Integer.toString(globalAccounts)); //New row in Account table		
			temp.add(Integer.toString(globalAccounts));		
			return dao.add("USER", temp);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}

	@Override
	public synchronized int addUserToLeague(String json, String email) throws APIException {
		League league = GSON.fromJson(json, League.class);   
		ArrayList<String> temp = new ArrayList<String>(3);
		temp.add(league.getLeagueID());
		temp.add(league.getName());
//		temp.add(league.getUserEmail());
//		temp.add(league.getAdmin());
		
		try {
			dao.add("LEAGUE", temp);
			dao.update("USER", "LEAGUE_ID", league.getLeagueID(), "EMAIL", league.getUserEmail());
			return 200;
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}		 
	}

	@Override
	public synchronized int addStockToAccount(String json, String ticker) throws APIException {
		Account account = GSON.fromJson(json, Account.class); 
		ArrayList<String> temp = new ArrayList<String>(2);
		temp.add(account.getAcctId());
		temp.add(account.getTicker());
//		temp.add(Double.toString(account.getValue()));
		
		try {
			
			//check if have funds
			return dao.add("ACCOUNT", temp);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}		
	}

	@Override
	public synchronized int addLeague(String json) throws APIException {
		League league = GSON.fromJson(json, League.class);
		ArrayList<String> temp = new ArrayList<String>(3);
		temp.add(Integer.toString(globalLeagues));
		temp.add(league.getName());
//		temp.add(league.getUserEmail());
//		temp.add(league.getAdmin());
		
		try {
			int code = dao.add("LEAGUE", temp);
			insreaseLeagues();
			return code;
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}

	@Override
	public synchronized int addStock(String json) {
		
		return 0;
	}

	@Override
	public synchronized int update(String json) {
		
		return 0;
	}

	@Override
	public synchronized int remove(String json) {
		
		return 0;
	}
	
	@Override
	public synchronized String getUser(String email) throws APIException { return GSON.toJson(dao.fetchUser(email)); }
	@Override
	public synchronized String getLeague(String leagueID) throws APIException { return GSON.toJson(dao.fetchLeague(leagueID)); }
	@Override
	public synchronized String getAccount(String acctID) throws APIException { return GSON.toJson(dao.fetchAccount(acctID)); }
	@Override
	public synchronized String getStock(String ticker) throws APIException { return GSON.toJson(dao.fetchStock(ticker)); }
	
	private static synchronized void insreaseLeagues() { globalLeagues++; }
	private static synchronized void increaseAccounts() {	globalAccounts++; }
	
	private static void updateStockPrices() {
		
	}	
	
	private void addAccount(String acctID) throws APIException {
		ArrayList<String> temp = new ArrayList<String>(2);
		temp.add(acctID);
		temp.add("Example Name");
		temp.add("0.00");
		
		try {
			dao.add("ACCOUNT", temp);
			increaseAccounts();
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}
}