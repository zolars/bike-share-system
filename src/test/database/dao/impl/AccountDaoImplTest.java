package database.dao.impl;

import database.dao.AccountDao;
import database.entity.Account;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountDaoImplTest {

    @Test
    void findAccountAll() {
        AccountDao dao = new AccountDaoImpl();
        try {
            System.out.println(dao.findAccountAll());
            assertEquals("admin xin qwe@qwe.qwe false", dao.findAccountAll().get(0).toString());
            assertEquals("123 qwe asd@qwe.com false", dao.findAccountAll().get(1).toString());
            assertEquals("234 wer 118169671@qq.com false", dao.findAccountAll().get(2).toString());
            assertEquals("345 ert WTF@wtf.wtf false", dao.findAccountAll().get(3).toString());
            assertEquals("qwer qwer zolars@outlook.com false", dao.findAccountAll().get(4).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findAccountByUserID() {
        AccountDao dao = new AccountDaoImpl();
        try {
            assertEquals("admin admin admin@admin.admin false", dao.findAccountByUserID("admin").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findAccountByUsername() {
        AccountDao dao = new AccountDaoImpl();
        try {
            assertEquals("123 qwe asd@qwe.com false", dao.findAccountByUsername("qwe").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addNewAccount() {
        AccountDao dao = new AccountDaoImpl();
        try {
            assert (dao.addNewAccount(new Account("4", "qweeee", "zzz@zzz.com")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void modifyAccount() {
    }
}