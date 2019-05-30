package database.dao;

import java.io.*;
import java.util.*;

import database.entity.*;

/**
 * AccountDao
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public interface AccountDao {
	
	/**
	* <p>Description: Get all accounts stored in database.</p>
	* @return All accounts
	* @throws IOException Input and output exception
	*/
    public List<Account> findAccountAll() throws IOException;

    /**
     * <p>Description: Get certain account in accordance with a user ID.</p>
     * @param userID A user's ID number
     * @return An account with a certain ID number
     * @throws IOException Input and output exception
     */
    public Account findAccountByUserID(String userID) throws IOException;

    /**
     * <p>Description: Get certain account in accordance with a user's name.</p>
     * @param username A user's name
     * @return An account with a certain name
     * @throws IOException Input and output exception
     */
    public Account findAccountByUsername(String username) throws IOException;

    /**
     * <p>Description: Add a new account</p>
     * @param account An object of Account
     * @return If succeed, return true. If not, return false.
     * @throws IOException Input and output exception
     */
    public boolean addNewAccount(Account account) throws IOException;

    /**
     * <p>Description: Update the information about an account stored in database</p>
     * @param accountModified An object of Account that will be modified
     * @return If succeed, return true. If not, return false.
     * @throws IOException Input and output exception
     */
    public boolean modifyAccount(Account accountModified) throws IOException;

    /**
     * <p>Description: Delete an account stored in database</p>
     * @param account An object of Account that will be deleted
     * @throws IOException Input and output exception
     */
    public void deleteAccount(Account account) throws IOException;
}