package database.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Record
 *
 * @author Xin Yifei
 * @version 1.0
 */
public class Record {

    private int recordID;
    private String userID;
    private Date startDate;
    private Date endDate;
    private long duration;
    private boolean fine;

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
     * @param fine the fine to set
     */
    public void setFine(boolean fine) {
        this.fine = fine;
    }

    /**
     * @return the fine
     */
    public boolean isFine() {
        return fine;
    }

    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd;HH:mm:ss");
        if (startDate.equals(endDate)) {
            return recordID + " " + userID + " " + sf.format(startDate) + " null";
        } else {
            return recordID + " " + userID + " " + sf.format(startDate) + " " + sf.format(endDate);
        }
    }

    public String toDetail() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (startDate.equals(endDate)) {
            return sf.format(startDate) + ";" + "Not end" + ";" + userID + ";" + duration + ";"
                    + fine;
        } else {
            return sf.format(startDate) + ";" + sf.format(endDate) + ";" + userID + ";" + duration
                    + ";" + fine;
        }
    }
}
