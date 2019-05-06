package database.entity;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

/**
 * Record
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class Record {
    private int recordID;
    private String userID;
    private Date startDate;
    private Date endDate;
    private long duration;
    private int bill;

    /**
     * @param recordID the recordID to set
     */
    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    /**
     * @return the recordID
     */
    public int getRecordID() {
        return recordID;
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
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * @return the duration
     */
    public long getDuration() {
        return duration;
    }

    /**
     * @param bill the bill to set
     */
    public void setBill(int bill) {
        this.bill = bill;
    }

    /**
     * @return the bill
     */
    public int getBill() {
        return bill;
    }

    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd;HH:mm:ss");
        String endDateStr;
        if (startDate.equals(endDate))
            endDateStr = " null";
        else
            endDateStr = sf.format(endDate);
        return recordID + " " + userID + " " + sf.format(startDate) + endDateStr;
    }

    public String[] toStringList() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String endDateStr;
        if (startDate.equals(endDate)) {
            endDateStr = "Not end";
            duration = ((new Date()).getTime() - startDate.getTime()) / (1000 * 60);
        } else {
            endDateStr = sf.format(endDate);
            duration = (endDate.getTime() - startDate.getTime()) / (1000 * 60);
        }
        return new String[] { sf.format(startDate), endDateStr, String.valueOf(duration) + "min",
                String.valueOf(bill) + " dollars" };
    }
}