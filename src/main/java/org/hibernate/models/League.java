package org.hibernate.models;

import java.util.ArrayList;

public class League {
  
    private String leagueID;
    private String leagueName;
    private double totalValue;
    private ArrayList<User> userList;
    private ArrayList<User> adminList;
    private int difficulty;

	private boolean privateMode;
    
    public League() {}
    
    public League(String leagueID, String leagueName, boolean privateMode, int difficulty) {
        this.leagueID = leagueID;
        this.leagueName = leagueName;
        this.totalValue = 0.0;
        this.userList = new ArrayList<User>();
        this.adminList = new ArrayList<User>();
        this.privateMode = privateMode;
        this.difficulty = difficulty;
    }
    
    public League(String leagueID, String leagueName, User owner, boolean privateMode, int difficulty) {
        this.leagueID = leagueID;
        this.leagueName = leagueName;
        this.totalValue = 0.0;
        this.userList = new ArrayList<User>();
        this.adminList = new ArrayList<User>();
        this.adminList.add(owner); //adds league creator to admin list
        this.privateMode = privateMode;
        this.difficulty = difficulty;
    }
    
    public League(String leagueID, String leagueName, double leagueValue, boolean privateMode,
            ArrayList<User> userList, ArrayList<User> adminList, int difficulty) {
        this.leagueID = leagueID;
        this.leagueName = leagueName;
        this.totalValue = 0.0;
        this.totalValue = leagueValue;
        this.userList = userList;
        this.adminList = adminList;
        this.privateMode = privateMode;
        this.difficulty = difficulty;
    }

    public String getLeagueID() { return leagueID; }
    public void setLeagueID(String leagueID) { this.leagueID = leagueID; }
    public String getLeagueName() { return leagueName; }
    public void setLeagueName(String leagueName) { this.leagueName = leagueName; }
    public double getLeagueValue() { return totalValue; }
    public void setLeagueValue(double leagueValue) { this.totalValue = leagueValue; }
    public ArrayList<User> getUserList() { return userList; }
    public void setUserList(ArrayList<User> userList) { this.userList = userList; }
    public ArrayList<User> getAdminList() { return adminList; }
    public void setAdminList(ArrayList<User> adminList) { this.adminList = adminList; }
    public boolean isPrivateMode() { return privateMode; }
    void setPrivateMode(boolean privateMode) { this.privateMode = privateMode; }
    public double getTotalValue() { return totalValue; }
	public void setTotalValue(double totalValue) { this.totalValue = totalValue; }
	public int getDifficulty() { return difficulty; }
	public void setDifficulty(int difficulty) { this.difficulty = difficulty; }
}
