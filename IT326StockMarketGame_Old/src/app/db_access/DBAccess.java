package app.db_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import app.main.exceptions.APIException;

public interface DBAccess {
	public ResultSet executeStatement(PreparedStatement sql) throws APIException;
	public Connection getClient() throws APIException;
	public void closeClient() throws APIException;
}