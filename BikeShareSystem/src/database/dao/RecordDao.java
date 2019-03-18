package database.dao;

import java.io.*;
import java.util.*;

import database.entity.*;

/**
 * RecordDao
 * 
 * @author Xin Yifei
 * @version 0.6
 */
public interface RecordDao {
    public List<Record> findRecordAll(String userID) throws IOException;

    public List<Record> findRecordNotend(String userID) throws IOException;

    public List<Record> findRecordOverdue(String userID) throws IOException;

    public boolean addNewRecord(Record record) throws IOException;

    public boolean modifyRecord(Record recordModified) throws IOException;

}