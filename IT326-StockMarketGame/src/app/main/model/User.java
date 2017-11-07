package app.main.model;

public class User {
	
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String leagueID;
	private String accountID;
	private double funds;
	
	public User() {	}
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getLeagueID() { return leagueID; }
	public void setLeagueID(String leagueID) { this.leagueID = leagueID; }
	public String getAccountID() { return accountID; }
	public void setAccountID( String accountID) {this.accountID = accountID; }
	public double getFunds() { return funds; }
	public void setFunds(double funds) { this.funds = funds; }
}