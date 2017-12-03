package app.main.model;

import java.util.ArrayList;

public class League {
	
	private String leagueID;
	private String name;
	private ArrayList<String> userList;
	private ArrayList<String> adminList;
	
	public String getLeagueID() { return leagueID; }
	public void setLeagueID(String leagueID) { this.leagueID = leagueID; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public ArrayList<String> getUserList() { return userList; }
	public void setUserList(ArrayList<String> userList) { this.userList = userList; }
	public ArrayList<String> getAdminList() { return adminList;	}
	public void setAdminList(ArrayList<String> adminList) { this.adminList = adminList; }
}