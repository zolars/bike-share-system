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
 * @version 0.8
 */
public class MsgDaoImpl extends BaseDao implements MsgDao {

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd;HH:mm:ss");

    public List<Msg> findMsgAll(String userID) throws IOException {
        return new ArrayList<Msg>();
    }

    public List<Msg> findMsgOverdue(String userID) throws IOException {
        List<Msg> result = new ArrayList<Msg>();
        List<String[]> resultStr = BaseDao.search("msg.txt", userID, 1);
        for (String[] data : resultStr) {
            if (data[3].equals("overdue"))
                try {
                    result.add(new Msg(Integer.parseInt(data[0]), data[1], sf.parse(data[2]), data[3]));
                } catch (NumberFormatException | ParseException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }

    public List<Msg> findMsgOther(String userID) throws IOException {
        List<Msg> result = new ArrayList<Msg>();
        List<String[]> resultStr = BaseDao.search("msg.txt", userID, 1);
        for (String[] data : resultStr) {
            if (!data[3].equals("overdue"))
                try {
                    result.add(new Msg(Integer.parseInt(data[0]), data[1], sf.parse(data[2]), data[3]));
                } catch (NumberFormatException | ParseException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }

    public void addOverdueMsg(Record overdueRecord) throws IOException {
        Boolean overdueMarkexists = false;
        for (String[] result : BaseDao.search("msg.txt", overdueRecord.getUserID(), 1))
            if (result[3].equals("overdue")) {
                overdueMarkexists = true;
            }
        if (!overdueMarkexists) {
            Date overdueDate = overdueRecord.getStartDate();
            overdueDate.setTime(overdueDate.getTime() + Main.overdueTime * 60 * 1000);
            Msg msg = new Msg(BaseDao.dataAmount("msg.txt", "", 0) + 1, overdueRecord.getUserID(), overdueDate,
                    "overdue");
            BaseDao.addLine("msg.txt", msg.toString());
        } else {

        }
    }

    public void addOtherMsg(String userID, String text) throws IOException {
        Msg msg = new Msg(BaseDao.dataAmount("msg.txt", "", 0) + 1, userID, new Date(), text);
        BaseDao.addLine("msg.txt", msg.toString());
    }

    public boolean deleteMsg(String userID) throws IOException {
        return false;
    }

    public static void main(String[] args) {
        MsgDao dao = new MsgDaoImpl();
        try {
            dao.addOtherMsg("123", "LALALALAL");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}