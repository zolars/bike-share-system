package database.entity;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Msg
 * 
 * @author Xin Yifei
 * @version 0.8
 */
public class Msg {
    private int msgID;
    private String userID;
    private Date date;
    private String text;

    public Msg() {
    }

    public Msg(int msgID, String userID, Date date, String text) {
        this.msgID = msgID;
        this.userID = userID;
        this.date = date;
        this.text = text;
    }

    /**
     * @param msgID the msgID to set
     */
    public void setMsgID(int msgID) {
        this.msgID = msgID;
    }

    /**
     * @return the msgID
     */
    public int getMsgID() {
        return msgID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd;HH:mm:ss");
        return msgID + " " + userID + " " + sf.format(date) + " " + text;
    }
}