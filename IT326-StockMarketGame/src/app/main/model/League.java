package app.main.model;


import java.util.HashMap;

public class League {
	
	private String leagueID;
	private String name;
	public HashMap<String, User> users;
	public HashMap<User, League> admins;
	
	public String getLeagueID() { return leagueID; }
	public void setLeagueID(String leagueID) { this.leagueID = leagueID; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public HashMap<String, User> getUsers() { return users; }
	public void setUsers(HashMap<String, User> users) { this.users = users; }
	public HashMap<User, League> getAdmins() { return admins; }
	public void setAdmins(HashMap<User, League> admins) {	this.admins = admins; }
}
