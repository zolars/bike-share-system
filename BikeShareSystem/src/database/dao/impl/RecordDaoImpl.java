package database.dao.impl;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import application.*;
import database.*;
import database.dao.*;
import database.entity.*;

/**
 * Impl
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class RecordDaoImpl extends BaseDao implements RecordDao {

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd;HH:mm:ss");

    public List<Record> generatRecords(List<String[]> resultStr) throws IOException {
        List<Record> result = new ArrayList<Record>();

        for (String[] data : resultStr) {
            Record record = new Record();

            record.setRecordID(Integer.parseInt(data[0]));
            record.setUserID(data[1]);

            try {
                record.setStartDate(sf.parse(data[2]));
                if (data[3].equals("null")) {
                    record.setEndDate(sf.parse(data[2]));
                } else {
                    record.setEndDate(sf.parse(data[3]));
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
            if (duration <= Main.overdueTime)
                record.setBill(0);
            else
                record.setBill((int) (Math.ceil(duration / 60) - 2) * 1);

            result.add(record);
        }
        return result;
    }

    public List<Record> findRecordAll(String userID) throws IOException {
        return generatRecords(BaseDao.search("record.txt", userID, 1));
    }

    public List<Record> findRecordNotend(String userID) throws IOException {
        List<Record> result = new ArrayList<Record>();
        for (Record record : generatRecords(BaseDao.search("record.txt", userID, 1))) {
            if (record.getStartDate().equals(record.getEndDate()))
                result.add(record);
        }
        return result;
    }

    public List<Record> findRecordOverdue(String userID) throws IOException {
        List<Record> result = new ArrayList<Record>();
        for (Record record : generatRecords(BaseDao.search("record.txt", userID, 1))) {
            if (record.getStartDate().equals(record.getEndDate())
                    && ((new Date()).getTime() - record.getStartDate().getTime()) / (1000 * 60) > Main.overdueTime) {
                result.add(record);
            }
        }
        return result;
    }

    public void addNewBorrow(String userID, Date startDate) throws IOException {
        int recordID = BaseDao.dataAmount("record.txt", "", 0) + 1;
        String str = recordID + " " + userID + " " + sf.format(startDate) + " null";
        BaseDao.addLine("record.txt", str);
    }

    public boolean addNewReturn(String userID) throws IOException {
        List<Record> result = findRecordNotend(userID);
        if (result.size() > 1 || result.size() == 0) {
            System.out.println("There are something wrong? Please contact the Administer.");
            return false;
        } else {
            result.get(0).setEndDate(new Date());
            BaseDao.replace("record.txt", result.get(0).getRecordID() + "", 0, result.get(0).toString());
            return true;
        }
    }

    public static void main(String[] args) {
        RecordDao dao = new RecordDaoImpl();
        try {
            for (int i = 0; i < dao.findRecordOverdue("").size(); i++)
                System.out.println(dao.findRecordOverdue("").get(i).getBill());
            dao.addNewBorrow("123", new Date());

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}