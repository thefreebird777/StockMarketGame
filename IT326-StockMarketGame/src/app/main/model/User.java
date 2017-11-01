package app.main.model;

import java.util.HashMap;

public class User {
	
	private String email;
	private String firstName;
	private String lastName;
	private HashMap<String, League> leagueList = null;
	private HashMap<String, Account> acctList = null;;
	boolean isAdmin;
	
	public User() {
		this.isAdmin = false;
	}
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }	
	public HashMap<String, League> getLeagueList() { return leagueList; }
	public void setLeagueList(HashMap<String, League> leagueList) { this.leagueList = leagueList;	}
	public HashMap<String, Account> getAcctList() { return acctList; }
	public void setAcctList(HashMap<String, Account> acctList) { this.acctList = acctList; }
	public boolean isAdmin() { return isAdmin; }
	public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }
}