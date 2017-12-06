package app.models;

public class User implements DefaultInterface {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private League league;
    private Account acct;
    private double funds;
    private int userStatus;
    
    public User() {}
    
    public User(String email, String password, String firstName, String lastName,
            Account acct) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.league = null;
        this.acct = acct;
        this.funds = 0.0;
        this.userStatus = 0;
    }
    
    public User(String email, String password, String firstName, String lastName, 
            League league, Account acct, double funds, int userStatus) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.league = league;
        this.acct = acct;
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
    public League getLeague() { return league; }
    public void setLeague(League league) { this.league = league; }
    public Account getAccount() { return acct; }
    public void setAccount(Account acct) { this.acct = acct; }
    public double getFunds() { return funds; }
    public void setFunds(double funds) { this.funds = funds; }    
    public int getUserStatus() { return userStatus; }
    public void setUserStatus(int userStatus) { this.userStatus = userStatus; }
}
