package app.db_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.main.exceptions.APIException;

public class DBClient implements DBAccess {
	
	private static Connection conn;
	private static final Logger LOGGER = Logger.getLogger(DBClient.class.getName());
	
	private static String db_host = "jdbc:derby:C:\\Users\\rdevi\\MyDB;create=true";
	private static String username = "admin";
	private static String password = "admin";
	
	public DBClient(Properties prop) throws APIException {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");						
//			db_host = prop.getProperty("db_host");
//			username = prop.getProperty("username");
//			password = prop.getProperty("password");
			conn = DriverManager.getConnection(db_host, username, password);						
		} 
		catch (SQLException | ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}

	@Override
	public ResultSet executeStatement(PreparedStatement stmnt) throws APIException {		
		try {
			if(stmnt == null) { throw new NullPointerException(); }			
			ResultSet rs = stmnt.executeQuery();
			return rs;			
		} 
		catch (SQLException sqlEx) {
			LOGGER.log(Level.SEVERE, sqlEx.getMessage());
			throw new APIException(500, sqlEx.getMessage());
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}
	
	@Override
	public Connection getClient() throws APIException {	
		if(conn == null) {
			LOGGER.log(Level.WARNING, "NullPointerException: Error while getting DBClient");
			throw new APIException(500, "NullPointerException: Error while getting DBClient");
		}			
		return conn; 
	}

	@Override
	public void closeClient() throws APIException {
		try { conn.close(); } 
		catch (SQLException sqlEx) {
			LOGGER.log(Level.SEVERE, sqlEx.getMessage());
			throw new APIException(500, sqlEx.getMessage());
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			throw new APIException(500, e.getMessage());
		}
	}
}