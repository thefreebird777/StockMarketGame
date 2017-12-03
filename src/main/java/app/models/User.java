package app.models;

public class User implements DefaultInterface {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String leagueID;
    private String acctID;
    private double funds;
    private int userStatus;
    
    public User() {}
    
    public User(String email, String password, String firstName, String lastName,
            String acctID) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.leagueID = "";
        this.acctID = acctID;
        this.funds = 0.0;
        this.userStatus = 0;
    }
    
    public User(String email, String password, String firstName, String lastName, 
            String leagueID, String acctID, double funds, int userStatus) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.leagueID = leagueID;
        this.acctID = acctID;
        this.funds = funds;
        this.userStatus = userStatus;
    }
    
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
    public String getAccountID() { return acctID; }
    public void setAccountID(String accountID) { this.acctID = accountID; }
    public double getFunds() { return funds; }
    public void setFunds(double funds) { this.funds = funds; }    
    public int getUserStatus() { return userStatus; }
    public void setUserStatus(int userStatus) { this.userStatus = userStatus; }
}
