package app.main.dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import app.main.exceptions.APIException;
import app.main.model.Account;
import app.main.model.League;
import app.main.model.Stock;
import app.main.model.User;
import app.main.util.ObjectMapper;

public class DataAccess implements SQLSelect {
	
	private static Properties properties;
	private static BasicDataSource dataSource;
	private static Map<String, String> dbTableTemplates;	
	private static final Logger LOGGER = Logger.getLogger(DataAccess.class.getName());
	
	/**
	 * Constructor method
	 * @throws APIException
	 */
	public DataAccess() throws APIException { 
		this.initProperties();
		this.createClientPool("admin", "admin", "jdbc:derby:C:\\Users\\rdevi\\MyDB;create=true");
		
		dbTableTemplates = new HashMap<String, String>();
		dbTableTemplates.put("USER", "(EMAIL, FIRST_NAME, LAST_NAME, LEAGUE, ACCOUNT)");
		dbTableTemplates.put("LEAGUE", "(LEAGUE_ID, NAME, USERS, ADMIN)");
		dbTableTemplates.put("ACCOUNT", "(ACCT_ID, EMAIL, LEAGUE_ATTACHED, STOCKS, VALUE)");
		dbTableTemplates.put("STOCK", "(TICKER, VALUE)");
	}
	
	/**
	 * Prepares a SQL statement to retrieve a specified user
	 * @param email - primary key for User table
	 * @return user - user object
	 * @throws APIException
	 */
	@Override
	public synchronized User fetchUser(String email) throws APIException {
		PreparedStatement pStmnt = null;
		User user = null;	
		
		try {
			String sql = "SELECT * FROM USER WHERE EMAIL = ?";	
			pStmnt = getConnection().prepareStatement(sql);			
			pStmnt.setString(1, email);
			
			ResultSet rs = pStmnt.executeQuery();			
			user = ObjectMapper.mapUser(rs);
			return user;
		} catch (SQLException sqlEx) {
			LOGGER.log(Level.SEVERE, sqlEx.getMessage());
			throw new APIException(500, sqlEx.getMessage());
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}

	/**
	 * Prepares a SQL statement to retrieve a specified game league
	 * @param leagueID - primary key for League table
	 * @return league - league object
	 * @throws APIException
	 */
	@Override
	public synchronized League fetchLeague(String leagueID) throws APIException {
		PreparedStatement pStmnt = null;
		League league = null;
		
		try {
			String sql = "SELECT * FROM LEAGUE WHERE LEAGUE_ID = ?";	
			pStmnt = getConnection().prepareStatement(sql);
			pStmnt.setString(1, leagueID);	
			
			ResultSet rs = pStmnt.executeQuery();		
			league = ObjectMapper.mapLeague(rs);
			return league;
		} 
		catch (SQLException sqlEx) {
			LOGGER.log(Level.SEVERE, sqlEx.getMessage());
			throw new APIException(500, sqlEx.getMessage());
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}

	/**
	 * Prepares a SQL statement to retrieve a specified user portfolio
	 * @param email - primary key for Account table
	 * @return account - account object
	 * @throws APIException
	 */
	@Override
	public synchronized Account fetchAccount(String acctID) throws APIException {
		PreparedStatement pStmnt = null;
		Account acct = null;
		
		try {
			String sql = "SELECT * FROM ACCOUNT WHERE ACCT_ID = ?";	
			pStmnt = getConnection().prepareStatement(sql);
			pStmnt.setString(1, acctID);	
			
			ResultSet rs = pStmnt.executeQuery();			
			acct = ObjectMapper.mapAccount(rs);
			return acct;
		} 
		catch (SQLException sqlEx) {
			LOGGER.log(Level.SEVERE, sqlEx.getMessage());
			throw new APIException(500, sqlEx.getMessage());
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}

	/**
	 * Prepares a SQL statement to retrieve a specified stock
	 * @param ticker - primary key for Stock table
	 * @return stock - stock object
	 * @throws APIException
	 */
	@Override
	public synchronized Stock fetchStock(String ticker) throws APIException {
		PreparedStatement pStmnt = null;
		Stock stock = null;
		
		try {
			String sql = "SELECT * FROM MARKET WHERE TICKER = ?";	
			pStmnt = getConnection().prepareStatement(sql);
			pStmnt.setString(1, ticker);	
			
			ResultSet rs = pStmnt.executeQuery();			
			stock = ObjectMapper.mapStock(rs);
			return stock;
		} 
		catch (SQLException sqlEx) {
			LOGGER.log(Level.SEVERE, sqlEx.getMessage());
			throw new APIException(500, sqlEx.getMessage());
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}
	
	//------------------------------  Additional SQL Methods  ------------------------------ 
	
	@Override
	public synchronized void add(String[] var, String table) throws APIException {
		PreparedStatement pStmnt = null;
		
		try {
			String sql = "INSERT INTO ? " + dbTableTemplates.get(table) + " VALUES (";
			
			for(int i = 0; i < var.length; i++)
				sql.concat((var[i] + ", "));
			
			sql.concat(")");
			pStmnt = getConnection().prepareStatement(sql);
			pStmnt.setString(1, table);			
			pStmnt.executeQuery();
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}

	@Override
	public synchronized void remove(String[] var, String table) throws APIException {
		PreparedStatement pStmnt = null;
		String sql = "DELETE FROM ? WHERE ? = ?";
		
		try {
			pStmnt = getConnection().prepareStatement(sql);
			pStmnt.setString(1, table);
			pStmnt.setString(2, var[0]);
			pStmnt.setString(3, var[1]);
			pStmnt.executeQuery();
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}

	@Override
	public synchronized void update(String[] var, String table) throws APIException {
		PreparedStatement pStmnt = null;
		String sql = "UPDATE ? SET ? WHERE ? = ?";
		
		try {
			pStmnt = getConnection().prepareStatement(sql);
			pStmnt.setString(1, table);
			pStmnt.setString(2, var[0]);
			pStmnt.setString(3, var[1]);
			pStmnt.setString(4, var[2]);
			pStmnt.executeQuery();
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}
	
	//------------------------------  Helper Methods  ------------------------------ 
	
	/**
	 * Helper method that gets a pooled connection to the database
	 * @return - Connection object
	 * @throws APIException
	 */
	private static synchronized Connection getConnection() throws APIException {
		try {
			LOGGER.log(Level.INFO, "INFO: Retrieving database connection");
			return dataSource.getConnection(); 
		} catch (SQLException sqlEx) {
			LOGGER.log(Level.SEVERE, sqlEx.getMessage());
			throw new APIException(500, sqlEx.getMessage());
		}
	}
	
	/**
	 * Helper method that creates a new DBClient when found null
	 * @return - new database client
	 * @throws APIException
	 */
	private void createClientPool(String username, String password, String url) throws APIException { 
		LOGGER.log(Level.WARNING, "WARNING: Database client is null");
		LOGGER.log(Level.INFO, "INFO: Creating new database pool instance");
		try {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			dataSource.setUrl(url);			
			dataSource.setMinIdle(1);
			dataSource.setMaxIdle(6);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}
	
	/**
	 * Helper method that sets the class's database properties file
	 * @throws APIException
	 */
	private void initProperties() throws APIException {
		try {
			LOGGER.log(Level.INFO, "INFO: Loading database properties");
			InputStream is = new FileInputStream("config.properties");
			properties.load(is);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}	
	}
}