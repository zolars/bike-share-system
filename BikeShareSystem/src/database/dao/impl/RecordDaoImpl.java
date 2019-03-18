package database.dao.impl;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import database.*;
import database.dao.*;
import database.entity.*;

/**
 * Impl
 * 
 * @author Xin Yifei
 * @version 0.6
 */
public class RecordDaoImpl extends BaseDao implements RecordDao {

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd;HH:mm:ss");

    public List<Record> generatRecords(List<String[]> resultStr) throws IOException {
        List<Record> result = new ArrayList<Record>();

        for (String[] data : resultStr) {
            Record record = new Record();

            record.setUserID(data[0]);

            try {
                record.setStartDate(sf.parse(data[1]));
                if (data[2].equals("null")) {
                    record.setEndDate(sf.parse(data[1]));
                } else {
                    record.setEndDate(sf.parse(data[2]));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Date startDate = record.getStartDate();
            Date endDate;
            if (record.getStartDate().equals(record.getEndDate())) {
                endDate = new Date();
            } else {
                endDate = record.getEndDate();
            }
            long duration = (endDate.getTime() - startDate.getTime()) / (1000 * 60);
            record.setDuration(duration);
            if (duration <= 120)
                record.setBill(0);
            else
                record.setBill((int) (Math.ceil(duration / 60) - 2) * 1);

            result.add(record);
        }
        return result;
    }

    public List<Record> findRecordAll(String userID) throws IOException {
        return generatRecords(BaseDao.search("record.txt", userID, 0));
    }

    public List<Record> findRecordNotend(String userID) throws IOException {
        List<Record> result = new ArrayList<Record>();
        for (Record record : generatRecords(BaseDao.search("record.txt", userID, 0))) {
            if (record.getStartDate().equals(record.getEndDate()))
                result.add(record);
        }
        return result;
    }

    public List<Record> findRecordOverdue(String userID) throws IOException {
        List<Record> result = new ArrayList<Record>();
        for (Record record : generatRecords(BaseDao.search("record.txt", userID, 0))) {
            if (record.getStartDate().equals(record.getEndDate())
                    && ((new Date()).getTime() - record.getStartDate().getTime()) / (1000 * 60) > 120) {
                result.add(record);
            }
        }
        return result;
    }

    public boolean addNewRecord(Record record) throws IOException {
        if (BaseDao.search("Record.txt", record.getUserID(), 0).size() == 0
                && BaseDao.search("Record.txt", record.getUserID(), 1).size() == 0) {
            String str = record.toString();
            BaseDao.addLine("Record.txt", str);
            return true;
        } else {
            return false;
        }
    }

    public boolean modifyRecord(Record recordModified) throws IOException {
        if (BaseDao.search("Record.txt", recordModified.getUserID(), 0).size() == 1) {
            BaseDao.replace("Record.txt", recordModified.getUserID(), 0, recordModified.toString());
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        RecordDao dao = new RecordDaoImpl();
        try {
            for (int i = 0; i < dao.findRecordNotend("123").size(); i++)
                System.out.println(dao.findRecordNotend("123").get(i).getBill());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}