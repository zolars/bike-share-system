package database.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import database.dao.AccountDao;
import database.entity.Account;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class AccountDaoImplTest {

    @Test
    void findAccountAll() {
        AccountDao dao = new AccountDaoImpl();
        try {
            System.out.println(dao.findAccountAll());
            assertEquals("admin admin admin false", dao.findAccountAll().get(0).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findAccountByUserID() {
        AccountDao dao = new AccountDaoImpl();
        try {
            assertEquals("admin admin admin false", dao.findAccountByUserID("admin").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findAccountByUsername() {
        AccountDao dao = new AccountDaoImpl();
        try {
            assertEquals("admin admin admin false", dao.findAccountByUsername("admin").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addNewAccount() {
        AccountDao dao = new AccountDaoImpl();
        try {
            Account newAccount = new Account("111111111", "newname", "qwe@qwe.com");

            assertEquals(true, dao.addNewAccount(newAccount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void modifyAccount() {
    }
}
