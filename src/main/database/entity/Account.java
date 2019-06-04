package database.entity;

/**
 * Account
 *
 * @author Xin Yifei
 * @version 1.0
 */
public class Account {

    private String userID;
    private String username;
    private String email;
    private boolean fine;

    public Account() {
    }

    public Account(String userID) {
        this.userID = userID;
    }

    public Account(String userID, String username, String email) {
        this.userID = userID;
        this.username = username;
        this.email = email;
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
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param fine the fine to set
     */
    public void setFine(boolean fine) {
        this.fine = fine;
    }

    /**
     * @return the fine
     */
    public boolean isFine() {
        return fine;
    }

    @Override
    public String toString() {
        username = username.replaceAll(" ", ";");
        return userID + " " + username + " " + email + " " + fine;
    }
}
