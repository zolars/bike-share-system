package database.entity;

/**
 * Account
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class Account {
    private String userID;
    private String username;
    private String password;
    private int balance;

    public Account() {

    }

    public Account(String userID) {
        this.userID = userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * @return the balance
     */
    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return userID + " " + username + " " + password + " " + balance;
    }

}