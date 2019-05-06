package database.dao.impl;

import java.io.*;
import java.util.*;

import database.*;
import database.dao.*;
import database.entity.*;

/**
 * Impl
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class AccountDaoImpl implements AccountDao {

    public List<Account> findAccountAll() throws IOException {
        List<Account> result = new ArrayList<Account>();

        for (String[] resultStr : BaseDao.search("account.txt", "", 0)) {
            result.add(new Account(resultStr[0], resultStr[1], resultStr[2]));
        }
        return result;
    }

    public Account findAccountByUserID(String userID) throws IOException {
        Account result = new Account();
        if (BaseDao.search("account.txt", userID, 0).size() == 0) {
            return null;
        } else {
            String[] resultStr = BaseDao.search("account.txt", userID, 0).get(0);
            result.setUserID(resultStr[0]);
            result.setUsername(resultStr[1]);
            result.setEmail(resultStr[2]);
            result.setFine(Boolean.parseBoolean(resultStr[3]));
            return result;
        }
    }

    public Account findAccountByUsername(String username) throws IOException {
        Account result = new Account();
        if (BaseDao.search("account.txt", username, 1).size() == 0) {
            return null;
        } else {
            String[] resultStr = BaseDao.search("account.txt", username, 1).get(0);
            result.setUserID(resultStr[0]);
            result.setUsername(resultStr[1]);
            result.setEmail(resultStr[2]);
            result.setFine(Boolean.parseBoolean(resultStr[3]));

            return result;
        }
    }

    public boolean addNewAccount(Account account) throws IOException {
        if (BaseDao.search("account.txt", account.getUserID(), 0).size() == 0
                && BaseDao.search("account.txt", account.getUsername(), 1).size() == 0) {
            String str = account.toString();
            BaseDao.addLine("account.txt", str);
            return true;
        } else {
            return false;
        }
    }

    public boolean modifyAccount(Account accountModified) throws IOException {
        if (BaseDao.search("account.txt", accountModified.getUserID(), 0).size() == 1
                && BaseDao.search("account.txt", accountModified.getUsername(), 1).size() == 1) {
            BaseDao.replace("account.txt", accountModified.getUserID(), 0, accountModified.toString());
            return true;
        } else {
            return false;
        }
    }

    public void deleteAccount(Account account) throws IOException {
        BaseDao.replace("account.txt", account.getUserID(), 0, "");
    }
}