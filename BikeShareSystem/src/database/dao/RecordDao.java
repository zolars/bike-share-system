package database.dao;

import java.io.*;

import database.entity.*;

/**
 * RecordDao
 * 
 * @author Xin Yifei
 * @version 0.6
 */
public interface RecordDao {
    public Record findRecordByUserID(String userID) throws IOException;

    public boolean addNewRecord(Record Record) throws IOException;

    public boolean modifyRecord(Record RecordModified) throws IOException;
}