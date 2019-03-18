package database.dao.impl;

import java.io.*;

import database.*;
import database.dao.*;
import database.entity.*;

/**
 * Impl
 * 
 * @author Xin Yifei
 * @version 0.6
 */
public class AccountDaoImpl extends BaseDao implements AccountDao {
    public Account findAccountByUserID(String userID) throws IOException {
        Account result = new Account();
        if (search("account.txt", userID, 0).size() == 0) {
            return null;
        } else {
            String[] resultStr = search("account.txt", userID, 0).get(0);
            result.setUserID(resultStr[0]);
            result.setUsername(resultStr[1]);
            result.setPassword(resultStr[2]);
            result.setBalance(Integer.parseInt(resultStr[3]));
            return result;
        }
    }

    public Account findAccountByUsername(String username) throws IOException {
        Account result = new Account();
        if (search("account.txt", username, 1).size() == 0) {
            return null;
        } else {
            String[] resultStr = search("account.txt", username, 1).get(0);
            result.setUserID(resultStr[0]);
            result.setUsername(resultStr[1]);
            result.setPassword(resultStr[2]);
            result.setBalance(Integer.parseInt(resultStr[3]));

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

    public static void main(String[] args) {
        AccountDao dao = new AccountDaoImpl();
        try {
            System.out.println(dao.findAccountByUsername("xin").getBalance());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}