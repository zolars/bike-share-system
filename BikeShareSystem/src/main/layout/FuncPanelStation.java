package layout;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import application.*;
import database.dao.*;
import database.dao.impl.*;
import database.entity.*;

/**
 * FuncPanelStation
 * 
 * @author Xin Yifei
 * @version 0.9
 */
public class FuncPanelStation extends FuncPanelDefault implements ActionListener {
    private static final long serialVersionUID = 1L;

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private JLabel text1;
    private JLabel text2;
    private JTable table;
    private JButton btn;

    public FuncPanelStation() {
        super();
        setName("Station");
        setLayout(null);

        if (MainStation.station == null) {
            Bikes[] bikes = Main.bikeStationList;
            Object[] obj = new Object[bikes.length];
            for (int i = 0; i < bikes.length; i++) {
                obj[i] = bikes[i].getStation();
            }

            while (true) {
                String station = (String) JOptionPane.showInputDialog(null,
                        "Please choose the station name of this PC:\n", "Bike Share System - Station",
                        JOptionPane.PLAIN_MESSAGE, new ImageIcon(), obj, "");
                MainStation.station = station;
                if (MainStation.station == null)
                    continue;
                else
                    break;
            }
        }

        text1 = new JLabel("", JLabel.CENTER);
        text1.setFont(new java.awt.Font("Dialog", 1, 50));
        text1.setOpaque(false);
        text1.setForeground(Color.BLACK);
        text1.setText("Shared Bikes System");
        add(text1);

        text2 = new JLabel("", JLabel.CENTER);
        text2.setFont(new java.awt.Font("Dialog", 1, 20));
        text2.setOpaque(false);
        text2.setForeground(Color.BLACK);
        text2.setText("Please use your ID card to unlock the shared bikes");
        add(text2);

        btn = new JButton();
        btn.setText("OK");
        btn.addActionListener(this);
        add(btn);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                text1.setBounds(getBounds().width / 2 - text1.getText().length() * 35 / 2,
                        getBounds().height / 3 - 60 / 3, text1.getText().length() * 35, 60);
                text2.setBounds(getBounds().width / 2 - text2.getText().length() * 21 / 4,
                        getBounds().height / 3 - 30 / 3 + 130, text2.getText().length() * 11, 30);
                btn.setBounds(getBounds().width / 2 - 30, getBounds().height * 3 / 4 - 30 * 3 / 4, 60, 30);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {

        RecordDao recordDao = new RecordDaoImpl();
        AccountDao accountDao = new AccountDaoImpl();
        BikesDao bikesDao = new BikesDaoImpl();

        if (e.getSource() == btn) {
            try {
                while (true) {
                    String userID = JOptionPane.showInputDialog(null, "Input your ID number (not null) : \n",
                            "Check ID", JOptionPane.PLAIN_MESSAGE);
                    Date date = recordDao.isUserForbidden(userID);
                    Account account = accountDao.findAccountByUserID(userID);

                    if (userID.equals("")) {
                        continue;
                    } else if (accountDao.findAccountByUserID(userID) == null) {
                        JOptionPane.showMessageDialog(null, "Invalid ID card. Please contact the administer.", "Sorry",
                                JOptionPane.WARNING_MESSAGE);
                        break;
                    } else if (recordDao.findRecordNotend(userID).size() >= 1) {
                        returnBikes(userID);
                        break;
                    } else if (date.getTime() != 0) {
                        text1.setText("You overrun using time in 24h : (");
                        text2.setText("You can use the bikes until " + sf.format(date));
                        btn.setEnabled(false);
                        MainStation.restart = true;
                        break;
                    } else if (account.isFine()) {
                        Object[] choices = { "Pay", "Cancel" };
                        int choiceNum = (int) JOptionPane.showOptionDialog(null,
                                "Until you can use the bikes, you still have 100BCD as fine to pay.", "Payment",
                                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choices,
                                choices[0]);
                        if (choiceNum == 0) {
                            // Pay for the fine
                            account.setFine(false);
                            accountDao.modifyAccount(account);

                            // borrow bikes
                            bikesDao.changeBikesByStation(new Bikes(MainStation.station,
                                    bikesDao.findBikesNumberByStation(MainStation.station) - 1));
                            recordDao.addNewBorrow(userID, new Date());
                            text1.setText("Your bike is unlocked : )");
                            text2.setText("Remember giving the bike back at one of parking areas!");
                            btn.setEnabled(false);
                            MainStation.restart = true;
                            break;
                        } else {
                            text1.setText("Please pay for the fine first : (");
                            text2.setText("You can use the bikes until you have paid for the fine");
                            btn.setEnabled(false);
                            MainStation.restart = true;
                            break;
                        }
                    } else {
                        // borrow bikes
                        bikesDao.changeBikesByStation(new Bikes(MainStation.station,
                                bikesDao.findBikesNumberByStation(MainStation.station) - 1));
                        recordDao.addNewBorrow(userID, new Date());
                        text1.setText("Your bike is unlocked : )");
                        text2.setText("Remember giving the bike back at one of parking areas!");
                        btn.setEnabled(false);
                        MainStation.restart = true;
                        break;
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    private void returnBikes(String userID) {
        BikesDao bikesDao = new BikesDaoImpl();
        MsgDao msgDao = new MsgDaoImpl();
        RecordDao recordDao = new RecordDaoImpl();

        try {
            // change viewer
            text1.setText("Your bike is returned : )");
            text2.setText("If your bike needs to repair, please tell us!");
            btn.setEnabled(false);

            Msg overdueMsg = null;
            try {
                overdueMsg = msgDao.findMsgOverdue(userID).get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // delete overdue msg & check the bill
            if (overdueMsg != null) {
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Record record = recordDao.findRecordOverdue(userID).get(0);

                String startDateStr = sf.format(record.getStartDate());
                String endDateStr = sf.format(new Date());

                msgDao.deleteMsg(String.valueOf(msgDao.findMsgOverdue(userID).get(0).getMsgID()));
                msgDao.addOtherMsg(userID, "Your ride from " + startDateStr + " to " + endDateStr
                        + " costs 100BCD. * Attention : The scooter must be returned within 30 minutes"
                        + " and the total usage must not exceed 2 hours a day, otherwise a fine should be issued.");
            }

            // change bikes num
            bikesDao.changeBikesByStation(
                    new Bikes(MainStation.station, bikesDao.findBikesNumberByStation(MainStation.station) + 1));
            recordDao.addNewReturn(userID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        feedback(userID);

        MainStation.restart = true;
    }

    private void feedback(String userID) {
        MsgDao msgDao = new MsgDaoImpl();

        boolean mark = false;
        while (true) {
            String notice;
            if (mark) {
                notice = "Thank you for your feedback.\nIs there any other malfunction on this bicycle?";
            } else {
                notice = "Is there any malfunction on this bicycle?";
            }
            Object[] choices = { "No, thanks", "Yes" };
            int choiceNum = (int) JOptionPane.showOptionDialog(null, notice, "Feedback",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

            if (choiceNum == 0) {
                break;
            } else {
                Object[] obj = { "1  Lock", "2  Brake", "3  Footlice", "4  Chain", "5  Handlebar", "6  Wheels" };
                String choiceStr = (String) JOptionPane.showInputDialog(null,
                        "Please choose the place of malfunction :\n", "Feedback", JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon(), obj, "");

                try {
                    msgDao.addOtherMsg("admin",
                            "Malfunction : " + choiceStr.split("  ")[1] + " from station " + MainStation.station);
                    msgDao.addOtherMsg(userID,
                            "Dear user, your feedback about \"" + choiceStr.split("  ")[1] + "\" has been received."
                                    + " If you have any other questions, welcome to connect the administer.");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                mark = true;
            }
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();

        BikesDao bikesDao = new BikesDaoImpl();
        RecordDao recordDao = new RecordDaoImpl();
        MsgDao msgDao = new MsgDaoImpl();

        try {
            // refresh the bikes' num
            if (text1.getText().charAt(0) == 'S')
                text1.setText("Station " + MainStation.station + " - "
                        + bikesDao.findBikesNumberByStation(MainStation.station) + " Bikes Left");

            // update overdue status
            List<Record> overdueRecords = new ArrayList<Record>();
            overdueRecords = recordDao.findRecordOverdue();
            if (overdueRecords != null) {
                for (Record overdueRecord : overdueRecords)
                    msgDao.addOverdueMsg(overdueRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainStation.setup();
    }
}