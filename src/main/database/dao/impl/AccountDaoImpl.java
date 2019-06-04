package database.dao.impl;

import database.BaseDao;
import database.dao.AccountDao;
import database.entity.Account;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Impl
 *
 * @author Xin Yifei
 * @version 1.0
 */
public class AccountDaoImpl implements AccountDao {

    /**
     * <p>
     * Description: Get all accounts stored in database.
     * </p>
     *
     * @return All accounts
     * @throws IOException Input and output exception
     */
    public List<Account> findAccountAll() throws IOException {
        List<Account> result = new ArrayList<Account>();

        for (String[] resultStr : BaseDao.search("account.txt", "", 0)) {
            result.add(new Account(resultStr[0], resultStr[1], resultStr[2]));
        }
        return result;
    }

    /**
     * <p>
     * Description: Get certain account in accordance with a user ID.
     * </p>
     *
     * @param userID A user's ID number
     * @return An account with a certain ID number
     * @throws IOException Input and output exception
     */
    public Account findAccountByUserID(String userID) throws IOException {
        Account result = new Account();
        if (BaseDao.search("account.txt", userID, 0).size() == 0) {
            return null;
        } else {
            String[] resultStr = BaseDao.search("account.txt", userID, 0).get(0);
            result.setUserID(resultStr[0]);
            resultStr[1] = resultStr[1].replaceAll(";", " ");
            result.setUsername(resultStr[1]);
            result.setEmail(resultStr[2]);
            result.setFine(Boolean.parseBoolean(resultStr[3]));
            return result;
        }
    }

    /**
     * <p>
     * Description: Get certain account in accordance with a user's name.
     * </p>
     *
     * @param username A user's name
     * @return An account with a certain name
     * @throws IOException Input and output exception
     */
    public Account findAccountByUsername(String username) throws IOException {
        Account result = new Account();
        if (BaseDao.search("account.txt", username, 1).size() == 0) {
            return null;
        } else {
            String[] resultStr = BaseDao.search("account.txt", username, 1).get(0);
            result.setUserID(resultStr[0]);
            resultStr[1] = resultStr[1].replaceAll(";", " ");
            result.setUsername(resultStr[1]);
            result.setEmail(resultStr[2]);
            result.setFine(Boolean.parseBoolean(resultStr[3]));

            return result;
        }
    }

    /**
     * <p>
     * Description: Add a new account
     * </p>
     *
     * @param account An object of Account
     * @return If succeed, return true. If not, return false.
     * @throws IOException Input and output exception
     */
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

    /**
     * <p>
     * Description: Update the information about an account stored in database
     * </p>
     *
     * @param accountModified An object of Account that will be modified
     * @return If succeed, return true. If not, return false.
     * @throws IOException Input and output exception
     */
    public boolean modifyAccount(Account accountModified) throws IOException {
        if (BaseDao.search("account.txt", accountModified.getUserID(), 0).size() == 1
                && BaseDao.search("account.txt", accountModified.getUsername(), 1).size() == 1) {
            BaseDao.replace("account.txt", accountModified.getUserID(), 0,
                    accountModified.toString());
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p>
     * Description: Delete an account stored in database
     * </p>
     *
     * @param account An object of Account that will be deleted
     * @throws IOException Input and output exception
     */
    public void deleteAccount(Account account) throws IOException {
        BaseDao.replace("account.txt", account.getUserID(), 0, "");
    }

    /**
     * <p>
     * Description: A main method.
     * </p>
     *
     * @param args Default
     */
    public static void main(String[] args) {
        AccountDao dao = new AccountDaoImpl();
        try {
            System.out.println(dao.findAccountByUserID("12345"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
