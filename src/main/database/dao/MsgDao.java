package database.dao;

import database.entity.Msg;
import database.entity.Record;
import java.io.IOException;
import java.util.List;

/**
 * MsgDao
 *
 * @author Xin Yifei
 * @version 1.0
 */
public interface MsgDao {

    /**
     * <p>Description: Get messages according to a certain user ID.</p>
     *
     * @param userID A user's ID number
     * @return Messages of a certain account
     * @throws IOException Input and output exception
     */
    public List<Msg> findMsgAll(String userID) throws IOException;

    /**
     * <p>Description: Find messages reminding an overdue event according to a certain user ID</p>
     *
     * @param userID A user's ID number
     * @return Overdue messages of a certain account
     * @throws IOException Input and output exception
     */
    public List<Msg> findMsgOverdue(String userID) throws IOException;

    /**
     * <p>Description: Find messages with other types according to a certain user ID</p>
     *
     * @param userID A user's ID number
     * @return Messages with other types
     * @throws IOException Input and output exception
     */
    public List<Msg> findMsgOther(String userID) throws IOException;

    /**
     * <p>Description: Add an message reminding an overdue event</p>
     *
     * @param overdueRecord An object of Record that is overdue
     * @throws IOException Input and output exception
     */
    public void addOverdueMsg(Record overdueRecord) throws IOException;

    /**
     * <p>Description: Add an message with other types</p>
     *
     * @param userID A user's ID number
     * @param text The content of the message
     * @return If succeed, return true. If not, return false.
     * @throws IOException Input and output exception
     */
    public boolean addOtherMsg(String userID, String text) throws IOException;

    /**
     * <p>Description: Delete a message according to a message ID</p>
     *
     * @param msgID An ID number of a message
     * @throws IOException Input and output exception
     */
    public void deleteMsg(String msgID) throws IOException;
}
