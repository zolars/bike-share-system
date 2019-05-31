package database.dao;

import java.io.*;
import java.util.*;

import database.entity.*;

/**
 * RecordDao
 * 
 * @author Xin Yifei
 * @version 1.0
 */
public interface RecordDao {
	
	/**
	* <p>Description: Get all records according to the existing accounts stored in database.</p>
	* @return Records of all accounts
	* @throws IOException Input and output exception
	*/
    public List<Record> findRecordAll() throws IOException;

    /**
     * <p>Description: Get certain records in accordance with a user ID.</p>
     * @param userID A user's ID number
     * @return Records of a certain account
     * @throws IOException Input and output exception
     */
    public List<Record> findRecordAll(String userID) throws IOException;

    /**
     * <p>Description: Find uncompleted records according to a certain user ID</p>
     * @param userID A user's ID number
     * @return Uncompleted records of a certain account
     * @throws IOException Input and output exception
     */
    public List<Record> findRecordNotend(String userID) throws IOException;

    /**
     * <p>Description: Find all overdue records</p>
     * @return Overdue records of all account
     * @throws IOException Input and output exception
     */
    public List<Record> findRecordOverdue() throws IOException;

    /**
     * <p>Description: Find overdue records according to a certain user ID</p>
     * @param userID A user's ID number
     * @return Overdue records of a certain account
     * @throws IOException Input and output exception
     */
    public List<Record> findRecordOverdue(String userID) throws IOException;

    /**
     * <p>Description: Add a new borrowing record using a user ID and the start time</p>
     * @param userID A user's ID number
     * @param startDate The start time
     * @throws IOException Input and output exception
     */
    public void addNewBorrow(String userID, Date startDate) throws IOException;

    /**
     * <p>Description: Add a new returning record using a user ID</p>
     * @param userID A user's ID number
     * @return If add a record successfully, return true. If not, return false.
     * @throws IOException Input and output exception
     */
    public boolean addNewReturn(String userID) throws IOException;

    /**
     * <p>Description: If a user with a certain ID number has ridden for two hours in a single day. Calculate the time when a user can utilize a bike again</p>
     * @param userID A user's ID number
     * @return If the user is run out off the time, return the time when it can ride again.
     * @throws IOException Input and output exception
     */
    public Date isUserForbidden(String userID) throws IOException;

}