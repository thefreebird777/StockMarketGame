package app.main.authentication;

import app.main.dao.DataAccess;
import app.main.exceptions.APIException;

public class Authentication {

	private static DataAccess dao;
	
	public Authentication() throws APIException {
		dao = new DataAccess();
	}
	
	public synchronized static String verifyUser(String email, String text) throws APIException {
		String password = dao.fetchUser(email).getPassword();		
		
		if(password.equals(text)) { 
			return generateJWT(); 
		} else {
			throw new APIException(403, "Error: User access denied - authentication failed");	
		}					
	}
	
	private static String generateJWT() {
		
		return "";
	}
}