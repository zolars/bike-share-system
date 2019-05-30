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

    /**
    * <p>Description: Generate records of all users' usages and judge if the users will get a fine. If a user will get a fine, a specific attribute of its account will be changed.
    * @param resultStr Users' information
    * @return Generated records
    * @throws IOException Input and output exception
    */
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
            result.add(record);
        }

        for (int i = result.size() - 1; i >= 0; i--) {
            long duration = result.get(i).getDuration();
            if (duration > Main.overdueTime_Once) {
                result.get(i).setFine(true);
            } else {
                for (int j = i - 1; j >= 0; j--) {
                    duration += result.get(j).getDuration();
                    if (duration > Main.overdueTime_All
                            && (result.get(i).getEndDate().getTime() - result.get(j).getStartDate().getTime()) / 1000
                                    / 60 / 60 < 24) {
                        result.get(i).setFine(true);
                        break;
                    } else {
                        result.get(i).setFine(false);
                    }
                }
            }
            if (result.get(i).isFine() && result.get(i).getStartDate().equals(result.get(i).getEndDate())) {
                AccountDao accountDao = new AccountDaoImpl();
                Account account = accountDao.findAccountByUserID(result.get(i).getUserID());
                account.setFine(true);
                accountDao.modifyAccount(account);
            }
        }
        return result;
    }

    /**
    * <p>Description: Get all records according to the existing accounts stored in database.</p>
    * @return Records of all accounts
    * @throws IOException Input and output exception
    */
    @Override
    public List<Record> findRecordAll() throws IOException {
        List<Record> result = new ArrayList<Record>();
        AccountDao accountDao = new AccountDaoImpl();

        for (Account account : accountDao.findAccountAll()) {
            List<Record> tempResult = new ArrayList<Record>();

            tempResult = generatRecords(BaseDao.search("record.txt", account.getUserID(), 1));

            for (Record record : tempResult) {
                result.add(record);
            }
        }
        return result;
    }

    /**
    * <p>Description: Get certain records in accordance with a user ID.</p>
    * @param userID A user's ID number
    * @return Records of a certain account
    * @throws IOException Input and output exception
    */
    @Override
    public List<Record> findRecordAll(String userID) throws IOException {
        return generatRecords(BaseDao.search("record.txt", userID, 1));
    }

    /**
    * <p>Description: Find uncompleted records according to a certain user ID</p>
    * @param userID A user's ID number
    * @return Uncompleted records of a certain account
    * @throws IOException Input and output exception
    */
    @Override
    public List<Record> findRecordNotend(String userID) throws IOException {
        List<Record> result = new ArrayList<Record>();
        List<Record> tempResult = new ArrayList<Record>();

        RecordDao dao = new RecordDaoImpl();
        tempResult = dao.findRecordAll(userID);

        for (Record record : tempResult) {
            if (record.getStartDate().equals(record.getEndDate()))
                result.add(record);
        }
        return result;
    }

    /**
    * <p>Description: Find all overdue records</p>
    * @return Overdue records of all account
    * @throws IOException Input and output exception
    */
    @Override
    public List<Record> findRecordOverdue() throws IOException {
        List<Record> result = new ArrayList<Record>();
        AccountDao accountDao = new AccountDaoImpl();

        for (Account account : accountDao.findAccountAll()) {
            List<Record> tempResult = new ArrayList<Record>();

            RecordDao dao = new RecordDaoImpl();
            tempResult = dao.findRecordNotend(account.getUserID());

            for (Record record : tempResult) {
                if (record.isFine())
                    result.add(record);
            }
        }
        return result;
    }

    /**
    * <p>Description: Find overdue records according to a certain user ID</p>
    * @param userID A user's ID number
    * @return Overdue records of a certain account
    * @throws IOException Input and output exception
    */
    @Override
    public List<Record> findRecordOverdue(String userID) throws IOException {
        List<Record> result = new ArrayList<Record>();
        List<Record> tempResult = new ArrayList<Record>();

        RecordDao dao = new RecordDaoImpl();
        tempResult = dao.findRecordNotend(userID);

        for (Record record : tempResult) {
            if (record.isFine())
                result.add(record);
        }
        return result;
    }

    /**
    * <p>Description: Add a new borrowing record using a user ID and the start time</p>
    * @param userID A user's ID number
    * @param startDate The start time
    * @throws IOException Input and output exception
    */
    @Override
    public void addNewBorrow(String userID, Date startDate) throws IOException {
        int recordID = BaseDao.dataAmount("record.txt", "", 0) + 1;
        String str = recordID + " " + userID + " " + sf.format(startDate) + " null";
        BaseDao.addLine("record.txt", str);
    }

    /**
    * <p>Description: Add a new returning record using a user ID</p>
    * @param userID A user's ID number
    * @return If add a record successfully, return true. If not, return false.
    * @throws IOException Input and output exception
    */
    @Override
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

    /**
    * <p>Description: If a user with a certain ID number has ridden for two hours in a single day. Calculate the time when a user can utilize a bike again</p>
    * @param userID A user's ID number
    * @return If the user is run out off the time, return the time when it can ride again.
    * @throws IOException Input and output exception
    */
    @Override
    public Date isUserForbidden(String userID) throws IOException {
        List<Record> tempResult = new ArrayList<Record>();
        long duration = 0;

        RecordDao dao = new RecordDaoImpl();
        tempResult = dao.findRecordAll(userID);

        for (int i = tempResult.size() - 1; i >= 0; i--) {
            if ((new Date().getTime() - tempResult.get(i).getStartDate().getTime()) / 1000 / 60 / 60 < 24) {
                duration += tempResult.get(i).getDuration();
            }
            if (duration > Main.overdueTime_All)
                return new Date(tempResult.get(i).getStartDate().getTime() + 24 * 60 * 60 * 1000);
        }
        return new Date(0);
    }

    /**
    * <p>Description: A main method.</p>
    * @param args Default
    */
    public static void main(String[] args) {
        RecordDao dao = new RecordDaoImpl();
        try {
            for (Record record : dao.findRecordAll()) {
                System.out.println(record);
                System.out.println(record.isFine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}