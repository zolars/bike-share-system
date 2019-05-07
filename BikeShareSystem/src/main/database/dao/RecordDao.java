package database.dao;

import java.io.*;
import java.util.*;

import database.entity.*;

/**
 * RecordDao
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public interface RecordDao {
    public List<Record> findRecordAll() throws IOException;

    public List<Record> findRecordAll(String userID) throws IOException;

    public List<Record> findRecordNotend(String userID) throws IOException;

    public List<Record> findRecordOverdue() throws IOException;

    public List<Record> findRecordOverdue(String userID) throws IOException;

    public void addNewBorrow(String userID, Date startDate) throws IOException;

    public boolean addNewReturn(String userID) throws IOException;

    public Date isUserForbidden(String userID) throws IOException;

}