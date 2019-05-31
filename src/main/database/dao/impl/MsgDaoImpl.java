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
 * @version 1.0
 */
public class MsgDaoImpl extends BaseDao implements MsgDao {

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd;HH:mm:ss");

    /**
     * <p>Description: Get messages according to a certain user ID.</p>
     * @param userID A user's ID number
     * @return Messages of a certain account
     * @throws IOException Input and output exception
     */
    public List<Msg> findMsgAll(String userID) throws IOException {
        return new ArrayList<Msg>();
    }

    /**
     * <p>Description: Find messages reminding an overdue event according to a certain user ID</p>
     * @param userID A user's ID number
     * @return Overdue messages of a certain account
     * @throws IOException Input and output exception
     */
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

    /**
     * <p>Description: Find messages with other types according to a certain user ID</p>
     * @param userID A user's ID number
     * @return Messages with other types
     * @throws IOException Input and output exception
     */
    public List<Msg> findMsgOther(String userID) throws IOException {
        List<Msg> result = new ArrayList<Msg>();
        List<String[]> resultStr = BaseDao.search("msg.txt", userID, 1);
        for (String[] data : resultStr) {
            if (!data[3].equals("overdue"))
                try {
                    String msgText = new String();
                    for (int i = 3; i < data.length; i++) {
                        msgText += data[i] + " ";
                        if (data[i].charAt(data[i].length() - 1) == '.') {
                            msgText += "\n";
                        }
                    }
                    result.add(new Msg(Integer.parseInt(data[0]), data[1], sf.parse(data[2]), msgText));
                } catch (NumberFormatException | ParseException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }

    /**
     * <p>Description: Add an message reminding an overdue event</p>
     * @param overdueRecord An object of Record that is overdue
     * @throws IOException Input and output exception
     */
    public void addOverdueMsg(Record overdueRecord) throws IOException {
        boolean overdueMarkexists = false;
        for (String[] result : BaseDao.search("msg.txt", overdueRecord.getUserID(), 1))
            if (result[3].equals("overdue")) {
                overdueMarkexists = true;
            }
        if (!overdueMarkexists) {
            Date overdueDate = overdueRecord.getStartDate();
            overdueDate.setTime(overdueDate.getTime() + Main.overdueTime_Once * 60 * 1000);
            Msg msg = new Msg(BaseDao.dataAmount("msg.txt", "", 0) + 1, overdueRecord.getUserID(), overdueDate,
                    "overdue");
            BaseDao.addLine("msg.txt", msg.toString());
        }
    }

    /**
     * <p>Description: Add an message with other types</p>
     * @param userID A user's ID number
     * @param text The content of the message
     * @return If succeed, return true. If not, return false.
     * @throws IOException Input and output exception
     */
    public boolean addOtherMsg(String userID, String text) throws IOException {
        if (BaseDao.search("account.txt", userID, 0).size() > 0) {
            Msg msg = new Msg(BaseDao.dataAmount("msg.txt", "", 0) + 1, userID, new Date(), text);
            BaseDao.addLine("msg.txt", msg.toString());
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p>Description: Delete a message according to a message ID</p>
     * @param msgID An ID number of a message
     * @throws IOException Input and output exception
     */
    public void deleteMsg(String msgID) throws IOException {
        BaseDao.replace("msg.txt", msgID, 0, "");
    }

    /**
     * <p>Description: A main method.</p>
     * @param args Default
     */
    public static void main(String[] args) {
        MsgDao dao = new MsgDaoImpl();
        try {
            dao.addOtherMsg("123", "LALALALAL");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}